import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Marius Juston
 **/
public final class Main {

	public static final String INPUT_IMAGE = "src/imageEmbedded.png";
	public static final String OUTPUT_IMAGE = "src/output.png";

	public static void main(String[] args) throws IOException {
		BufferedImage image = readImage(INPUT_IMAGE);
		BufferedImage bufferedImage = extractHiddenImage(image);

		ImageIO.write(bufferedImage, "png", new File(OUTPUT_IMAGE));
	}

	private static BufferedImage readImage(String fileLocation) throws IOException {
		return ImageIO.read(new File(fileLocation));
	}

	private static BufferedImage extractHiddenImage(BufferedImage image) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);

				int newRed = (clr & 0x00ff0000) >> 16;
				int lestSig = newRed & 0b11;
				newRed = (lestSig) * 0xff;

				int newGreen = newRed;
				int newBlue = newRed;

				int col = (newRed << 16) | (newGreen << 8) | newBlue;
				bufferedImage.setRGB(x, y, col);

			}
		}

		return bufferedImage;
	}

}
