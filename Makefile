pdf:
	latex imagej2
	makeglossaries imagej2
	latexmk -pdf

clean:
	rm -f \
		imagej2.aux imagej2.bbl imagej2.blg imagej2.dvi imagej2.fdb_latexmk \
		imagej2.fls imagej2.glg imagej2.glo imagej2.gls imagej2.ist \
		imagej2.log imagej2.pdf imagej2.synctex.gz
