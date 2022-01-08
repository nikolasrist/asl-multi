#!/bin/bash

echo "#### Zielpfad angeben ohne ~ und ohne / am Ende: ####"
read -p "Zielpfad: " target_path
echo "#### Reset git repo ####"
git reset --hard
echo "#### Pull latest state ####"
git pull
echo "#### Build exe file ####"
gradle build
echo "#### Extract Dist files ####"
tar -xf ./build/distributions/asl-multi-1.0.tar
echo "#### Copy exe to '$target_path' ####"
cp -r "./build/distributions/asl-multi-1.0" "$HOME$target_path"
