#
# Copyright (c) nexB Inc. and others. All rights reserved.
# purldb is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/purldb for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import binascii
import copy
import logging
import sys
import uuid
from collections import defaultdict
from difflib import SequenceMatcher
from itertools import groupby
from typing import NamedTuple

from django.contrib.postgres.fields import ArrayField
from django.db import models
from django.forms.models import model_to_dict
from django.utils.translation import gettext_lazy as _

import attr
import natsort
from licensedcode.cache import build_spdx_license_expression
from licensedcode.spans import Span
from packageurl import PackageURL
from packageurl.contrib.django.models import PackageURLMixin
from packageurl.contrib.django.models import PackageURLQuerySetMixin
from samecode.halohash import byte_hamming_distance

from matchcode_toolkit.fingerprinting import SNIPPET_WINDOW_LENGTH
from matchcode_toolkit.fingerprinting import create_halohash_chunks
from matchcode_toolkit.fingerprinting import hexstring_to_binarray
from matchcode_toolkit.fingerprinting import split_fingerprint


TRACE = False

logger = logging.getLogger(__name__)
logging.basicConfig(stream=sys.stdout)
logger.setLevel(logging.INFO)


def logger_debug(*args):
    return logger.debug(" ".join(isinstance(a, str) and a or repr(a) for a in args))


def sort_version(packages):
    """Return the packages sorted by version."""
    return natsort.natsorted(packages, key=lambda p: p.version.replace(".", "~") + "z")


class PackageQuerySet(PackageURLQuerySetMixin, models.QuerySet):
    def get_or_none(self, *args, **kwargs):
        """Return the object matching the given lookup parameters, or None if no match exists."""
        try:
            return self.get(*args, **kwargs)
        except Package.DoesNotExist:
            return


VCS_CHOICES = [
    ("git", "git"),
    ("svn", "subversion"),
    ("hg", "mercurial"),
    ("bzr", "bazaar"),
    ("cvs", "cvs"),
]


class LowerCaseField(models.CharField):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

    def to_python(self, value):
        return str(value).lower()


class HistoryMixin(models.Model):
    """
    A mixin with a JSONField-based append-only history field. The history field
    is a list containing mappings representing the history for this object. Each
    mapping contains the field "timestamp" and "message".
    """

    history = models.JSONField(
        default=list,
        blank=True,
        editable=False,
        help_text=_(
            "A list of mappings representing the history for this object. "
            'Each mapping contains the fields "timestamp" and "message".'
        ),
    )
    created_date = models.DateTimeField(
        null=True,
        blank=True,
        db_index=True,
        help_text=_("Timestamp set when a Package is created"),
    )
    last_modified_date = models.DateTimeField(
        null=True,
        blank=True,
        db_index=True,
        help_text=_("Timestamp set when a Package is created or modified"),
    )

    class Meta:
        abstract = True

    def get_history(self):
        """
        Return a list of mappings of all history entries from oldest to newest as:
            {"timestamp": "<YYYY-MM-DD-HH:MM:SS>", "message": "message"}
        """
        history_copy = copy.deepcopy(self.history)
        return history_copy


class HashFieldsMixin(models.Model):
    """
    The hash fields are not indexed by default, use the `indexes` in Meta as needed:
    class Meta:
        indexes = [
            models.Index(fields=['md5']),
            models.Index(fields=['sha1']),
            models.Index(fields=['sha256']),
            models.Index(fields=['sha512']),
        ]

    This model is from scancode.io.
    """

    md5 = models.CharField(
        _("MD5"),
        max_length=32,
        blank=True,
        null=True,
        db_index=True,
        help_text=_("MD5 checksum hex-encoded, as in md5sum."),
    )
    sha1 = models.CharField(
        _("SHA1"),
        max_length=40,
        blank=True,
        null=True,
        db_index=True,
        help_text=_("SHA1 checksum hex-encoded, as in sha1sum."),
    )
    sha256 = models.CharField(
        _("SHA256"),
        max_length=64,
        blank=True,
        null=True,
        db_index=True,
        help_text=_("SHA256 checksum hex-encoded, as in sha256sum."),
    )
    sha512 = models.CharField(
        _("SHA512"),
        max_length=128,
        blank=True,
        null=True,
        db_index=True,
        help_text=_("SHA512 checksum hex-encoded, as in sha512sum."),
    )

    class Meta:
        abstract = True


class ExtraDataFieldMixin(models.Model):
    """
    Adds the `extra_data` field and helper methods.

    This model is from scancode.io.
    """

    extra_data = models.JSONField(
        default=dict,
        blank=True,
        help_text=_("Optional mapping of extra data key/values."),
    )

    class Meta:
        abstract = True


class AbstractPackage(models.Model):
    """
    These model fields should be kept in line with `packagedcode.models.PackageData`.

    This model is from scancode.io.
    """

    filename = models.CharField(
        max_length=255,
        blank=True,
        null=True,
        db_index=True,
        help_text=_(
            "File name of a Resource sometimes part of the URI proper"
            "and sometimes only available through an HTTP header."
        ),
    )
    primary_language = models.CharField(
        max_length=50,
        blank=True,
        null=True,
        help_text=_("Primary programming language."),
    )
    description = models.TextField(
        blank=True,
        null=True,
        help_text=_(
            "Description for this package. "
            "By convention the first line should be a summary when available."
        ),
    )
    release_date = models.DateTimeField(
        blank=True,
        null=True,
        db_index=True,
        help_text=_(
            "The date and time that the package file was created, or when "
            "it was posted to its original download source."
        ),
    )
    homepage_url = models.CharField(
        _("Homepage URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_("URL to the homepage for this package."),
    )
    download_url = models.CharField(
        _("Download URL"),
        max_length=2048,
        unique=True,
        db_index=True,
        help_text=_("A direct download URL."),
    )
    size = models.BigIntegerField(
        blank=True,
        null=True,
        db_index=True,
        help_text=_("Size in bytes."),
    )
    bug_tracking_url = models.CharField(
        _("Bug tracking URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_("URL to the issue or bug tracker for this package."),
    )
    code_view_url = models.CharField(
        _("Code view URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_("a URL where the code can be browsed online."),
    )
    vcs_url = models.CharField(
        _("VCS URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_(
            "A URL to the VCS repository in the SPDX form of: "
            '"git", "svn", "hg", "bzr", "cvs", '
            "https://github.com/nexb/scancode-toolkit.git@405aaa4b3 "
            'See SPDX specification "Package Download Location" '
            "at https://spdx.org/spdx-specification-21-web-version#h.49x2ik5"
        ),
    )
    repository_homepage_url = models.CharField(
        _("Repository homepage URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_(
            "URL to the page for this package in its package repository. "
            "This is typically different from the package homepage URL proper."
        ),
    )
    repository_download_url = models.CharField(
        _("Repository download URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_(
            "Download URL to download the actual archive of code of this "
            "package in its package repository. "
            "This may be different from the actual download URL."
        ),
    )
    api_data_url = models.CharField(
        _("API data URL"),
        max_length=1024,
        blank=True,
        null=True,
        help_text=_(
            "API URL to obtain structured data for this package such as the "
            "URL to a JSON or XML api its package repository."
        ),
    )
    copyright = models.TextField(
        blank=True,
        null=True,
        help_text=_("Copyright statements for this package. Typically one per line."),
    )
    holder = models.TextField(
        blank=True,
        null=True,
        help_text=_("Holders for this package. Typically one per line."),
    )
    declared_license_expression = models.TextField(
        blank=True,
        null=True,
        help_text=_(
            "The license expression for this package typically derived "
            "from its extracted_license_statement or from some other type-specific "
            "routine or convention."
        ),
    )
    license_detections = models.JSONField(
        default=list,
        blank=True,
        null=True,
        help_text=_(
            "A list of LicenseDetection mappings typically derived "
            "from its extracted_license_statement or from some other type-specific "
            "routine or convention."
        ),
    )
    other_license_expression = models.TextField(
        blank=True,
        null=True,
        help_text=_(
            "The license expression for this package which is different from the "
            "declared_license_expression, (i.e. not the primary license) "
            "routine or convention."
        ),
    )
    other_license_detections = models.JSONField(
        default=list,
        blank=True,
        null=True,
        help_text=_(
            "A list of LicenseDetection mappings which is different from the "
            "declared_license_expression, (i.e. not the primary license) "
            "These are detections for the detection for the license expressions "
            "in other_license_expression. "
        ),
    )
    extracted_license_statement = models.TextField(
        blank=True,
        null=True,
        help_text=_(
            "The license statement mention, tag or text as found in a "
            "package manifest and extracted. This can be a string, a list or dict of "
            "strings possibly nested, as found originally in the manifest."
        ),
    )
    notice_text = models.TextField(
        blank=True,
        null=True,
        help_text=_("A notice text for this package."),
    )
    datasource_id = models.CharField(
        max_length=64,
        blank=True,
        null=True,
        help_text=_("The identifier for the datafile handler used to obtain this package."),
    )
    file_references = models.JSONField(
        default=list,
        blank=True,
        null=True,
        help_text=_(
            "List of file paths and details for files referenced in a package "
            "manifest. These may not actually exist on the filesystem. "
            "The exact semantics and base of these paths is specific to a "
            "package type or datafile format."
        ),
    )

    class Meta:
        abstract = True

    @property
    def declared_license_expression_spdx(self):
        declared_license_expression = self.declared_license_expression
        if declared_license_expression:
            return build_spdx_license_expression(declared_license_expression)

    @property
    def other_license_expression_spdx(self):
        other_license_expression = self.other_license_expression
        if other_license_expression:
            return build_spdx_license_expression(other_license_expression)


class PackageContentType(models.IntegerChoices):
    """List of Package content types."""

    # TODO: curation is a special case, based on how the curation identity
    # fields matches with the current package
    CURATION = 1, "curation"
    PATCH = 2, "patch"
    SOURCE_REPO = 3, "source_repo"
    SOURCE_ARCHIVE = 4, "source_archive"
    BINARY = 5, "binary"
    TEST = 6, "test"
    DOC = 7, "doc"


def get_class_name(obj):
    """Return a string containing the class name of `obj`"""
    return type(obj).__name__


# TODO: Figure out what ordering we want for the fields
class Package(
    HistoryMixin,
    ExtraDataFieldMixin,
    HashFieldsMixin,
    PackageURLMixin,
    AbstractPackage,
):
    uuid = models.UUIDField(verbose_name=_("UUID"), default=uuid.uuid4, unique=True, editable=False)
    mining_level = models.PositiveIntegerField(
        default=0,
        help_text=_(
            "A numeric indication of the highest depth and breadth "
            "of package data collected through previous visits. "
            "Higher means more and deeper collection."
        ),
    )
    keywords = ArrayField(
        base_field=models.TextField(
            blank=True,
            null=True,
        ),
        default=list,
        blank=True,
        null=True,
        help_text=_("A list of keywords."),
    )
    root_path = models.CharField(
        max_length=1024,
        blank=True,
        null=True,
        help_text=_(
            "The path to the root of the package documented in this manifest "
            "if any, such as a Maven .pom or a npm package.json parent "
            "directory."
        ),
    )
    source_packages = ArrayField(
        base_field=models.TextField(
            blank=True,
            null=True,
        ),
        default=list,
        blank=True,
        null=True,
        help_text=_(
            'A list of source package URLs (aka. "purl") for this package. '
            'For instance an SRPM is the "source package" for a binary RPM.'
        ),
    )
    last_indexed_date = models.DateTimeField(
        null=True,
        blank=True,
        help_text="Timestamp set to the date of the last indexing. Used to track indexing status.",
    )
    index_error = models.TextField(
        null=True,
        blank=True,
        help_text="Indexing errors messages. When present this means the indexing has failed.",
    )
    package_sets = models.ManyToManyField(
        "PackageSet",
        related_name="packages",
        help_text=_("A set representing the Package sets this Package is a member of."),
    )
    package_content = models.IntegerField(
        null=True,
        choices=PackageContentType.choices,
        help_text=_(
            "Content of this Package as one of: {}".format(", ".join(PackageContentType.labels))
        ),
    )
    summary = models.JSONField(
        default=dict,
        blank=True,
        null=True,
        help_text=_("A mapping containing a summary and license clarity score for this Package"),
    )
    is_duplicate = models.BooleanField(
        default=False,
        help_text=_("True if this Package is a duplicate of another Package"),
    )

    objects = PackageQuerySet.as_manager()

    # TODO: Think about ordering, unique together, indexes, etc.
    class Meta:
        managed = False
        db_table = 'packagedb_package'
        app_label = 'matchcode_toolkit'
        ordering = ["id"]
        unique_together = [
            (
                "download_url",
                "type",
                "namespace",
                "name",
                "version",
                "qualifiers",
                "subpath",
            )
        ]
        indexes = [
            # multicolumn index for search on a whole `purl`
            models.Index(fields=["type", "namespace", "name", "version", "qualifiers", "subpath"]),
            models.Index(fields=["type"]),
            models.Index(fields=["namespace"]),
            models.Index(fields=["name"]),
            models.Index(fields=["version"]),
            models.Index(fields=["qualifiers"]),
            models.Index(fields=["subpath"]),
            models.Index(fields=["download_url"]),
            models.Index(fields=["filename"]),
            models.Index(fields=["size"]),
            models.Index(fields=["release_date"]),
            models.Index(fields=["md5"]),
            models.Index(fields=["sha1"]),
            models.Index(fields=["sha256"]),
            models.Index(fields=["sha512"]),
            models.Index(fields=["package_content"]),
        ]

    def __str__(self):
        return self.package_url

    @property
    def purl(self):
        return self.package_url

    @property
    def package_uid(self):
        purl = PackageURL.from_string(self.package_url)
        purl.qualifiers["uuid"] = str(self.uuid)
        return str(purl)

    # def to_dict(self):
    #     from packagedb.serializers import PackageMetadataSerializer

    #     package_metadata = PackageMetadataSerializer(self).data
    #     return package_metadata

    def get_all_versions(self):
        """Return a list of all the versions of this Package."""
        manager = self.__class__.objects
        queryset = manager.filter(
            name=self.name,
            type=self.type,
            namespace=self.namespace,
        )
        return queryset

    def get_latest_version(self):
        """Return the latest version of this Package."""
        sorted_versions = sort_version(self.get_all_versions())
        if sorted_versions:
            return sorted_versions[-1]


party_person = "person"
# often loosely defined
party_project = "project"
# more formally defined
party_org = "organization"
PARTY_TYPES = (
    (party_person, party_person),
    (party_project, party_project),
    (party_org, party_org),
)


class Party(models.Model):
    """A party is a person, project or organization related to a package."""

    package = models.ForeignKey(
        Package,
        related_name="parties",
        on_delete=models.CASCADE,
        help_text=_("The Package that this party is related to"),
    )

    type = models.CharField(
        max_length=20,
        blank=True,
        null=True,
        choices=PARTY_TYPES,
        help_text=_("the type of this party"),
    )

    role = models.CharField(
        max_length=32,
        blank=True,
        null=True,
        help_text=_(
            "A role for this party. Something such as author, "
            "maintainer, contributor, owner, packager, distributor, "
            "vendor, developer, owner, etc."
        ),
    )

    name = models.CharField(
        max_length=1024, blank=True, null=True, help_text=_("Name of this party.")
    )

    email = models.CharField(
        max_length=1024, blank=True, null=True, help_text=_("Email for this party.")
    )

    url = models.CharField(
        max_length=1024,
        blank=True,
        null=True,
        help_text=_("URL to a primary web page for this party."),
    )

    # def to_dict(self):
    #     from packagedb.serializers import PartySerializer

    #     party_data = PartySerializer(self).data
    #     return party_data

    class Meta:
        managed = False
        db_table = 'packagedb_party'
        app_label = 'matchcode_toolkit'


class DependentPackage(models.Model):
    """An identifiable dependent package package object."""

    package = models.ForeignKey(
        Package,
        related_name="dependencies",
        on_delete=models.CASCADE,
        help_text=_("The Package that this dependent package is related to"),
    )

    purl = models.CharField(
        max_length=2048,
        blank=True,
        null=True,
        help_text=_("A compact purl package URL"),
    )

    extracted_requirement = models.CharField(
        max_length=200,
        blank=True,
        null=True,
        help_text=_("A string defining version(s)requirements. Package-type specific."),
    )

    scope = models.CharField(
        max_length=100,
        blank=True,
        null=True,
        help_text=_(
            "The scope of this dependency, such as runtime, install, etc. "
            "This is package-type specific and is the original scope string."
        ),
    )

    is_runtime = models.BooleanField(
        default=True, help_text=_("True if this dependency is a runtime dependency.")
    )

    is_optional = models.BooleanField(
        default=False, help_text=_("True if this dependency is an optional dependency")
    )

    is_pinned = models.BooleanField(
        default=False,
        help_text=_(
            "True if this dependency version requirement has "
            "been resolved and this dependency url points to an "
            "exact version."
        ),
    )

    # def to_dict(self):
    #     from packagedb.serializers import DependentPackageSerializer

    #     depedent_package_data = DependentPackageSerializer(self).data
    #     return depedent_package_data

    class Meta:
        managed = False
        db_table = 'packagedb_dependentpackage'
        app_label = 'matchcode_toolkit'


class AbstractResource(models.Model):
    """These model fields should be kept in line with scancode.resource.Resource"""

    path = models.CharField(
        max_length=2000,
        help_text=_(
            "The full path value of a resource (file or directory) in the archive it is from."
        ),
    )

    name = models.CharField(
        max_length=255,
        blank=True,
        help_text=_("File or directory name of this resource with its extension."),
    )

    extension = models.CharField(
        max_length=100,
        blank=True,
        help_text=_("File extension for this resource (directories do not have an extension)."),
    )

    size = models.BigIntegerField(
        blank=True,
        null=True,
        help_text=_("Size in bytes."),
    )

    mime_type = models.CharField(
        max_length=100,
        blank=True,
        null=True,
        help_text=_(
            "MIME type (aka. media type) for this resource. "
            "See https://en.wikipedia.org/wiki/Media_type"
        ),
    )

    file_type = models.CharField(
        max_length=1024,
        blank=True,
        null=True,
        help_text=_("Descriptive file type for this resource."),
    )

    programming_language = models.CharField(
        max_length=50,
        blank=True,
        null=True,
        help_text=_("Programming language of this resource if this is a code file."),
    )

    is_binary = models.BooleanField(default=False)
    is_text = models.BooleanField(default=False)
    is_archive = models.BooleanField(default=False)
    is_key_file = models.BooleanField(default=False)
    is_media = models.BooleanField(default=False)

    is_file = models.BooleanField(
        default=False,
        help_text=_("True if this Resource is a file, False if it is a Directory"),
    )

    @property
    def type(self):
        return "file" if self.is_file else "directory"

    @type.setter
    def type(self, value):
        if value == "file":
            self.is_file = True
        else:
            self.is_file = False

    def __str__(self):
        return self.path

    class Meta:
        abstract = True


class ScanFieldsModelMixin(models.Model):
    """
    Fields returned by the ScanCode-toolkit scans.

    This model is from ScanCode.io
    """

    detected_license_expression = models.TextField(
        blank=True,
        help_text=_("TODO"),
    )
    detected_license_expression_spdx = models.TextField(
        blank=True,
        help_text=_("TODO"),
    )
    license_detections = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of license detection details."),
    )
    license_clues = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of license clues."),
    )
    percentage_of_license_text = models.FloatField(
        blank=True,
        null=True,
        help_text=_("TODO"),
    )
    copyrights = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of detected copyright statements (and related detection details)."),
    )
    holders = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of detected copyright holders (and related detection details)."),
    )
    authors = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of detected authors (and related detection details)."),
    )
    package_data = models.JSONField(
        default=list,
        blank=True,
        help_text=_("List of Package data detected from this CodebaseResource"),
    )
    emails = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of detected emails (and related detection details)."),
    )
    urls = models.JSONField(
        blank=True,
        default=list,
        help_text=_("List of detected URLs (and related detection details)."),
    )

    class Meta:
        abstract = True

    @classmethod
    def scan_fields(cls):
        return [field.name for field in ScanFieldsModelMixin._meta.get_fields()]


class Resource(ExtraDataFieldMixin, HashFieldsMixin, ScanFieldsModelMixin, AbstractResource):
    package = models.ForeignKey(
        Package,
        related_name="resources",
        on_delete=models.CASCADE,
        help_text=_("The Package that this Resource is from"),
    )

    git_sha1 = models.CharField(
        max_length=40,
        blank=True,
        null=True,
        help_text=_("git SHA1 checksum hex-encoded"),
    )

    class Meta:
        managed = False
        db_table = 'packagedb_resource'
        app_label = 'matchcode_toolkit'
        unique_together = (("package", "path"),)
        ordering = ("id",)
        indexes = [
            models.Index(fields=["md5"]),
            models.Index(fields=["sha1"]),
            models.Index(fields=["sha256"]),
            models.Index(fields=["sha512"]),
            models.Index(fields=["git_sha1"]),
        ]

    @property
    def for_packages(self):
        """Return the list of all Packages associated to this resource."""
        return [self.package.package_uid or str(self.package)]

    # def to_dict(self):
    #     from packagedb.serializers import ResourceMetadataSerializer

    #     resource_metadata = ResourceMetadataSerializer(self).data
    #     return resource_metadata


class PackageRelation(models.Model):
    """
    A directed relationship between two packages.

    This consists of three attributes:
    - The "from" (or subject) package in the relationship,
    - the "to" (or object) package in the relationship,
    - and the "relationship" (or predicate) choice that specifies the relationship.
    """

    class Relationship(models.TextChoices):
        SOURCE_PACKAGE = "source_package"
        BINARY_PACKAGE = "binary_package"

    from_package = models.ForeignKey(
        Package,
        related_name="related_to",
        on_delete=models.CASCADE,
        editable=False,
    )

    to_package = models.ForeignKey(
        Package,
        related_name="related_from",
        on_delete=models.CASCADE,
        editable=False,
    )

    relationship = models.CharField(
        max_length=30,
        choices=Relationship.choices,
        help_text="Relationship between the from and to package "
        'URLs such as "source_package" when a package '
        "is the source code package for another package.",
    )

    def __str__(self):
        return (
            f"{self.from_package.purl} is the {self.relationship.upper()} to {self.to_package.purl}"
        )

    class Meta:
        managed = False
        db_table = 'packagedb_packagerelation'
        app_label = 'matchcode_toolkit'

def make_relationship(
    from_package,
    to_package,
    relationship,
):
    """Create and return the from/to package relathionship if it does exists."""
    pkg, _created = PackageRelation.objects.get_or_create(
        from_package=from_package,
        to_package=to_package,
        relationship=relationship,
    )
    return pkg


class PackageSet(models.Model):
    """A group of related Packages"""

    uuid = models.UUIDField(
        verbose_name=_("UUID"),
        default=uuid.uuid4,
        unique=True,
        help_text=_("The identifier of the Package set"),
    )

    def add_to_package_set(self, package):
        self.packages.add(package)

    def get_package_set_members(self):
        """Return related Packages"""
        return self.packages.order_by(
            "package_content",
        )

    class Meta:
        managed = False
        db_table = 'packagedb_packageset'
        app_label = 'matchcode_toolkit'


class PackageRelatedMixin(models.Model):
    package = models.ForeignKey(
        Package,
        help_text="The Package that this file is from",
        null=False,
        on_delete=models.CASCADE,
    )

    class Meta:
        abstract = True


###############################################################################
# FILE MATCHING
###############################################################################
class BaseFileIndex(PackageRelatedMixin, models.Model):
    sha1 = models.BinaryField(
        max_length=20,
        db_index=True,
        help_text="Binary form of a SHA1 checksum in lowercase hex for a file",
        null=False,
        blank=False,
    )

    class Meta:
        abstract = True

    @classmethod
    def match(cls, sha1):
        """Return a list of matched Packages that contains a file with a SHA1 value of `sha1`"""
        if TRACE:
            logger_debug(cls.__name__, "match:", "sha1:", sha1)

        if not sha1:
            return cls.objects.none()

        sha1_in_bin = hexstring_to_binarray(sha1)
        matches = cls.objects.filter(sha1=sha1_in_bin)
        if TRACE:
            for match in matches:
                package = match.package
                dct = model_to_dict(package)
                logger_debug(cls.__name__, "match:", "matched_file:", dct)
        return matches

    def fingerprint(self):
        return binascii.hexlify(self.sha1).decode("utf-8")


class ExactPackageArchiveIndex(BaseFileIndex):
    class Meta:
        managed = False
        db_table = 'matchcode_exactpackagearchiveindex'
        app_label = 'matchcode_toolkit'


class ExactFileIndex(BaseFileIndex):
    class Meta:
        managed = False
        db_table = 'matchcode_exactfileindex'
        app_label = 'matchcode_toolkit'


################################################################################
# DIRECTORY MATCHING
################################################################################
def bah128_ranges(indexed_elements_count, range_ratio=0.05):
    """
    Return a tuple of two integers, one smaller than `indexed_elements_count` by
    `range_ratio` and one larger than `indexed_elements_count` by `range_ratio`

    This helps us match on directories with similar amounts of files. Directory
    fingerprints become uncomparable if one fingerprint has more elements
    indexed in it than another.
    """
    return (
        int(indexed_elements_count * (1 - range_ratio)),
        int(indexed_elements_count * (1 + range_ratio)),
    )


class ApproximateMatchingHashMixin(PackageRelatedMixin, models.Model):
    indexed_elements_count = models.IntegerField(
        help_text="Number of elements that went into the fingerprint",
    )

    chunk1 = models.BinaryField(
        max_length=4,
        db_index=True,
        help_text="Binary form of the first 8 (0-7) hex digits of the fingerprint",
        null=False,
        blank=False,
    )

    chunk2 = models.BinaryField(
        max_length=4,
        db_index=True,
        help_text="Binary form of the second 8 (8-15) hex digits of the fingerprint",
        null=False,
        blank=False,
    )

    chunk3 = models.BinaryField(
        max_length=4,
        db_index=True,
        help_text="Binary form of the third 8 (16-23) hex digits of the fingerprint",
        null=False,
        blank=False,
    )

    chunk4 = models.BinaryField(
        max_length=4,
        db_index=True,
        help_text="Binary form of the fourth 8 (24-32) hex digits of the fingerprint",
        null=False,
        blank=False,
    )

    path = models.CharField(
        max_length=2000,
        help_text=_("The full path value of this resource"),
    )

    class Meta:
        abstract = True
        unique_together = ["chunk1", "chunk2", "chunk3", "chunk4", "package", "path"]

    def __str__(self):
        return self.fingerprint()

    @classmethod
    def match(cls, fingerprint, resource=None, exact_match=False):
        """Return a list of matched Packages"""
        if TRACE:
            logger_debug(
                cls.__name__,
                "match:",
                "fingerprint:",
                fingerprint,
                "resource:",
                resource,
            )

        if not fingerprint:
            return cls.objects.none()

        indexed_elements_count, bah128 = split_fingerprint(fingerprint)
        chunk1, chunk2, chunk3, chunk4 = create_halohash_chunks(bah128)

        # Step 0: if exact only, then return a filter
        if exact_match:
            matches = cls.objects.filter(
                indexed_elements_count=indexed_elements_count,
                chunk1=chunk1,
                chunk2=chunk2,
                chunk3=chunk3,
                chunk4=chunk4,
            )
            return matches

        # Step 1: find fingerprints with matching chunks
        frange = bah128_ranges(indexed_elements_count)
        matches = cls.objects.filter(
            models.Q(indexed_elements_count__range=frange, chunk1=chunk1)
            | models.Q(indexed_elements_count__range=frange, chunk2=chunk2)
            | models.Q(indexed_elements_count__range=frange, chunk3=chunk3)
            | models.Q(indexed_elements_count__range=frange, chunk4=chunk4)
        )

        if TRACE:
            for match in matches:
                dct = model_to_dict(match)
                logger_debug(cls.__name__, "match:", "matched_package:", dct)

        # Step 2: calculate Hamming distance of all matches

        hamming_distance_threshold = 10
        # Store all close matches in a dictionary of querysets
        matches_by_hamming_distance = defaultdict(cls.objects.none)
        for match in matches:
            # Get fingerprint from the match
            fp = match.fingerprint()
            _, match_bah128 = split_fingerprint(fp)

            # Perform Hamming distance calculation between the fingerprint we
            # are looking up and a potential match fingerprint
            hd = byte_hamming_distance(bah128, match_bah128)

            # TODO: try other thresholds if this is too restrictive
            # TODO: rank matches instead of having threshold
            if hd < hamming_distance_threshold:
                # Save match to `matches_by_hamming_distance` by adding the matched object
                # to the queryset
                matches_by_hamming_distance[hd] |= cls.objects.filter(pk=match.pk)

        if TRACE:
            logger_debug(list(matches_by_hamming_distance.items()))

        # Step 3: order matches from lowest Hamming distance to highest Hamming distance
        # TODO: consider limiting matches for brevity
        hamming_distances_and_matches = []
        for hamming_distance, matches in sorted(matches_by_hamming_distance.items()):
            hamming_distances_and_matches.append((hamming_distance, matches))

        if TRACE:
            for hamming_distance, matches in hamming_distances_and_matches:
                for match in matches:
                    dct = model_to_dict(match)
                    logger_debug(
                        cls.__name__,
                        "match:",
                        "step_3_hamming_distance:",
                        hamming_distance,
                        "step_3_matched_package:",
                        dct,
                    )

        # Step 4: use file heuristics to rank matches from step 3

        # If we are not given resource data, return the matches we have
        if not (resource and hamming_distances_and_matches):
            remaining_matches = cls.objects.none()
            if hamming_distances_and_matches:
                for _, matches in hamming_distances_and_matches:
                    remaining_matches |= matches
            return remaining_matches

        resource_size = resource.size
        matches_by_rank_attributes = defaultdict(list)
        for hamming_distance, matches in hamming_distances_and_matches:
            for match in matches:
                matched_resource = match.package.resources.get(path=match.path)

                if TRACE:
                    logger_debug(
                        cls.__name__,
                        "match:",
                        "step_4_matched_resource:",
                        matched_resource,
                    )

                # Compute size and name difference
                if matched_resource.is_file:
                    size_difference = abs(resource_size - matched_resource.size)
                else:
                    # TODO: index number of files in a directory so we can use
                    # that for size comparison. For now, we are going to
                    # disregard size as a factor.
                    size_difference = 0
                name_sequence_matcher = SequenceMatcher(a=resource.name, b=matched_resource.name)
                name_difference = 1 - name_sequence_matcher.ratio()
                rank_attributes = (hamming_distance, size_difference, name_difference)
                matches_by_rank_attributes[rank_attributes].append(match)

                if TRACE:
                    logger_debug(
                        cls.__name__,
                        "match:",
                        "step_4_size_difference:",
                        size_difference,
                        "step_4_name_difference:",
                        name_difference,
                    )

        # Order these from low to high (low being low difference/very similar)),
        # first by hamming distance, then by size difference, and finally by name difference.
        ranked_attributes = sorted(matches_by_rank_attributes)
        best_ranked_attributes = ranked_attributes[0]
        ranked_matches = matches_by_rank_attributes[best_ranked_attributes]

        if TRACE:
            dct = model_to_dict(match)
            logger_debug(cls.__name__, "match:", "step_4_best_match:", dct)

        matches = cls.objects.filter(pk__in=[match.pk for match in ranked_matches])
        return matches

    def get_chunks(self):
        chunk1 = binascii.hexlify(self.chunk1)
        chunk2 = binascii.hexlify(self.chunk2)
        chunk3 = binascii.hexlify(self.chunk3)
        chunk4 = binascii.hexlify(self.chunk4)
        return chunk1, chunk2, chunk3, chunk4

    def fingerprint(self):
        indexed_element_count_as_hex_bytes = b"%08x" % self.indexed_elements_count
        chunk1, chunk2, chunk3, chunk4 = self.get_chunks()
        fingerprint = indexed_element_count_as_hex_bytes + chunk1 + chunk2 + chunk3 + chunk4
        return fingerprint.decode("utf-8")


class ApproximateDirectoryStructureIndex(ApproximateMatchingHashMixin):
    class Meta:
        managed = False
        db_table = 'matchcode_approximatedirectorystructureindex'
        app_label = 'matchcode_toolkit'

class ApproximateDirectoryContentIndex(ApproximateMatchingHashMixin):
    class Meta:
        managed = False
        db_table = 'matchcode_approximatedirectorycontentindex'
        app_label = 'matchcode_toolkit'


class ApproximateResourceContentIndex(ApproximateMatchingHashMixin):
    class Meta:
        managed = False
        db_table = 'matchcode_approximateresourcecontentindex'
        app_label = 'matchcode_toolkit'


class PackageSnippetMatch(NamedTuple):
    package: Package
    fingerprints: list
    fingerprints_count: int


class ResourceSnippetMatch(NamedTuple):
    package: Package
    resource: Resource
    similarity: float
    match_detections: list["Span"]

    def to_dict(self):
        match_detections = []
        for md in self.match_detections:
            match_detections.extend(list(md))
        return {
            "package": str(self.package),
            "resource": self.resource.path,
            "similarity": self.similarity,
            "match_detections": match_detections,
        }


class BaseSnippetIndexMixin(PackageRelatedMixin, models.Model):
    resource = models.ForeignKey(
        Resource,
        help_text="The Package that this snippet fingerprint is from",
        null=False,
        on_delete=models.CASCADE,
    )

    fingerprint = models.BinaryField(
        max_length=16,
        db_index=True,
        help_text="Binary form of a snippet fingerprint",
        null=False,
        blank=False,
    )

    position = models.PositiveIntegerField(
        null=False,
        blank=False,
        default=0,
    )

    class Meta:
        abstract = True

    @classmethod
    def match(cls, fingerprints):
        """
        Return a list of PackageSnippetMatch for matched Package.
        """
        if TRACE:
            logger_debug(
                cls.__name__,
                "match:",
                "fingerprints:",
                fingerprints,
            )

        if not fingerprints:
            return cls.objects.none()

        # strip positions
        only_fings = [hexstring_to_binarray(fing["snippet"]) for fing in fingerprints]

        # Step 0: get all fingerprint records that match whith the input
        matched_fps = cls.objects.filter(fingerprint__in=only_fings)

        # Step 1: count Packages whose fingerprints appear
        # Step 1.1: get Packages that show up in the query
        packages = set(f.package for f in matched_fps.iterator())

        # Step 1.2: group matched Packages and fingerprints with count
        matches = []
        for package in packages:
            match_fingerprints = matched_fps.filter(package=package).distinct("fingerprint")
            matches.append(
                PackageSnippetMatch(
                    package=package,
                    fingerprints=match_fingerprints,
                    fingerprints_count=match_fingerprints.count(),
                )
            )

        return matches

    @classmethod
    def match_resources(cls, fingerprints, top=None, **kwargs):
        """
        Return a list of ResourceSnippetMatch for matched Resources.
        Only return the ``top`` matches, or all matches if ``top`` is zero.
        """
        from matchcode_toolkit.match import merge_matches

        if TRACE:
            logger_debug(
                cls.__name__,
                "match:",
                "fingerprints:",
                fingerprints,
            )

        if not fingerprints:
            return cls.objects.none()

        # map fingerprints to spans
        # after we have our matched fingerprints, we can go  back and get the
        # spans that they were for When we hhave the spans that we matched to,
        # we need to consolidate these spans, a lot of spans will be contained
        # in the other
        extended_file_fragment_matches_by_fingerprints = defaultdict(list)
        for fp in fingerprints:
            snippet = fp["snippet"]
            start_pos = fp["position"]
            end_pos = start_pos + SNIPPET_WINDOW_LENGTH - 1
            resource = kwargs.get("resource")
            qspan = Span(start_pos, end_pos)
            extended_file_fragment_matches_by_fingerprints[snippet].append(
                ExtendedFileFragmentMatch(qspan=qspan, qresource=resource)
            )

        only_fings = [
            hexstring_to_binarray(fing)
            for fing in extended_file_fragment_matches_by_fingerprints.keys()
        ]

        # TODO: track matched package and package resource in ExtendedFileFragmentMatch

        # Step 0: get all fingerprint records that match whith the input
        matched_fps = cls.objects.filter(fingerprint__in=only_fings)

        # Step 1: get Resources that show up in the query
        resources = set(f.resource for f in matched_fps.iterator())

        # Step 2: see which Resource we most match to by calculating jaccard coefficient of our fingerprints against the others
        fingerprints_length = len(only_fings)
        matches = []
        for r in resources:
            # Get unique snippet fingerprints for this Resource
            r_snippets = cls.objects.filter(resource=r).distinct("fingerprint")
            matching_snippets = r_snippets.filter(fingerprint__in=only_fings)
            r_snippets_count = r_snippets.count()
            matching_snippets_count = matching_snippets.count()
            jc = matching_snippets_count / (
                (r_snippets_count + fingerprints_length) - matching_snippets_count
            )
            for matching_snippet in matching_snippets:
                fp = matching_snippet.fingerprint.hex()
                match_templates = extended_file_fragment_matches_by_fingerprints.get(fp)
                for match_template in match_templates:
                    match_copy = copy.deepcopy(match_template)
                    match_copy.iresource = r
                    match_copy.ipackage = r.package
                    match_copy.similarity = jc
                    matches.append(match_copy)

        # TODO: we do not track position so we do not know if we have a long or short match, or if the matches overlap
        # We need have start position and end position, we need to have a function that combines overlapping matches into a span of matches
        # This should be aligned between the query and index side
        # we need to have a list of matched position in ascending order

        # Step 3: group matches by ipackage and iresource, then spans
        def sorter(m):
            return -m.similarity, m.qspan.start, -m.len()

        matches.sort(key=sorter)
        matches_by_ipackage_iresource = groupby(matches, key=sorter)
        final_matches = []
        similarity = 0.0
        prev_ipackage = None
        prev_iresource = None
        match_detections = []
        for (
            similarity,
            qspan_start,
            match_length,
        ), grouped_matches in matches_by_ipackage_iresource:
            for match in grouped_matches:
                package_changed = match.ipackage != prev_ipackage if prev_ipackage else False
                resource_changed = match.iresource != prev_iresource if prev_iresource else False

                if package_changed or resource_changed:
                    # finish up match_detections for this ipackage or iresource and create ResourceSnippetMatch
                    match_detections = merge_matches(match_detections)
                    mds = []
                    for match_detection in match_detections:
                        m = match_detection.qspan.subspans()
                        mds.extend(m)
                    m = ResourceSnippetMatch(
                        package=prev_ipackage,
                        resource=prev_iresource,
                        similarity=abs(similarity),
                        match_detections=mds,
                    )
                    final_matches.append(m)
                    match_detections = []

                match_detections.append(match)
                prev_ipackage = match.ipackage
                prev_iresource = match.iresource

        if match_detections:
            # we are out of the loop but not reported what was left over
            match_detections = merge_matches(match_detections)
            mds = []
            for match_detection in match_detections:
                m = match_detection.qspan.subspans()
                mds.extend(m)
            m = ResourceSnippetMatch(
                package=prev_ipackage,
                resource=prev_iresource,
                similarity=abs(similarity),
                match_detections=mds,
            )
            final_matches.append(m)

        return final_matches[:top]


class SnippetIndex(BaseSnippetIndexMixin, models.Model):
    class Meta:
        managed = False
        db_table = 'matchcode_snippetindex'
        app_label = 'matchcode_toolkit'


class StemmedSnippetIndex(BaseSnippetIndexMixin, models.Model):
    class Meta:
        managed = False
        db_table = 'matchcode_stemmedsnippetindex'
        app_label = 'matchcode_toolkit'


class ApproximateFileIndex(ApproximateMatchingHashMixin, models.Model):
    class Meta:
        managed = False
        db_table = 'matchcode_approximatefileindex'
        app_label = 'matchcode_toolkit'


@attr.s(slots=True, eq=False, order=False, repr=False)
class ExtendedFileFragmentMatch:
    """
    A File origin detection match to an index Resource with matched query positions, lines and
    matched index positions. This also computes a score for a match. At a high level,
    a match behaves a little like a Span/interval and has several similar methods taking
    into account both the query (codebase-side) and index-side Spans.

    Note that the relationship between the query-side qspan Span and the index-
    side ispan Span is such that:

    - they always have the exact same number of items but when sorted each
      value at a given index may be different
    - the nth position when sorted by position is such that their token
      value is equal for this position.

    These properties mean that the qspan and ispan can be safely zipped together with
    zip() to align them. Also and as a convention throughout, we always use qspan first then
    ispan: in general we put codebase/query-related variables on the left hand side and
    index-related variables on the right hand side.
    """

    qresource = attr.ib(
        default=None,
        type=Resource,
        metadata=dict(help="matched from query-side Resource"),
    )

    iresource = attr.ib(
        default=None,
        type=Resource,
        metadata=dict(help="matched to index-side Resource"),
    )

    ipackage = attr.ib(
        default=None, type=Package, metadata=dict(help="matched to index-side Package")
    )

    qspan = attr.ib(
        default=None,
        metadata=dict(help="query matched Span, start at zero which is the query Resource start."),
    )

    start_line = attr.ib(default=0, metadata=dict(help="match start line, 1-based"))

    end_line = attr.ib(default=0, metadata=dict(help="match end line, 1-based"))

    similarity = attr.ib(
        default=0,
        metadata=dict(
            help="a float that represents the Jaccard index of this match against the index-side Resource"
        ),
    )

    def __repr__(self):
        qreg = (self.qstart, self.qend)
        return (
            f"ExtendedFileFragmentMatch: "
            f"qres: {self.qresource!r}, "
            f"ires: {self.iresource!r}, "
            f"lines={self.lines()!r}, "
            f"len={self.len()}, "
            f"qreg={qreg!r}, "
            f"qspan={self.qspan}"
        )

    # NOTE: we implement all rich comparison operators with some inlining for performance reasons,
    # and we ignore the qresource side. We never compare matches for different resources.

    def __eq__(self, other):
        """
        Strict equality is based on matched resource and matched positions.
        """
        return (
            isinstance(other, ExtendedFileFragmentMatch)
            and self.qspan == other.qspan
            and self.iresource == other.iresource
        )

    def __ne__(self, other):
        """
        Strict inequality is based on matched resource and matched positions.
        """
        return (
            not isinstance(other, ExtendedFileFragmentMatch)
            or self.qspan != other.qspan
            or self.iresource != other.iresource
        )

    def __lt__(self, other):
        if not isinstance(other, ExtendedFileFragmentMatch):
            return NotImplemented

        return self.qstart < other.qstart

    def __lte__(self, other):
        if not isinstance(other, ExtendedFileFragmentMatch):
            return NotImplemented

        return self.qstart < other.qstart or (
            self.qspan == other.qspan and self.iresource == other.iresource
        )

    def __gt__(self, other):
        if not isinstance(other, ExtendedFileFragmentMatch):
            return NotImplemented

        return self.qstart > other.qstart

    def __gte__(self, other):
        if not isinstance(other, ExtendedFileFragmentMatch):
            return NotImplemented

        return self.qstart > other.qstart or (
            self.qspan == other.qspan and self.iresource == other.iresource
        )

    def lines(self, line_by_pos=None):
        if line_by_pos:
            self.set_lines(line_by_pos)
        return self.start_line, self.end_line

    def set_lines(self, line_by_pos):
        """
        Set this match start and end lines using a mapping of ``line_by_pos`` {pos: line}.
        """
        self.start_line = line_by_pos[self.qstart]
        self.end_line = line_by_pos[self.qend]

    @property
    def qstart(self):
        return self.qspan.start

    @property
    def qend(self):
        return self.qspan.end

    def len(self):
        """
        Return the length of the match as the number of matched query tokens.
        """
        return len(self.qspan)

    def __contains__(self, other):
        """
        Return True if qspan contains other.qspan and ispan contains other.ispan.
        """
        return other.qspan in self.qspan

    def qcontains(self, other):
        """
        Return True if qspan contains other.qspan.
        """
        return other.qspan in self.qspan

    def qdistance_to(self, other):
        """
        Return the absolute qspan distance to other match.
        Overlapping matches have a zero distance.
        Non-overlapping touching matches have a distance of one.
        """
        return self.qspan.distance_to(other.qspan)

    def overlap(self, other):
        """
        Return the number of overlapping positions with other.
        """
        return self.qspan.overlap(other.qspan)

    def combine(self, other):
        """Return a new match object combining self and an other match."""
        if self.iresource != other.iresource or self.ipackage != other.ipackage:
            raise TypeError(
                "Cannot combine matches with different ipackage-iresource combination: "
                f"from: {self!r}, to: {other!r}"
            )

        combined = ExtendedFileFragmentMatch(
            qresource=self.qresource,
            ipackage=self.ipackage,
            iresource=self.iresource,
            qspan=Span(self.qspan | other.qspan),
        )
        return combined

    def update(self, other):
        """Update self with other match and return the updated self in place."""
        combined = self.combine(other)
        self.qspan = combined.qspan
        return self
