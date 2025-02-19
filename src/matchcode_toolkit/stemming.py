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
        "comments": ["comment", "block_comment", "line_comment"],
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
        "comments": ["comment", "block_comment", "line_comment"],
    },
}


def get_parser(location):
    """
    Get the appropriate tree-sitter parser and grammar config for
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


def apply_mutation(text, start_point, end_point, replacement, successive_line_count):
    """Mutate tokens between start and end points with replacement string."""

    start_row, start_col = start_point
    end_row, end_col = end_point

    # Compute 1D mutation position from 2D coordinates
    start_index = successive_line_count[start_row] + start_col
    end_index = successive_line_count[end_row] + end_col

    modified_text = text[:start_index] + replacement + text[end_index:]
    modified_lines = modified_text.splitlines(keepends=True)

    # Remove empty comment lines.
    if not replacement and modified_lines[start_row].strip() == "":
        del modified_lines[start_row]

    return "".join(modified_lines)


def get_stem_code(location):
    """
    Return the stemmed code for the code file at the specified `location`.

    Parse the code using tree-sitter, create a mutation index for tokens that
    need to be replaced or removed, and apply these mutations bottom-up to
    generate the stemmed code.
    """
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
    cur_count = 0
    lines = text.splitlines(keepends=True)
    successive_line_count = [cur_count := cur_count + len(line) for line in lines]
    successive_line_count.insert(0, 0)

    for value in mutations.values():
        text = apply_mutation(
            text=text,
            end_point=value["end_point"],
            start_point=value["start_point"],
            replacement=("idf" if value["type"] == "identifier" else ""),
            successive_line_count=successive_line_count,
        )
    return text
