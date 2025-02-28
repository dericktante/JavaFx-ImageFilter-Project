package edu.okcu.imagefx.filters;
import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixelateFilter implements IFilter {
    public Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        int pixelSize = 30;
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage pixelImage = new BufferedImage(width, height, img.getType());

        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int pixelColor = img.getRGB(x, y);

                for (int a = 0; a < pixelSize; a++) {
                    for (int b = 0; b < pixelSize; b++) {
                        if (x + a < width && y + b < height) {
                            pixelImage.setRGB(x + a, y + b, pixelColor);
                        }
                    }
                }
            }

        }
        return ImageUtil.convertBufferedImageToFxImage(pixelImage);
    }
}