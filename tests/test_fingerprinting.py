#
# Copyright (c) nexB Inc. and others. All rights reserved.
# purldb is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/purldb for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import os

from commoncode.resource import VirtualCodebase
from commoncode.testcase import FileBasedTesting

from matchcode_toolkit.fingerprinting import _create_directory_fingerprint
from matchcode_toolkit.fingerprinting import _get_resource_subpath
from matchcode_toolkit.fingerprinting import compute_codebase_directory_fingerprints
from matchcode_toolkit.fingerprinting import create_content_fingerprint
from matchcode_toolkit.fingerprinting import create_halohash_chunks
from matchcode_toolkit.fingerprinting import create_structure_fingerprint
from matchcode_toolkit.fingerprinting import get_file_fingerprint_hashes
from matchcode_toolkit.fingerprinting import split_fingerprint
from matchcode_toolkit.halohash import byte_hamming_distance


class Resource():
    def __init__(self, path='', size=0, sha1=''):
        self.path = path
        self.size = size
        self.sha1 = sha1


class TestFingerprintingFunctions(FileBasedTesting):
    test_data_dir = os.path.join(os.path.dirname(
        __file__), 'testfiles/fingerprinting')

    def test__create_directory_fingerprint(self):
        test_input = [
            'package',
            'package/readme.txt',
            'package/index.js',
            'package/package.json',
        ]
        directory_fingerprint = _create_directory_fingerprint(test_input)
        expected_directory_fingerprint = '0000000410d24471969646cb5402032288493126'
        self.assertEqual(expected_directory_fingerprint, directory_fingerprint)
        indexed_elements_count, _ = split_fingerprint(directory_fingerprint)
        self.assertEqual(len(test_input), indexed_elements_count)

    def test_split_fingerprint(self):
        directory_fingerprint = '0000000410d24471969646cb5402032288493126'
        indexed_elements_count, bah128 = split_fingerprint(
            directory_fingerprint)

        expected_indexed_elements_count = 4
        self.assertEqual(expected_indexed_elements_count,
                         indexed_elements_count)

        expected_bah128 = '10d24471969646cb5402032288493126'
        self.assertEqual(expected_bah128, bah128)

    def test_create_content_fingerprint(self):
        test_resources = [
            Resource(sha1='d4e4abbe8e2a8169d6a52907152c2c80ec884745'),
            Resource(sha1='0c94f137f6e0536db8cb2622a9dc84253b91b90c'),
            Resource(sha1='10cab45fe6f353b47b587a576c1077a96ce348f5'),
            Resource(sha1='134f2b052b6e5f56b631be2eded70f89d44cf381'),
        ]
        fingerprint = create_content_fingerprint(test_resources)
        expected_fingerprint = '00000004005b88c2800f0044044781ae05680419'
        self.assertEqual(expected_fingerprint, fingerprint)

    def test__get_resource_subpath(self):
        test_resource = Resource(path='foo/bar/baz/qux.c')
        test_top_resource = Resource(path='foo/bar/')
        subpath = _get_resource_subpath(test_resource, test_top_resource)
        expected_subpath = 'baz/qux.c'
        self.assertEqual(expected_subpath, subpath)

    def test_create_structure_fingerprint(self):
        test_top_resource = Resource(path='package')
        test_child_resources = [
            Resource(path='package/readme.txt', size=771),
            Resource(path='package/index.js', size=608),
            Resource(path='package/package.json', size=677),
        ]
        fingerprint = create_structure_fingerprint(
            test_top_resource, test_child_resources)
        expected_fingerprint = '00000003ce72f4308a1bc1afb0fb47ed590b5c53'
        self.assertEqual(expected_fingerprint, fingerprint)

    def test_create_halohash_chunks(self):
        test_bah128 = 'ce72f4308a1bc1afb0fb47ed590b5c53'
        chunk1, chunk2, chunk3, chunk4 = create_halohash_chunks(test_bah128)
        expected_chunk1 = bytearray(b'\xcer\xf40')
        expected_chunk2 = bytearray(b'\x8a\x1b\xc1\xaf')
        expected_chunk3 = bytearray(b'\xb0\xfbG\xed')
        expected_chunk4 = bytearray(b'Y\x0b\\S')
        self.assertEqual(chunk1, expected_chunk1)
        self.assertEqual(chunk2, expected_chunk2)
        self.assertEqual(chunk3, expected_chunk3)
        self.assertEqual(chunk4, expected_chunk4)

    def test_compute_codebase_directory_fingerprints(self):
        scan_loc = self.get_test_loc('abbrev-1.0.3-i.json')
        vc = VirtualCodebase(location=scan_loc)
        vc = compute_codebase_directory_fingerprints(vc)
        directory_content = vc.root.extra_data['directory_content']
        directory_structure = vc.root.extra_data['directory_structure']
        expected_directory_content = '0000000346ce04751a3c98f00086f16a91d9790b'
        expected_directory_structure = '000000034f9bf110673bdf06197cd514a799a66c'
        self.assertEqual(expected_directory_content, directory_content)
        self.assertEqual(expected_directory_structure, directory_structure)

    def test_do_not_compute_fingerprint_for_empty_dirs(self):
        scan_loc = self.get_test_loc('test.json')
        vc = VirtualCodebase(location=scan_loc)
        vc = compute_codebase_directory_fingerprints(vc)
        directory_content = vc.root.extra_data['directory_content']
        directory_structure = vc.root.extra_data['directory_structure']
        expected_directory_content = '000000032a5fa8d01922536b53e8fc6e3d43766f'
        expected_directory_structure = '000000030a399ce2b947a6f611821965a4fcc577'
        self.assertEqual(expected_directory_content, directory_content)
        self.assertEqual(expected_directory_structure, directory_structure)
        # These directories should not have fingerprints generated or stored in
        # extra_data
        empty_dir_1 = vc.get_resource('test/test')
        empty_dir_2 = vc.get_resource('test/test/test2')
        self.assertEqual({}, empty_dir_1.extra_data)
        self.assertEqual({}, empty_dir_1.extra_data)
        self.assertEqual({}, empty_dir_2.extra_data)
        self.assertEqual({}, empty_dir_2.extra_data)

    def test_get_file_fingerprint_hashes_one_line_removed(self):
        test_file1 = self.get_test_loc('inflate.c')
        test_file2 = self.get_test_loc('inflate-mod.c')
        result1 = get_file_fingerprint_hashes(test_file1)
        result2 = get_file_fingerprint_hashes(test_file2)
        result1 = result1.get('halo1')
        result2 = result2.get('halo1')
        result1_indexed_elements_count, result1_fingerprint = split_fingerprint(
            result1)
        result2_indexed_elements_count, result2_fingerprint = split_fingerprint(
            result2)

        expected_result1_indexed_elements_count = 6395
        expected_result2_indexed_elements_count = 6388
        assert result1_indexed_elements_count == expected_result1_indexed_elements_count
        assert result2_indexed_elements_count == expected_result2_indexed_elements_count

        expected_result1_fingerprint = 'a23a49e4cd40718d1297be719e6564a4'
        expected_result2_fingerprint = 'aa3a49e4cd40718d1297be519e6564a4'
        assert result1_fingerprint == expected_result1_fingerprint
        assert result2_fingerprint == expected_result2_fingerprint

        assert byte_hamming_distance(
            result1_fingerprint, result2_fingerprint) == 2

    def test_get_file_fingerprint_hashes_one_line_added(self):
        test_file1 = self.get_test_loc('inflate.c')
        test_file2 = self.get_test_loc('inflate-mod2.c')
        result1 = get_file_fingerprint_hashes(test_file1)
        result2 = get_file_fingerprint_hashes(test_file2)
        result1 = result1.get('halo1')
        result2 = result2.get('halo1')
        result1_indexed_elements_count, result1_fingerprint = split_fingerprint(
            result1)
        result2_indexed_elements_count, result2_fingerprint = split_fingerprint(
            result2)

        expected_result1_indexed_elements_count = 6395
        expected_result2_indexed_elements_count = 6398
        assert result1_indexed_elements_count == expected_result1_indexed_elements_count
        assert result2_indexed_elements_count == expected_result2_indexed_elements_count

        expected_result1_fingerprint = 'a23a49e4cd40718d1297be719e6564a4'
        expected_result2_fingerprint = 'a23b49e4cd40708d1297be719c6564a4'
        assert result1_fingerprint == expected_result1_fingerprint
        assert result2_fingerprint == expected_result2_fingerprint

        assert byte_hamming_distance(
            result1_fingerprint, result2_fingerprint) == 3
