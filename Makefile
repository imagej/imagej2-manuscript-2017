pdf:
	latexmk -pdf

clean:
	rm -f \
		imagej2.aux imagej2.bbl imagej2.blg imagej2.dvi imagej2.fdb_latexmk \
		imagej2.fls imagej2.log imagej2.pdf imagej2.synctex.gz
