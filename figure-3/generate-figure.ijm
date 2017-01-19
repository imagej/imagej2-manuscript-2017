dir = "/Users/curtis/Dropbox/LOCI/papers/2016-imagej2/figure1";

open(dir + "/imagej1.png");
w1 = getWidth();
h1 = getHeight();
open(dir + "/imglib2.png");
w2 = getWidth();
h2 = getHeight();

pad = 8;

w = w1 + w2 + pad*5;
if (h1 > h2) h = h1 + pad*2;
else h = h2 + pad*2;

newImage("figure", "RGB white", w, h, 1);

selectWindow("imagej1.png");
run("Select All");
run("Copy");
selectWindow("figure");
makeRectangle(pad, pad, w1, h1);
run("Paste");

selectWindow("imglib2.png");
run("Select All");
run("Copy");
selectWindow("figure");
makeRectangle(w1 + pad*4, pad, w2, h2);
run("Paste");

run("Select None");
setColor(0, 0, 0);
setLineWidth(4);
drawLine(w1 + pad*3, 0, w1 + pad*3, h);

saveAs("PNG", dir + "/../figure-1.png");
