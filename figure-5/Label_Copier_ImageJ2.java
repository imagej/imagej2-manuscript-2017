import ij.IJ;
import ij.ImagePlus;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.command.Command;

@Plugin(type = Command.class)
public class Label_Copier_ImageJ2 implements Command {

	@Parameter
	private ImagePlus source;

	@Parameter(type = ItemIO.BOTH)
	private ImagePlus target;

	@Override
	public void run(final String arg) {
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

}
