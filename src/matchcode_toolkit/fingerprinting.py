#
# Copyright (c) nexB Inc. and others. All rights reserved.
# purldb is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/purldb for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import binascii
import re

from samecode.halohash import BitAverageHaloHash
from licensedcode.tokenize import query_lines


# A collection of directory fingerprints that we want to avoid
IGNORED_DIRECTORY_FINGERPRINTS = [
    # This is both the directory content and directory structure fingerprint for
    # an empty directory.
    "0000000000000000000000000000000000000000",
]


def _create_directory_fingerprint(inputs):
    """
    Return a 128-bit BitAverageHaloHash fingerprint in hex from `inputs`
    """
    inputs = [i.encode("utf-8") for i in inputs if i]
    bah128 = BitAverageHaloHash(inputs, size_in_bits=128).hexdigest()
    inputs_count = len(inputs)
    inputs_count_hex_str = "%08x" % inputs_count
    bah128 = bah128.decode("utf-8")
    directory_fingerprint = inputs_count_hex_str + bah128
    return directory_fingerprint


def create_content_fingerprint(resources):
    """
    Collect SHA1 strings from a list of Resources (`resources`) and create a
    directory fingerprint from them
    """
    features = [r.sha1 for r in resources if r.sha1]
    return _create_directory_fingerprint(features)


def _get_resource_subpath(resource, top):
    """
    Return the subpath of `resource` relative to `top` from `codebase`

    For example:

    top.path = 'foo/bar/'
    resource.path = 'foo/bar/baz.c'

    The subpath returned would be 'baz.c'
    """
    _, _, subpath = resource.path.partition(top.path)
    subpath = subpath.lstrip("/")
    return subpath


def create_structure_fingerprint(directory, children):
    """
    Collect the subpaths of children Resources of Resource `directory` and
    create a fingerprint from them
    """
    features = []
    for child in children:
        if not child.path:
            continue
        child_subpath = _get_resource_subpath(child, directory)
        if not child.size:
            rounded_child_size = 0
        else:
            rounded_child_size = int(child.size / 10) * 10
        path_feature = str(rounded_child_size) + child_subpath
        features.append(path_feature)
    return _create_directory_fingerprint(features)


def _compute_directory_fingerprints(directory, codebase):
    """
    Compute fingerprints for `directory` from `codebase`
    """
    # We do not want to add empty files to our fingerprint
    children = [r for r in directory.walk(codebase) if r.is_file and r.size]
    if len(children) <= 1:
        return

    directory_content_fingerprint = create_content_fingerprint(children)
    if hasattr(directory, "directory_content_fingerprint"):
        directory.directory_content_fingerprint = directory_content_fingerprint
    else:
        directory.extra_data["directory_content"] = directory_content_fingerprint

    directory_structure_fingerprint = create_structure_fingerprint(directory, children)
    if hasattr(directory, "directory_structure_fingerprint"):
        directory.directory_structure_fingerprint = directory_structure_fingerprint
    else:
        directory.extra_data["directory_structure"] = directory_structure_fingerprint

    directory.save(codebase)
    return directory


def compute_directory_fingerprints(directory, codebase):
    """
    Recursivly compute fingerprints for `directory` from `codebase`
    """
    for resource in directory.walk(codebase, topdown=False):
        if resource.is_file:
            continue
        _ = _compute_directory_fingerprints(resource, codebase)
    return directory


def compute_codebase_directory_fingerprints(codebase):
    """
    Compute fingerprints for directories from `codebase`
    """
    for resource in codebase.walk(topdown=False):
        if resource.is_file or not resource.path:
            continue
        _ = _compute_directory_fingerprints(resource, codebase)
    return codebase


def split_fingerprint(fingerprint):
    """
    Given a string `fingerprint`, return the indexed elements count as an
    integer and the bah128 fingerprint string
    """
    indexed_elements_count_hash = fingerprint[0:8]
    indexed_elements_count = int(indexed_elements_count_hash, 16)
    bah128 = fingerprint[8:]
    return indexed_elements_count, bah128


def hexstring_to_binarray(hex_string):
    """
    Convert a hex string to binary form, then store in a bytearray
    """
    return bytearray(binascii.unhexlify(hex_string))


def create_halohash_chunks(bah128):
    """
    Given a 128-bit bah128 hash string, split it into 4 chunks and return those
    chunks as bytearrays
    """
    chunk1 = bah128[0:8]
    chunk2 = bah128[8:16]
    chunk3 = bah128[16:24]
    chunk4 = bah128[24:32]

    chunk1 = hexstring_to_binarray(chunk1)
    chunk2 = hexstring_to_binarray(chunk2)
    chunk3 = hexstring_to_binarray(chunk3)
    chunk4 = hexstring_to_binarray(chunk4)

    return chunk1, chunk2, chunk3, chunk4


# Split on whitespace and punctuations: keep only characters and numbers
query_pattern = "[^_\\W]+"
word_splitter = re.compile(query_pattern, re.UNICODE).findall

# TODO: return line numbers from where the token was taken
def _tokenizer(text):
    """
    Return an list of tokens from a unicode text.
    """
    if not text:
        return []
    return [token for token in word_splitter(text) if token]


def tokenizer(text):
    """
    Return an list of tokens from a unicode text.

    For example::
    >>> list(tokenizer(''))
    []
    >>> x = list(tokenizer('some Text with   spAces! + _ -'))
    >>> assert x == ['some', 'text', 'with', 'spaces']

    >>> x = list(tokenizer('{{}some }}Text with   spAces! + _ -'))
    >>> assert x == ['some', 'text', 'with', 'spaces']

    >>> x = list(tokenizer('{{Hi}}some {{}}Text with{{noth+-_!@ing}}   {{junk}}spAces! + _ -{{}}'))
    >>> assert x == ['hi', 'some', 'text', 'with', 'noth', 'ing', 'junk', 'spaces']

    """
    return _tokenizer(text.lower())


def get_file_fingerprint_hashes(
    location, ngram_length=5, window_length=16, include_ngrams=False, **kwargs
):
    """
    Return a mapping of fingerprint hashes for the file at `location`

    The `halo1` hash is the hex digest of the fingerprint of the file.
    `halo1` is empty if the file is empty.

    - We start by breaking the file into words (tokens)
    - We compute ngrams over the list of tokens

    Return an empty mapping if `location` is not a text file
    """
    from commoncode import filetype
    from typecode.contenttype import get_type

    # Do not process `location` if it's not a text file
    ft = get_type(location)
    if not (filetype.is_file(location) and ft.is_text):
        return {}

    with open(location) as f:
        content = f.read()

    return create_file_fingerprints(
        content,
        ngram_length=ngram_length,
        window_length=window_length,
        include_ngrams=include_ngrams,
    )


def create_file_fingerprints(content, ngram_length=5, window_length=16, include_ngrams=False):
    """
    Return a mapping of halo1 and snippet hashes from content string
    """
    from licensedcode.tokenize import ngrams
    from licensedcode.tokenize import select_ngrams

    fingerprints = {
        "halo1": "",
        "snippets": [],
    }

    # tokenize content into words
    words = list(tokenizer(content))

    # Create a file fingerprint from the number of elements in the content hash
    # and the content hash digest iteself.
    ngs = ngrams(words, ngram_length)
    # TODO: consider using itertools.chain.from_iterable()
    ngs_bytes = [[g.encode("utf-8") for g in ng] for ng in ngs]
    ngs_bytes = [b"".join(ng) for ng in ngs_bytes]
    content_hash, ngs_count = BitAverageHaloHash(ngs_bytes), len(ngs_bytes)
    if content_hash:
        content_fingerprint = content_hash.hexdigest().decode("utf-8")
        ngs_count_hex_str = "%08x" % ngs_count
        file_fingerprint = ngs_count_hex_str + content_fingerprint
        fingerprints["halo1"] = file_fingerprint

    # Select windows from the content to compute snippet fingerprints
    windows = ngrams(words, window_length)
    selected_windows = list(select_ngrams(windows, with_pos=True))
    # TODO: consider using itertools.chain.from_iterable()
    selected_windows_bytes = [
        (int(pos), [g.encode("utf-8") for g in window]) for pos, window in selected_windows
    ]
    selected_windows_bytes = [(pos, b"".join(window)) for pos, window in selected_windows_bytes]
    snippets = []
    for (pos, window_bytes), (_, window) in zip(selected_windows_bytes, selected_windows):
        s = {
            "qstart": pos,
            "qend": pos + window_length - 1,
            "snippet": BitAverageHaloHash(window_bytes).hexdigest().decode("utf-8"),
        }
        if include_ngrams:
            s["ngrams"] = list(window)
        snippets.append(s)
    if snippets:
        fingerprints["snippets"] = snippets

    return fingerprints


def get_line_by_pos(content):
    """
    Return a list of lines numbers whose indices correspond to a token position
    in `content`.

    For example, given line_by_pos[0] = 1, this means that the token at position
    0 in `content` is on line 1.
    """
    line_number_and_lines = query_lines(query_string=content)
    line_by_pos = []
    pos = 0
    for line_number, line in line_number_and_lines:
        tokens = tokenizer(line)
        for _ in tokens:
            line_by_pos[pos] = line_number
            pos += 1
    return line_by_pos
