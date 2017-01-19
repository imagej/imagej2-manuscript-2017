// @OpService ops
// @Dataset input
// @OUTPUT Dataset output

// run map of zernike magnitude over the image; visualize result
// repeat with various statistics. see what looks cool.
// make it into a panel?

t = input.firstElement();
//op = ops.op("zernike.magnitude", t, input, order, repetition)
op = ops.op("math.sin", t, t)
output = ops.create().img(input)
import net.imglib2.IterableInterval
import net.imglib2.RandomAccessibleInterval
ops.map(output as RandomAccessibleInterval, input as IterableInterval, op)
