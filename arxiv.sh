#!/bin/sh
dir="arxiv"
mkdir -p "$dir"
cp bmcart.cls imagej2.tex imagej2.bbl imagej2.gls "$dir"

# NB: Strip out natwidth and natheight.
# Without them, the manuscript does not build locally.
# But with them in place, arXiv's processor dies.
sed -i '' -e 's/,natwidth=[0-9]*,natheight=[0-9]*//g' "$dir/imagej2.tex"

for fig in $(grep 'includegraphics\[' imagej2.tex | sed 's/.*{\(.*\)}/\1/')
do
  dest="$dir/$(dirname "$fig")"
  mkdir -p "$dest"
  cp "$fig" "$dest"
done
cd "$dir"
zip -r9 ../imagej2-arxiv.zip .
cd -
