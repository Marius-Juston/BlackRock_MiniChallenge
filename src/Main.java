import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Marius Juston
 **/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedImage image = readImage("src/imageEmbedded.png");

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int clr = image.getRGB(x, y);

				int newRed = (clr & 0x00ff0000) >> 16;
				int lestSig = newRed & 0b1;
				newRed = (lestSig) * 0xff;

				int newGreen = newRed;
				int newBlue = newRed;

				int col = (newRed << 16) | (newGreen << 8) | newBlue;
				bufferedImage.setRGB(x, y, col);

			}
		}

		ImageIO.write(bufferedImage, "png", new File("output.png"));
	}

	static BufferedImage readImage(String fileLoc) throws IOException {
		return ImageIO.read(new File(fileLoc));
	}

}
