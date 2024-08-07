MatchCode toolkit
=================
MatchCode toolkit is a Python library that provides the file and directory
fingerprinting functionality for `ScanCode toolkit
<https://github.com/aboutcode-org/scancode-toolkit>`_ and `ScanCode.io
<https://github.com/aboutcode-org/scancode.io>`_ by implementing the HaloHash algorithm
and using it in ScanCode toolkit and ScanCode.io plugins and pipelines.


Installation
------------

MatchCode toolkit must be installed in the same environment as ScanCode toolkit
or ScanCode.io.

From PyPI:
::

  pip install matchcode-toolkit

A checkout of this repo can also be installed into an environment using pip's
``--editable`` option,
::

  # Activate the virtual environment you want to install MatchCode-toolkit into,
  # change directories to the ``matchcode-toolkit`` directory
  pip install --editable .

or built into a wheel and then installed:
::

  python setup.py build bdist_wheel
  pip install matchcode_toolkit-*-py3-none-any.whl


Usage
-----

MatchCode toolkit provides the ``--fingerprint`` option for ScanCode toolkit.
This is a scan plugin that adds the fields
``directory_content_fingerprint``, ``directory_structure_fingerprint``, and
``halo1`` to Resources and computes those values.
::

  scancode --info --fingerprint <scan target location> --json-pp <output location>


MatchCode toolkit provides the ``scan_and_fingerprint_package`` and
``fingerprint_codebase`` pipelines for ScanCode.io.

These pipelines add resource and directory fingerprints to the ``extra_data`` field.


License
-------

SPDX-License-Identifier: Apache-2.0

The ScanCode.io software is licensed under the Apache License version 2.0.
Data generated with ScanCode.io is provided as-is without warranties.
ScanCode is a trademark of nexB Inc.

You may not use this software except in compliance with the License.
You may obtain a copy of the License at: http://apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.

Data Generated with ScanCode.io is provided on an "AS IS" BASIS, WITHOUT WARRANTIES
OR CONDITIONS OF ANY KIND, either express or implied. No content created from
ScanCode.io should be considered or used as legal advice. Consult an Attorney
for any legal advice.
