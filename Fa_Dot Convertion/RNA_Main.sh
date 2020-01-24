#!/usr/bin/env bash
cd ./RNAstructure
make AllSub
make ct2dot


echo Please Enter the filename:

read -p 'Filename: ' fn

awk '/>/{close(x);x="S"++i".fa";}{print > x;}' ${fn}.fa

for file in ./splited/*.fa
do
    ./exe/AllSub "$file" "${file%.fa}.ct"
    ./exe/ct2dot "${file%.fa}.ct" 1 "${file%.fa}.dot"
done

cat ./splited/*.dot >> ${fn}.dot

mv $fn.dot ../Main/${fn}.dot

rm ./splited/*.fa
rm ./splited/*.ct
rm ./splited/*.dot
