#
# Copyright (c) nexB Inc. and others. All rights reserved.
# ScanCode is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/scancode-toolkit for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import os

from commoncode.testcase import FileBasedTesting
from scancode.cli_test_utils import check_json_scan
from scancode.cli_test_utils import run_scan_click
from scancode_config import REGEN_TEST_FIXTURES

from matchcode_toolkit.fingerprinting import get_file_fingerprint_hashes

"""
These tests spawn new process as if launched from the command line.
"""


class TestPluginFingerprinting(FileBasedTesting):
    test_data_dir = os.path.join(os.path.dirname(__file__), "testfiles")

    def test_plugin_fingerprinting_api_works(self):
        test_loc = self.get_test_loc("fingerprinting/inflate.c")
        detections = list(get_file_fingerprint_hashes(location=test_loc))
        assert detections

    def test_fingerprinting_plugin_works(self):
        test_dir = self.get_test_loc("fingerprinting", copy=True)
        result_file = self.get_temp_file("json")
        args = [
            "--info",
            "--fingerprint",
            "--verbose",
            "--json",
            result_file,
            test_dir,
        ]
        run_scan_click(args)
        test_loc = self.get_test_loc("fingerprinting-expected.json")
        check_json_scan(
            test_loc,
            result_file,
            remove_file_date=True,
            check_headers=False,
            regen=REGEN_TEST_FIXTURES,
        )
