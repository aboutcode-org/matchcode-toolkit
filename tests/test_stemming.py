#
# Copyright (c) nexB Inc. and others. All rights reserved.
# ScanCode is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/matchcode-toolkit for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#


from pathlib import Path

from commoncode.testcase import FileBasedTesting

from matchcode_toolkit import stemming


def check_against_expected_code_file(results, expected_file, regen=True):
    """
    Check that the ``results`` data are the same as the data in the
    ``expected_file``.

    If `regen` is True the expected_file will overwritten with the ``results``.
    This is convenient for updating tests expectations. But use with caution.
    """
    if regen:
        with open(expected_file, "w") as reg:
            reg.write(results)
        expected = results
    else:
        with open(expected_file) as exp:
            expected = exp.read()

    assert results == expected


class TestFingerprintingFunctions(FileBasedTesting):
    test_data_dir = Path(__file__).parent / "testfiles/stemming"

    def test_java_code_stemming(self):
        file_location = self.test_data_dir / "java/contenttype.java"
        expected_file_location = self.test_data_dir / "java/contenttype-stemmed.java"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_cpp_code_stemming(self):
        file_location = self.test_data_dir / "cpp/string.cpp"
        expected_file_location = self.test_data_dir / "cpp/string-stemmed.cpp"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_c_code_stemming(self):
        file_location = self.test_data_dir / "c/main.c"
        expected_file_location = self.test_data_dir / "c/main-stemmed.c"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_golang_code_stemming(self):
        file_location = self.test_data_dir / "golang/utils.go"
        expected_file_location = self.test_data_dir / "golang/utils-stemmed.go"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_python_code_stemming(self):
        file_location = self.test_data_dir / "python/sync_scancode_scans.py"
        expected_file_location = self.test_data_dir / "python/sync_scancode_scans-stemmed.py"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_javascript_code_stemming(self):
        file_location = self.test_data_dir / "javascript/utils.js"
        expected_file_location = self.test_data_dir / "javascript/utils-stemmed.js"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)

    def test_rust_code_stemming(self):
        file_location = self.test_data_dir / "rust/metrics.rs"
        expected_file_location = self.test_data_dir / "rust/metrics-stemmeds.rs"
        results = stemming.get_stem_code(location=str(file_location))
        check_against_expected_code_file(results, expected_file_location)
