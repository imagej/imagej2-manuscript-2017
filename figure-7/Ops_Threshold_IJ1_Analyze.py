# @OpService ops
# @UIService ui
# @Dataset inputData
# @Double sigma

from net.imglib2.img.display.imagej import ImageJFunctions
from ij import IJ
from java.lang import Double

# create a log kernel
logKernel = ops.create().kernelLog(Double(inputData.numDimensions()), sigma);

# convolve with log kernel
logFiltered = ops.filter().convolve(inputData, logKernel)

# display log filter result
ui.show("log", logFiltered)

# otsu threshold and display
thresholded = ops.threshold().otsu(logFiltered)
ui.show("thresholded", thresholded)

# convert to imagej1 imageplus so we can run analyze particles
impThresholded = ImageJFunctions.wrap(thresholded, "wrapped")

# convert to mask and analyze particles
IJ.run(impThresholded, "Convert to Mask", "")
IJ.run(impThresholded, "Analyze Particles...", "display add")
