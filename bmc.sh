#!/bin/sh
dir="bmc"
mkdir -p "$dir"
cp *.bst *.cls *.sty *.tex *.bib "$dir"
for fig in \
figure-1/figure-config1-label.png \
figure-2/figure-labeled.png \
figure-3/figure-labeled.png \
figure-4/figure-label.png \
figure-5/figure-labeled.png \
figure-6/figure.png \
figure-7/figure-labeled.png \
figure-s.1/module-execution-labeled.png \
figure-s.2/figure-labeled.png
do
  ext="${fig##*.}"
  dest="$dir/$(dirname "$fig").$ext"
  cp "$fig" "$dest"
done
cd "$dir"
zip -r9 ../imagej2-bmc.zip .
cd -
