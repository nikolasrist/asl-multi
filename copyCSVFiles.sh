#!/bin/bash

# Use this script in the 'asl-kalkulationen' folder to work properly.

# Iterate through all items in the current directory
echo "Start copy process for CSV files"
echo "Checking current location:"
currentDir=$(pwd)
echo "Current directory: $currentDir"

if [[ "$currentDir" == */asl-kalkulationen ]]; then
  # Use ./einzelpakete/*/ if in the 'asl-kalkulationen' folder
  searchPath="./einzelpakete/*/"
else
  # Use ./*/ if in einzelpakete
  searchPath="./*/"
fi

echo "Using search path: $searchPath"

echo "Creating 'csv_files' folder"
mkdir -p ./csv_files
for dir in $searchPath; do
  # Check if it is a directory
  if [ -d "$dir" ] && [[ $(basename "$dir") =~ ^[0-9]{2}$ ]]; then
    echo "About to work through folder/person: $dir"
    # Perform actions on the directory here
    for subDir in "$dir"MS_GRASE/*/; do
      echo "Copying from SubDirectory: $subDir"
      # Check if it is a directory
      if [ -d "$subDir" ] && [[ $(basename "$subDir") =~ ^[0-9]{2}$ ]]; then
        dirName=$(basename "$dir")
        subDirName=$(basename "$subDir")
        srcFile="${subDir}native_space/region_analysis/region_analysis.csv"
        destFile="./csv_files/${dirName}_${subDirName}_region_analysis.csv"
        if [ -f "$srcFile" ]; then
          echo "Created target file name: $destFile"
          cp "$srcFile" "$destFile"
        else
          echo "Warning: File $srcFile does not exist!"
        fi
      fi
    done
  fi
done