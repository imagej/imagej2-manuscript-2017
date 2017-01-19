import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import java.util.Random;

public class Label_Munger implements PlugIn {

	@Override
	public void run(String arg) {
		final ImagePlus imp = IJ.getImage();
		for (int i = 1; i <= imp.getStackSize(); i++) {
			imp.getStack().setSliceLabel(randomString(5, 10), i);
		}
		imp.updateAndDraw();
	}

	public static String randomString(final int min, final int max) {
		final Random r = new Random();
		final int len = r.nextInt(max - min) + min;
		final char[] letters = new char[len];
		for (int i = 0; i < len; i++) {
			letters[i] = (char) ('A' + r.nextInt(26));
		}
		return new String(letters);
	}

}
