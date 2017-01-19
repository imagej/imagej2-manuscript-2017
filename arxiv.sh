#!/bin/sh
dir="arxiv"
mkdir -p "$dir"
cp bmcart.cls imagej2.tex imagej2.bbl imagej2.gls "$dir"
for fig in $(grep 'includegraphics\[' imagej2.tex | sed 's/.*{\(.*\)}/\1/')
do
  dest="$dir/$(dirname "$fig")"
  mkdir -p "$dest"
  cp "$fig" "$dest"
done
cd "$dir"
zip -r9 ../imagej2-arxiv.zip .
cd -
