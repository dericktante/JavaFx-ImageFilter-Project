package edu.okcu.imagefx.filters;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;

public class ScaleFilter {

    public Image apply(Image inputImage, double scale) {
        if (inputImage == null || scale <= 0) {
            return null; // Avoid errors for invalid input
        }

        // Calculates the width and height for the image based on the scale factor
        int width = (int) (inputImage.getWidth() * scale);
        int height = (int) (inputImage.getHeight() * scale);

        WritableImage outputImage = new WritableImage(width, height);
        // Gets the pixelReader and pixelWriter to read pixels from the original image and to write pixels ro the new image respectively.
        PixelReader pixelReader = inputImage.getPixelReader();
        PixelWriter pixelWriter = outputImage.getPixelWriter();

        //loops through each pixel in the new image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int srcX = (int) (x / scale);
                int srcY = (int) (y / scale);

                
                pixelWriter.setColor(x, y, pixelReader.getColor(srcX, srcY));
            }
        }

        return outputImage;
    }
}
