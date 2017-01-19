import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;

public class Label_Copier_ImageJ1 implements PlugIn {

	private ImagePlus source;

	private ImagePlus target;

	@Override
	public void run(final String arg) {
		promptUserForImages();
		copyLabels(source, target);
	}

	public void copyLabels(final ImagePlus source, final ImagePlus target) {
		if (source.getStackSize() != target.getStackSize()) {
			IJ.error("Source and target images must have same stack size.");
			return;
		}
		for (int i = 1; i <= source.getStackSize(); i++) {
			final String label = source.getStack().getSliceLabel(i);
			target.getStack().setSliceLabel(label, i);
		}
	}

	public void promptUserForImages() {
		final int[] wList = WindowManager.getIDList();
		if (wList==null) {
			IJ.noImage();
			return;
		}
		final String[] titles = new String[wList.length];
		for (int i=0; i<wList.length; i++) {
			final ImagePlus imp = WindowManager.getImage(wList[i]);
			if (imp!=null)
				titles[i] = imp.getTitle();
			else
				titles[i] = "";
		}
		final GenericDialog gd =
			new GenericDialog("Label Copier", IJ.getInstance());
		gd.addChoice("Source Image:", titles, titles[0]);
		gd.addChoice("Target Image:", titles, titles[0]);
		gd.showDialog();
		if (gd.wasCanceled())
			return;
		final int sourceIndex = gd.getNextChoiceIndex();
		final int targetIndex = gd.getNextChoiceIndex();
		source = WindowManager.getImage(wList[sourceIndex]);
		target = WindowManager.getImage(wList[targetIndex]);
	}

}
