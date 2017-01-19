final T type = img.firstElement();
final double min = type.getMinValue();
final double max = type.getMaxValue();
double offset = invert ? max : 0;
if (type instanceof IntegerType) offset += 0.5;

final Cursor<T> imgCursor = img.localizingCursor();
final RandomAccess<T> bgAccess = bg.randomAccess();
while (imgCursor.hasNext()) {
    imgCursor.fwd();
    bgAccess.setPosition(imgCursor);
    final T sample = imgCursor.get();
    final double bgVal = bgAccess.get().getRealDouble();

    final double value = sample.getRealDouble() - bgVal + offset;
    if (value < min) value = min;
    if (value > max) value = max;
    sample.setReal(value);
}
