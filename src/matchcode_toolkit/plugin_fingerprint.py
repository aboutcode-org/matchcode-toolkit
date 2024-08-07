#
# Copyright (c) nexB Inc. and others. All rights reserved.
# ScanCode is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/scancode-toolkit for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import attr

from commoncode.cliutils import PluggableCommandLineOption
from commoncode.cliutils import SCAN_GROUP
from matchcode_toolkit.fingerprinting import compute_codebase_directory_fingerprints
from matchcode_toolkit.fingerprinting import get_file_fingerprint_hashes
from plugincode.scan import ScanPlugin
from plugincode.scan import scan_impl


@scan_impl
class FingerprintScanner(ScanPlugin):
    resource_attributes = dict(
        directory_content_fingerprint=attr.ib(default=None, repr=False),
        directory_structure_fingerprint=attr.ib(default=None, repr=False),
        halo1=attr.ib(default=None, repr=False),
    )
    sort_order = 6
    options = [
        PluggableCommandLineOption(
            (
                '--fingerprint',
            ),
            is_flag=True,
            default=False,
            help='Compute directory and resource fingerprints that are used for matching',
            help_group=SCAN_GROUP,
            sort_order=20,
        )
    ]

    def is_enabled(self, fingerprint, **kwargs):
        return fingerprint

    def get_scanner(self, **kwargs):
        return get_file_fingerprint_hashes

    def process_codebase(self, codebase, **kwargs):
        codebase = compute_codebase_directory_fingerprints(codebase)
