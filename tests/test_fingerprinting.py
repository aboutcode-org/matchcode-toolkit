#
# Copyright (c) nexB Inc. and others. All rights reserved.
# purldb is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/purldb for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import os
from collections import defaultdict

from commoncode.resource import VirtualCodebase
from commoncode.testcase import FileBasedTesting
from commoncode.testcase import check_against_expected_json_file

from matchcode_toolkit.fingerprinting import _create_directory_fingerprint
from matchcode_toolkit.fingerprinting import _get_resource_subpath
from matchcode_toolkit.fingerprinting import compute_codebase_directory_fingerprints
from matchcode_toolkit.fingerprinting import create_content_fingerprint
from matchcode_toolkit.fingerprinting import create_halohash_chunks
from matchcode_toolkit.fingerprinting import create_structure_fingerprint
from matchcode_toolkit.fingerprinting import get_file_fingerprint_hashes
from matchcode_toolkit.fingerprinting import split_fingerprint
from samecode.halohash import byte_hamming_distance


class Resource:
    def __init__(self, path="", size=0, sha1=""):
        self.path = path
        self.size = size
        self.sha1 = sha1


class TestFingerprintingFunctions(FileBasedTesting):
    test_data_dir = os.path.join(os.path.dirname(__file__), "testfiles/fingerprinting")

    def test__create_directory_fingerprint(self):
        test_input = [
            "package",
            "package/readme.txt",
            "package/index.js",
            "package/package.json",
        ]
        directory_fingerprint = _create_directory_fingerprint(test_input)
        expected_directory_fingerprint = "0000000410d24471969646cb5402032288493126"
        self.assertEqual(expected_directory_fingerprint, directory_fingerprint)
        indexed_elements_count, _ = split_fingerprint(directory_fingerprint)
        self.assertEqual(len(test_input), indexed_elements_count)

    def test_split_fingerprint(self):
        directory_fingerprint = "0000000410d24471969646cb5402032288493126"
        indexed_elements_count, bah128 = split_fingerprint(directory_fingerprint)

        expected_indexed_elements_count = 4
        self.assertEqual(expected_indexed_elements_count, indexed_elements_count)

        expected_bah128 = "10d24471969646cb5402032288493126"
        self.assertEqual(expected_bah128, bah128)

    def test_create_content_fingerprint(self):
        test_resources = [
            Resource(sha1="d4e4abbe8e2a8169d6a52907152c2c80ec884745"),
            Resource(sha1="0c94f137f6e0536db8cb2622a9dc84253b91b90c"),
            Resource(sha1="10cab45fe6f353b47b587a576c1077a96ce348f5"),
            Resource(sha1="134f2b052b6e5f56b631be2eded70f89d44cf381"),
        ]
        fingerprint = create_content_fingerprint(test_resources)
        expected_fingerprint = "00000004005b88c2800f0044044781ae05680419"
        self.assertEqual(expected_fingerprint, fingerprint)

    def test__get_resource_subpath(self):
        test_resource = Resource(path="foo/bar/baz/qux.c")
        test_top_resource = Resource(path="foo/bar/")
        subpath = _get_resource_subpath(test_resource, test_top_resource)
        expected_subpath = "baz/qux.c"
        self.assertEqual(expected_subpath, subpath)

    def test_create_structure_fingerprint(self):
        test_top_resource = Resource(path="package")
        test_child_resources = [
            Resource(path="package/readme.txt", size=771),
            Resource(path="package/index.js", size=608),
            Resource(path="package/package.json", size=677),
        ]
        fingerprint = create_structure_fingerprint(test_top_resource, test_child_resources)
        expected_fingerprint = "00000003ce72f4308a1bc1afb0fb47ed590b5c53"
        self.assertEqual(expected_fingerprint, fingerprint)

    def test_create_halohash_chunks(self):
        test_bah128 = "ce72f4308a1bc1afb0fb47ed590b5c53"
        chunk1, chunk2, chunk3, chunk4 = create_halohash_chunks(test_bah128)
        expected_chunk1 = bytearray(b"\xcer\xf40")
        expected_chunk2 = bytearray(b"\x8a\x1b\xc1\xaf")
        expected_chunk3 = bytearray(b"\xb0\xfbG\xed")
        expected_chunk4 = bytearray(b"Y\x0b\\S")
        self.assertEqual(chunk1, expected_chunk1)
        self.assertEqual(chunk2, expected_chunk2)
        self.assertEqual(chunk3, expected_chunk3)
        self.assertEqual(chunk4, expected_chunk4)

    def test_compute_codebase_directory_fingerprints(self):
        scan_loc = self.get_test_loc("directory_fingerprinting/abbrev-1.0.3-i.json")
        vc = VirtualCodebase(location=scan_loc)
        vc = compute_codebase_directory_fingerprints(vc)
        directory_content = vc.root.extra_data["directory_content"]
        directory_structure = vc.root.extra_data["directory_structure"]
        expected_directory_content = "0000000346ce04751a3c98f00086f16a91d9790b"
        expected_directory_structure = "000000034f9bf110673bdf06197cd514a799a66c"
        self.assertEqual(expected_directory_content, directory_content)
        self.assertEqual(expected_directory_structure, directory_structure)

    def test_do_not_compute_fingerprint_for_empty_dirs(self):
        scan_loc = self.get_test_loc("directory_fingerprinting/test.json")
        vc = VirtualCodebase(location=scan_loc)
        vc = compute_codebase_directory_fingerprints(vc)
        directory_content = vc.root.extra_data["directory_content"]
        directory_structure = vc.root.extra_data["directory_structure"]
        expected_directory_content = "000000032a5fa8d01922536b53e8fc6e3d43766f"
        expected_directory_structure = "000000030a399ce2b947a6f611821965a4fcc577"
        self.assertEqual(expected_directory_content, directory_content)
        self.assertEqual(expected_directory_structure, directory_structure)
        # These directories should not have fingerprints generated or stored in
        # extra_data
        empty_dir_1 = vc.get_resource("test/test")
        empty_dir_2 = vc.get_resource("test/test/test2")
        self.assertEqual({}, empty_dir_1.extra_data)
        self.assertEqual({}, empty_dir_1.extra_data)
        self.assertEqual({}, empty_dir_2.extra_data)
        self.assertEqual({}, empty_dir_2.extra_data)

    def test_get_file_fingerprint_hashes_one_line_removed(self):
        test_file1 = self.get_test_loc("directory_fingerprinting/inflate.c")
        test_file2 = self.get_test_loc("directory_fingerprinting/inflate-mod.c")
        result1 = get_file_fingerprint_hashes(test_file1)
        result2 = get_file_fingerprint_hashes(test_file2)
        result1 = result1.get("halo1")
        result2 = result2.get("halo1")
        result1_indexed_elements_count, result1_fingerprint = split_fingerprint(result1)
        result2_indexed_elements_count, result2_fingerprint = split_fingerprint(result2)

        expected_result1_indexed_elements_count = 6398
        expected_result2_indexed_elements_count = 6391
        self.assertEqual(expected_result1_indexed_elements_count, result1_indexed_elements_count)
        self.assertEqual(expected_result2_indexed_elements_count, result2_indexed_elements_count)

        expected_result1_fingerprint = "dc025ae7ebb104419e5314c665a08919"
        expected_result2_fingerprint = "dc025ae7ebb104419e5354c665a0891d"
        self.assertEqual(expected_result1_fingerprint, result1_fingerprint)
        self.assertEqual(expected_result2_fingerprint, result2_fingerprint)

        self.assertEqual(2, byte_hamming_distance(result1_fingerprint, result2_fingerprint))

    def test_get_file_fingerprint_hashes_one_line_added(self):
        test_file1 = self.get_test_loc("directory_fingerprinting/inflate.c")
        test_file2 = self.get_test_loc("directory_fingerprinting/inflate-mod2.c")
        result1 = get_file_fingerprint_hashes(test_file1)
        result2 = get_file_fingerprint_hashes(test_file2)
        result1 = result1.get("halo1")
        result2 = result2.get("halo1")
        result1_indexed_elements_count, result1_fingerprint = split_fingerprint(result1)
        result2_indexed_elements_count, result2_fingerprint = split_fingerprint(result2)

        expected_result1_indexed_elements_count = 6398
        expected_result2_indexed_elements_count = 6401
        self.assertEqual(expected_result1_indexed_elements_count, result1_indexed_elements_count)
        self.assertEqual(expected_result2_indexed_elements_count, result2_indexed_elements_count)

        expected_result1_fingerprint = "dc025ae7ebb104419e5314c665a08919"
        expected_result2_fingerprint = "dc025ae7ebb104419e5314c665a1891d"
        self.assertEqual(expected_result1_fingerprint, result1_fingerprint)
        self.assertEqual(expected_result2_fingerprint, result2_fingerprint)

        self.assertEqual(2, byte_hamming_distance(result1_fingerprint, result2_fingerprint))

    @classmethod
    def _create_snippet_mappings_by_snippets(cls, snippets):
        snippet_mappings_by_snippet = defaultdict(list)
        for s in snippets:
            snippet = s["snippet"]
            snippet_mappings_by_snippet[snippet].append(s)
        return snippet_mappings_by_snippet

    def test_snippets_similarity(self, regen=False):
        # 1 function from adler32.c has been added to zutil.c
        test_file1 = self.get_test_loc("snippets/adler32.c")
        test_file2 = self.get_test_loc("snippets/zutil.c")
        results1 = get_file_fingerprint_hashes(test_file1, include_ngrams=True)
        results2 = get_file_fingerprint_hashes(test_file2, include_ngrams=True)
        results1_snippets = results1.get("snippets")
        results2_snippets = results2.get("snippets")

        results1_snippet_mappings_by_snippets = self._create_snippet_mappings_by_snippets(
            results1_snippets
        )
        results2_snippet_mappings_by_snippets = self._create_snippet_mappings_by_snippets(results2_snippets)

        matching_snippets = (
            results1_snippet_mappings_by_snippets.keys() & results2_snippet_mappings_by_snippets.keys()
        )
        expected_matching_snippets = {
            "33b1d50de7e1701bd4beb706bf25970e",
            "0dcb44bfa9a7c7e310ea9d4a921b777b",
            "9bc102ceddabba9c1dc31140500e6c6c",
            "310e6e530d4bda6977774b34515101ab",
            "cd50d59e9cd0df93ef6b8dfbf0f7d311",
            "5af889295c942ecb75189c86df62e201",
            "0057152e3b1795b6befd36a4412c21a5",
            "c09e0b1020b5265ccac6d03439dff2dc",
            "ecbedbeebd47e4a24210bfb8419c9f8e",
            "3c866b47965d9cc62c4640e3ae132d2b",
            "2b74fe7dde58dfa20bf75a6b4e589a10",
            "07a7b1300fb58b5f9b9b3e56df23e003",
            "72a86996522cfb9f83cf388d8010b7ab",
            "d45f0d54c32b2d884919665c65c65638",
            "cac65171e0f01c57e1af7a5b99929d12",
            "8571422ee6dec38705bcdb8c12496473",
            "b9db06731d27c61a56600e74d145e814",
            "ba34fbe4e05f3f28641958ecc5eb9af9",
            "de43d78e467331cc3bcbf87fdb3c90c3",
        }
        self.assertEqual(expected_matching_snippets, matching_snippets)

        results = []
        for snippet in sorted(matching_snippets):
            sorted_results1 = results1_snippet_mappings_by_snippets[snippet]
            sorted_results2 = results2_snippet_mappings_by_snippets[snippet]
            results.append(
                {
                    "snippet": snippet,
                    "snippet_matched_to_results1": sorted_results1,
                    "snippet_matched_to_results2": sorted_results2,
                }
            )
        expected_results_loc = self.get_test_loc("snippet-similarity-expected.json")
        check_against_expected_json_file(results, expected_results_loc, regen=regen)

    def test_snippets_similarity_2(self, regen=False):
        # index-modified.js is index.js with a function removed
        test_file1 = self.get_test_loc("snippets/index.js")
        test_file2 = self.get_test_loc("snippets/index-modified.js")
        results1 = get_file_fingerprint_hashes(test_file1, include_ngrams=True)
        results2 = get_file_fingerprint_hashes(test_file2, include_ngrams=True)
        results1_snippets = results1.get("snippets")
        results2_snippets = results2.get("snippets")

        results1_snippet_mappings_by_snippets = self._create_snippet_mappings_by_snippets(
            results1_snippets
        )
        results2_snippet_mappings_by_snippets = self._create_snippet_mappings_by_snippets(results2_snippets)

        matching_snippets = (
            results1_snippet_mappings_by_snippets.keys() & results2_snippet_mappings_by_snippets.keys()
        )

        # jaccard coefficient
        jc = len(matching_snippets) / ((len(results1_snippets) + len(results2_snippets)) / 2)

        assert jc == 0.9666666666666667
        assert len(results1_snippets) == 61
        assert len(results2_snippets) == 59
        assert len(matching_snippets) == 58
