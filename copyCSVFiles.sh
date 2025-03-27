#!/bin/bash

# Use this script in the `asl-kalkulationen` folder to work properly.

# Iterate through all items in the current directory
echo "Start copy process for CSV files"
echo "Creating 'csv_files' folder"
mkdir -p ./csv_files
for dir in ./einzelpakete/*/; do
  # Check if it is a directory
  if [ -d "$dir" ]; then
    echo "Copying now from directory: $dir"
    # Perform actions on the directory here
    for subDir in "$dir"MS_GRASE/*/; do
      echo "Copying from SubDirectory $subDir"
      # Check if it is a directory
      if [ -d "$subDir" ]; then
        dirName=$(basename "$dir")
        subDirName=$(basename "$subDir")

        srcFile="${subDir}native_space/region_analysis/region_analysis.csv"
        destFile="./csv_files/${dirName}_${subDirName}_region_analysis.csv"
        echo "Detected sourceFile: $srcFile"
        echo "Detected destFile: $destFile"
        if [ -f "$srcFile" ]; then
          cp "$srcFile" "$destFile"
        else
          echo "Warning: File $srcFile does not exist!"
        fi
      fi
    done
  fi
done