#
# Copyright (c) nexB Inc. and others. All rights reserved.
# FederatedCode is a trademark of nexB Inc.
# SPDX-License-Identifier: Apache-2.0
# See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
# See https://github.com/nexB/federatedcode for support or download.
# See https://aboutcode.org for more information about AboutCode.org OSS projects.
#

from pathlib import Path
from traceback import format_exc as traceback_format_exc

from aboutcode.pipeline import LoopProgress
from fedcode.models import Package
from fedcode.models import Repository
from fedcode.pipelines import FederatedCodePipeline
from fedcode.pipes import utils


class SyncScanCodeScans(FederatedCodePipeline):
    """Sync Package scans from FederatedCode git repositories."""

    pipeline_id = "sync_scancode_scans"

    @classmethod
    def steps(cls):
        return (
            cls.get_git_repos,
            cls.sync_scan_repositories,
        )

    def get_git_repos(self):
        self.git_repos = Repository.objects.all()

    def sync_scan_repositories(self):
        repositories_count = self.git_repos.count()
        self.log(f"Syncing package scans from {repositories_count:,d} repositories")

        synced_package_scan_count = 0
        progress = LoopProgress(total_iterations=repositories_count, logger=self.log)
        for repository in progress.iter(self.git_repos.iterator(chunk_size=2000)):
            repository.git_repo_obj.remotes.origin.pull()
            synced_package_scan_count += sync_scancodeio_scan(
                repository=repository,
                logger=self.log,
            )

        self.log(f"Successfully synced {synced_package_scan_count:,d} package scans")


def sync_scancodeio_scan(repository, logger):
    repo = repository.git_repo_obj
    latest_commit_hash = repo.head.commit.hexsha
    latest_commit = repo.commit(latest_commit_hash)

    if last_commit_hash := repository.last_imported_commit:
        last_imported_commit = repo.commit(last_commit_hash)
        diffs = last_imported_commit.diff(latest_commit)
        scans = [item for item in diffs if item.a_path.endswith("scancodeio.json")]
        scan_count = sync_scan_from_diff(diffs=scans, repository=repository, logger=logger)
    else:
        scan_count = sync_all_scan(repository=repository, logger=logger)

    repository.last_imported_commit = latest_commit_hash
    repository.save()

    return scan_count


def sync_scan_from_diff(diffs, repository, logger):
    scans = [
        item
        for item in diffs
        if item.a_path.endswith("scancodeio.json") or item.b_path.endswith("scancodeio.json")
    ]
    scan_count = len(scans)

    logger(f"Syncing {scan_count:,d} package scan from {repository.url}")
    progress = LoopProgress(total_iterations=scan_count, logger=logger)
    for scan in progress.iter(scans):
        change_type = scan.change_type
        if change_type in ("A", "M", "R"):
            scan_path = scan.b_path
            action = utils.create_note
        elif change_type == "D":
            scan_path = scan.a_path
            action = utils.delete_note

        purl = utils.package_metadata_path_to_purl(path=Path(scan_path), version=False)
        package, _ = Package.objects.get_or_create(purl=str(purl), service=repository.admin)
        note = utils.get_scan_note(path=Path(scan_path))
        action(pkg=package, note_dict=note)
    return scan_count


def sync_all_scan(repository, logger):
    repo = repository.git_repo_obj
    root = Path(repo.working_dir)
    scan_count = sum(1 for _ in root.rglob("scancodeio.json"))

    scans = root.rglob("scancodeio.json")
    logger(f"Syncing {scan_count:,d} package scan from {repo.remotes.origin.url}")

    progress = LoopProgress(total_iterations=scan_count, logger=logger)
    for scan in progress.iter(scans):
        relative_path = scan.relative_to(root)
        purl = utils.package_metadata_path_to_purl(relative_path, version=False)
        package, _ = Package.objects.get_or_create(purl=str(purl), service=repository.admin)
        note = utils.get_scan_note(path=relative_path)
        utils.create_note(pkg=package, note_dict=note)
    return scan_count
