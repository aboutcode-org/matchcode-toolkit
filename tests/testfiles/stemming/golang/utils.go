/*

Copyright (c) nexB Inc. and others. All rights reserved.
ScanCode is a trademark of nexB Inc.
SPDX-License-Identifier: Apache-2.0
See http://www.apache.org/licenses/LICENSE-2.0 for the license text.
See https://github.com/aboutcode-org/dependency-inspector for support or download.
See https://aboutcode.org for more information about nexB OSS projects.

*/

package internal

import (
	"fmt"
	"os"
	"os/exec"
	"path/filepath"

	"github.com/bmatcuk/doublestar/v4"
)

// CreateLockFile generates lockfile using lockGenCmd command.
//
// If forced is false and any of the specified lockFiles already exist, skip lockfile generation.
// Otherwise, generate the lockfile using lockGenCmd.
func CreateLockFile(lockFiles []string, cmdArgs []string, lockGenCmd []string, outputFileName string, forced bool) {
	absPath := getAbsPath(cmdArgs)
	if absPath == "" {
		return
	}

	if !forced {
		// If any lockfile is present already then skip lockfile generation.
		for _, lockFile := range lockFiles {
			lockFileAbsPath := filepath.Join(absPath, lockFile)

			if res := DoesFileExists(lockFileAbsPath); !res {
				continue
			}
			return
		}
	}
	genLock(lockGenCmd, absPath, outputFileName)

}

// DoesFileExists checks if the file exists at the given absolute path.
//
// If the file exists, print its relative path and return true.
// If the file does not exist, return false.
func DoesFileExists(absPath string) bool {
	if _, err := os.Stat(absPath); err == nil {

		cwd, err := os.Getwd()
		if err != nil {
			fmt.Fprintln(os.Stderr, "Error:", err)
			os.Exit(1)
		}

		relPath, err := filepath.Rel(cwd, absPath)
		if err != nil {
			fmt.Fprintln(os.Stderr, "Error:", err)
			os.Exit(1)
		}

		fmt.Printf("Lockfile '%s' already present.\n", relPath)
		return true
	}
	return false
}

// getAbsPath returns the absolute path of a given directory.
//
// If cmdArgs is empty, return the absolute path of the current directory.
// Otherwise, return the absolute path of first arg in cmdArgs.
// If there is an error while retrieving the absolute path, print an error
// message to the standard error and return an empty string.
func getAbsPath(cmdArgs []string) string {
	path := "."
	if len(cmdArgs) > 0 {
		path = cmdArgs[0]
	}

	absPath, err := filepath.Abs(path)
	if err != nil {
		fmt.Fprintln(os.Stderr, "Error: Failed to retrieve absolute path: ", err)
		return ""
	}

	return absPath
}

// genLock generates a lockfile at absPath using the lockGenCmd command.
//
// Execute lockGenCmd command in the absPath directory.
// If outputFileName is specified, create an output file in absPath and redirect the command's
// output to that file. Print an error message and exit with status 1 if creating the output file fails.
func genLock(lockGenCmd []string, absPath string, outputFileName string) {
	fmt.Printf("Generating lockfile at '%s' using '%s'\n", absPath, lockGenCmd)

	// #nosec G204
	command := exec.Command(lockGenCmd[0], lockGenCmd[1:]...)
	command.Dir = absPath
	command.Stderr = os.Stderr
	command.Stdout = os.Stdout
	if outputFileName != "" {

		outputPath := filepath.Join(absPath, outputFileName)

		// #nosec G304
		outputFile, err := os.Create(outputPath)
		if err != nil {
			fmt.Fprintln(os.Stderr, "Error: failed to create output file: ", err)
			os.Exit(1)
		}
		defer outputFile.Close()

		command.Stdout = outputFile
	}

	if err := command.Run(); err != nil {
		fmt.Fprintln(os.Stderr, "Error: Failed to generate lockfile: ", err)
		return
	}

	fmt.Println("Lock file generated successfully.")
}

// CreateLockFileNuGet generates NuGet lockfile for all NuGet projects found in the directory.
//
// Search for all .csproj files recursively in the project_path.
// If no .csproj files are found, print an error message to standard error and return.
//
// For each .csproj file found, generate corresponding lockfile if force is true or the lockfile
// does not already exist.
func CreateLockFileNuGet(cmdArgs []string, force bool) {
	nuGetLockFileName := "packages.lock.json"
	nuGetLockFileGenCmd := []string{"dotnet", "restore", "--use-lock-file"}

	project_path := getAbsPath(cmdArgs)
	if project_path == "" {
		return
	}

	fs := os.DirFS(project_path)
	csproj_pattern := "**/*.csproj"

	csproj_files, _ := doublestar.Glob(fs, csproj_pattern)
	if len(csproj_files) == 0 {
		fmt.Fprintln(os.Stderr, "Error: Path does not contain a NuGet project")
		return
	}

	// Generate lockfile for all NuGet projects
	for _, file := range csproj_files {
		fullPath := filepath.Join(project_path, file)
		dir := filepath.Dir(fullPath)

		lockFile := filepath.Join(dir, nuGetLockFileName)
		if force || !DoesFileExists(lockFile) {
			genLock(nuGetLockFileGenCmd, dir, "")
		}

	}

}
