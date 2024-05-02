#
# Copyright (c) nexB Inc. and others. All rights reserved.
# purldb is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/nexB/purldb for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import copy
import os
import random
import subprocess

from commoncode.testcase import FileBasedTesting

from matchcode_toolkit.fingerprinting import create_content_hash


SIZE_IN_BITS = 128


class TestHalohash(FileBasedTesting):
    test_data_dir = os.path.join(os.path.dirname(__file__), 'testfiles/halohash')

    def test_halohash_deleted_lines(self, ngram_length=8):
        test_file1 = self.get_test_loc('index.js')
        with open(test_file1) as f:
            original_content = [line for line in f]
        original_content_str = ''.join(original_content)
        original_hash, number_of_ngrams_original = create_content_hash(
            original_content_str,
            ngram_length=ngram_length
        )
        print(original_hash.hexdigest())

        content_copy = copy.copy(original_content)
        idx = 0
        while True:
            content_copy_str = ''.join(content_copy)
            content_hash, number_of_ngrams_content = create_content_hash(
                content_copy_str,
                ngram_length=ngram_length
            )
            if not content_hash:
                print(content_copy_str)
                break
            hamming_distance = original_hash.distance(content_hash)
            fingerprint_similarity = ((SIZE_IN_BITS - hamming_distance) / SIZE_IN_BITS) * 100
            input_similarity = (number_of_ngrams_content / number_of_ngrams_original) * 100
            print(idx, fingerprint_similarity, input_similarity)
            content_copy.pop()
            idx += 1
        print(content_copy)

    def test_halohash_replaced_words(self, ngram_length=8):
        test_file1 = self.get_test_loc('index.js')
        original_content = []
        with open(test_file1) as f:
            for line in f.readlines():
                original_content.extend(line.split())
        original_content_str = ''.join(original_content)
        original_hash, number_of_ngrams_original = create_content_hash(
            original_content_str,
            ngram_length=ngram_length
        )

        content_copy = copy.copy(original_content)

        # Pick a word at random
        print('\nwords replaced\tfingerprint similarity (%)\tinput similarity (%)')
        for idx in range(len(content_copy)):
            content_copy_str = ''.join(content_copy)
            content_hash, number_of_ngrams_content = create_content_hash(
                content_copy_str,
                ngram_length=ngram_length
            )
            hamming_distance = original_hash.distance(content_hash)
            fingerprint_similarity = ((SIZE_IN_BITS - hamming_distance) / SIZE_IN_BITS) * 100
            input_similarity = (number_of_ngrams_content / number_of_ngrams_original) * 100
            print(f'{idx}\t{fingerprint_similarity:.2f}\t{input_similarity:.2f}')

            line_num = random.randint(0, len(content_copy) - 1)
            new_word = (
                subprocess.run(
                    ['shuf', '-n', '1', '/usr/share/dict/american-english'],
                    stdout=subprocess.PIPE
                )
                .stdout
                .decode('utf-8')
                .strip()
                .replace('"', '')
            )
            content_copy[line_num] = new_word
