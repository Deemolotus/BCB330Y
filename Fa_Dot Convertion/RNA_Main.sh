#!/usr/bin/env bash
cd ./RNAstructure
make AllSub
make ct2dot


echo Please Enter the filename:

read -p 'Filename: ' fn

split -l 2 ./$fn.fa ./splited/$fn

#Rename file to .fa format
for file in ./splited/*
do
    mv "$file" "$file.fa"
done

for file in ./splited/*.fa
do
    ./exe/AllSub "$file" "${file%.fa}.ct"
    ./exe/ct2dot "${file%.fa}.ct" 1 "${file%.fa}.dot"
done

cat ./splited/*.dot >> $fn.dot

mv $fn.dot ../Main/$fn.dot

rm ./splited/*.fa
rm ./splited/*.ct
rm ./splited/*.dot
