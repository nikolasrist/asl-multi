#!/bin/bash

# Iterate through all items in the current directory
for dir in */; do
  # Check if it is a directory
  if [ -d "$dir" ]; then
    echo "Directory: $dir"
    # Perform actions on the directory here
  fi
done