#
# Copyright (c) nexB Inc. and others. All rights reserved.
# ScanCode is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/aboutcode-org/matchcode-toolkit for support or download.
# See https://aboutcode.org for more information about nexB OSS projects.
#

import importlib

from tree_sitter import Language
from tree_sitter import Parser
from typecode.contenttype import Type


class TreeSitterWheelNotInstalled(Exception):
    pass


TS_LANGUAGE_CONF = {
    "Bash": {
        "wheel": "tree_sitter_bash",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "C": {
        "wheel": "tree_sitter_c",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "C++": {
        "wheel": "tree_sitter_cpp",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "Go": {
        "wheel": "tree_sitter_go",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "Java": {
        "wheel": "tree_sitter_java",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "JavaScript": {
        "wheel": "tree_sitter_javascript",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "Python": {
        "wheel": "tree_sitter_python",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
    "Rust": {
        "wheel": "tree_sitter_rust",
        "identifiers": ["identifier"],
        "comments": ["comment"],
    },
}


def get_parser(location):
    """
    Get the appropriate tree-sitter parser and string identifier for
    file at location.
    """
    file_type = Type(location)
    language = file_type.programming_language

    if not language or language not in TS_LANGUAGE_CONF:
        return

    language_info = TS_LANGUAGE_CONF[language]
    wheel = language_info["wheel"]

    try:
        grammar = importlib.import_module(wheel)
    except ModuleNotFoundError:
        raise TreeSitterWheelNotInstalled(f"{wheel} package is not installed")

    parser = Parser(language=Language(grammar.language()))

    return parser, language_info


def add_to_mutation_index(node, mutation_index):
    if content := node.text.decode():
        end_point = node.end_point
        start_point = node.start_point
        mutation_index[(end_point.row, end_point.column)] = {
            "type": node.type,
            "content": content,
            "start_point": (start_point.row, start_point.column),
            "end_point": (end_point.row, end_point.column),
        }


def traverse(node, language_info, mutation_index):
    """
    Recursively traverse the parse tree node and create mutation index.

    Mutation index contains the start, end coordinates and where mutations
    is to be applied, along with the type of mutation. Each mutation entry
    is keyed by a tuple containing the end coordinates.
    """
    if node.type in language_info.get("identifiers") or node.type in language_info.get("comments"):
        add_to_mutation_index(node=node, mutation_index=mutation_index)

    for child in node.children:
        traverse(child, language_info, mutation_index)


def apply_mutation(text, start_point, end_point, replacement):
    start_row, start_col = start_point
    end_row, end_col = end_point

    lines = text.splitlines()

    # Compute the start and end indices, +1 for newline.
    start_index = sum(len(line) + 1 for line in lines[:start_row]) + start_col
    end_index = sum(len(line) + 1 for line in lines[:end_row]) + end_col

    return text[:start_index] + replacement + text[end_index:]


def get_stem_code(location):
    parser_result = get_parser(location)
    if not parser_result:
        return

    with open(location, "rb") as f:
        source = f.read()
    mutations = {}
    parser, language_info = parser_result
    tree = parser.parse(source)
    traverse(tree.root_node, language_info, mutations)

    # Apply mutations bottom-up
    mutations = dict(sorted(mutations.items(), reverse=True))
    text = source.decode()
    for value in mutations.values():
        text = apply_mutation(
            text=text,
            end_point=value["end_point"],
            start_point=value["start_point"],
            replacement=("idf" if value["type"] == "identifier" else ""),
        )
    return text
