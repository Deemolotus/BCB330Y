#!/usr/bin/env bash
make AllSub
make ct2dot

echo Please Enter the filename and structure Number:

read -p 'Filename: ' fn
read -p 'Structure Number: ' sn

cd exe

rm *.ct
rm *.dot

./AllSub $fn mature.ct
./ct2dot mature.ct $sn mature.dot
