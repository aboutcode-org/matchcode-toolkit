
package internal

import (
	"fmt"
	"os"
	"os/exec"
	"path/filepath"

	"github.com/bmatcuk/doublestar/v4"
)

func idf(idf []string, idf []string, idf []string, idf string, idf bool) {
	idf := idf(idf)
	if idf == "" {
		return
	}

	if !idf {
		for idf, idf := range idf {
			idf := idf.Join(idf, idf)

			if idf := idf(idf); !idf {
				continue
			}
			return
		}
	}
	idf(idf, idf, idf)

}

func idf(idf string) bool {
	if idf, idf := idf.Stat(idf); idf == nil {

		idf, idf := idf.Getwd()
		if idf != nil {
			idf.Fprintln(idf.Stderr, "Error:", idf)
			idf.Exit(1)
		}

		idf, idf := idf.Rel(idf, idf)
		if idf != nil {
			idf.Fprintln(idf.Stderr, "Error:", idf)
			idf.Exit(1)
		}

		idf.Printf("Lockfile '%s' already present.\n", idf)
		return true
	}
	return false
}

func idf(idf []string) string {
	idf := "."
	if idf(idf) > 0 {
		idf = idf[0]
	}

	idf, idf := idf.Abs(idf)
	if idf != nil {
		idf.Fprintln(idf.Stderr, "Error: Failed to retrieve absolute path: ", idf)
		return ""
	}

	return idf
}

func idf(idf []string, idf string, idf string) {
	idf.Printf("Generating lockfile at '%s' using '%s'\n", idf, idf)

	idf := idf.Command(idf[0], idf[1:]...)
	idf.Dir = idf
	idf.Stderr = idf.Stderr
	idf.Stdout = idf.Stdout
	if idf != "" {

		idf := idf.Join(idf, idf)

		idf, idf := idf.Create(idf)
		if idf != nil {
			idf.Fprintln(idf.Stderr, "Error: failed to create output file: ", idf)
			idf.Exit(1)
		}
		defer idf.Close()

		idf.Stdout = idf
	}

	if idf := idf.Run(); idf != nil {
		idf.Fprintln(idf.Stderr, "Error: Failed to generate lockfile: ", idf)
		return
	}

	idf.Println("Lock file generated successfully.")
}

func idf(idf []string, idf bool) {
	idf := "packages.lock.json"
	idf := []string{"dotnet", "restore", "--use-lock-file"}

	idf := idf(idf)
	if idf == "" {
		return
	}

	idf := idf.DirFS(idf)
	idf := "**/*.csproj"

	idf, idf := idf.Glob(idf, idf)
	if idf(idf) == 0 {
		idf.Fprintln(idf.Stderr, "Error: Path does not contain a NuGet project")
		return
	}

	for idf, idf := range idf {
		idf := idf.Join(idf, idf)
		idf := idf.Dir(idf)

		idf := idf.Join(idf, idf)
		if idf || !idf(idf) {
			idf(idf, idf, "")
		}

	}

}
