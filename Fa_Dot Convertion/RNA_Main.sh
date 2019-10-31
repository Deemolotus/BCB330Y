#!/usr/bin/env bash
cd ./RNAstructure
make AllSub
make ct2dot

echo Please Enter the filename:

read -p 'Filename: ' fn

rm ./splited/*.fa
rm ./splited/*.ct
rm ./splited/*.dot

split -l 3 ./$fn.fa ./splited/$fn.fa

for file in ./splited/*
do
    mv "$file" "$file.fa"
done

for file in ./splited/*.fa
do
    ./exe/AllSub "$file" "$file.ct"
    ./exe/ct2dot "$file.ct" 1 "$file.dot"
done

cat ./splited/*.dot >> $fn.dot
