float[] bgPixels = (float[])fp.getPixels(); //currently holds the background
if (ip instanceof FloatProcessor) {         //here ip and fp are the same (bgPixels will be output)
    float[] snapshotPixels = (float[])fp.getSnapshotPixels(); //original data in the snapshot
    for (int p=0; p<bgPixels.length; p++)
        bgPixels[p] = snapshotPixels[p]-bgPixels[p];
//for all others, the image data are in ip, the background is in fp
} else if (ip instanceof ShortProcessor) {
    float offset = invert ? 65535.5f : 0.5f;//includes 0.5 for rounding when converting float to short
    short[] pixels = (short[])ip.getPixels();
    for (int p=0; p<bgPixels.length; p++) {
        float value = (pixels[p]&0xffff) - bgPixels[p] + offset;
        if (value<0f) value = 0f;

        if (value>65535f) value = 65535f;

        pixels[p] = (short)(value);
    }
} else if (ip instanceof ByteProcessor) {
    float offset = invert ? 255.5f : 0.5f;  //includes 0.5 for rounding when converting float to byte
    byte[] pixels = (byte[])ip.getPixels();
    for (int p=0; p<bgPixels.length; p++) {
        float value = (pixels[p]&0xff) - bgPixels[p] + offset;
        if (value<0f) value = 0f;

        if (value>255f) value = 255f;

        pixels[p] = (byte)(value);
    }
} else if (ip instanceof ColorProcessor) {
    float offset = invert ? 255.5f : 0.5f;
    int[] pixels = (int[])ip.getPixels();
    int shift = 16 - 8*channelNumber;

    int byteMask = 255<<shift;
    int resetMask = 0xffffffff^(255<<shift);

    for (int p=0; p<bgPixels.length; p++) {
        int pxl = pixels[p];
        float value = ((pxl&byteMask)>>shift) - bgPixels[p] + offset;
        if (value<0f) value = 0f;

        if (value>255f) value = 255f;
        pixels[p] = (pxl&resetMask) | ((int)value<<shift);

    }
}
