#!/bin/bash
FILES=$PWD/*
for f in $FILES
do
  echo "Processing $f file..."
  RNAdistance -Xf<$f>>out.txt
done
