#!/bin/bash

#Get the last commit hash
last_commit=$(git rev-parse HEAD)

#Get the parent of the last commmit
parent_commit=$(git rev-parse "$(last_commit)^")

#Check for addition in Java files that contain selected annotations
api_changes_detected=$(echo "$diff" | grep '^\+.*(@RequestMapping|@RestController).*\.java$')

# If changes detected
if [ -n "$api_changes_detected"]; then
    echo "::set-output name=api_changes_detected::true"
else
    echo "::set-output name=api_changes_detected::false"
fi