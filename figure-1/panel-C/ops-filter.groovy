// @OpService ops
// @DatasetService datasetService
// @Dataset image
// @OUTPUT Object dog
// @OUTPUT Object gauss
// @OUTPUT Object median
// @OUTPUT Object erode
// @OUTPUT Object dilate
// @OUTPUT Object close
// @OUTPUT Object topHat
import net.imglib2.algorithm.neighborhood.DiamondShape

def dataset(name, image) {
	dataset = datasetService.create(image)
	dataset.setName(name)
	return dataset
}

shape = new DiamondShape(3)
image32 = ops.convert().float32(image)
dog = dataset("dog(10, 5)", ops.filter().dog(image32, 10, 5))
gauss = dataset("gauss(3)", ops.filter().gauss(image32, 3))
median = dataset("median", ops.filter().median(ops.create().img(image), image, shape))
erode = dataset("erode", ops.morphology().erode(image, [shape]))
dilate = dataset("dilate", ops.morphology().dilate(image, [shape]))
close = dataset("close", ops.morphology().close(image, [shape]))
topHat = dataset("topHat", ops.morphology().topHat(image32, [shape]))
