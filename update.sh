#!/bin/bash

echo "#### Zielpfad angeben ohne ~ und ohne / am Ende: ####"
read -p "Zielpfad: " target_path
echo "#### Reset git repo ####"
git reset --hard
echo "#### Pull latest state ####"
git pull
echo "#### Build exe file ####"
gradle build
echo "#### Copy exe to '$target_path' ####"
cp "./build/bin/native/releaseExecutable/multi-asl-cli.kexe" "$HOME$target_path/multi-asl-cli.kexe"
