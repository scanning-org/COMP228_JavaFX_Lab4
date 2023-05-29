#!/bin/bash

# get the last commit hash
last_commit=$(git rev-parse HEAD)

# get the parent commit hash
parent_commit=$(git rev-parse "$last_commit"^)

# get the diff
diff=$(git diff "$parent_commit" "$last_commit" --name-only -- '*.java')

# loop through each file in the diff
for file in $diff; do
    # if the file contains specific annotation
    if grep -q "@RequestMapping\|@RestController" "$file"; then
        echo "API change found in file $file" >> api_changes_report.txt
        grep "@RequestMapping\|@RestController" "$file" >> api_changes_report.txt
    fi
done
