package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.GrayScaleFilter;
import edu.okcu.imagefx.filters.PixelateFilter;
import edu.okcu.imagefx.filters.SepiaScaleFilter;
import edu.okcu.imagefx.filters.RotateFilter;
import edu.okcu.imagefx.filters.ScaleFilter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.IOException;

public class ImageFXController {
    // Filter Objects
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    SepiaScaleFilter sepiaFilter = new SepiaScaleFilter();
    RotateFilter rotateFilter = new RotateFilter();
    PixelateFilter pixelateFilter = new PixelateFilter();
    ScaleFilter scaleFilter = new ScaleFilter();

    @FXML
    private ImageView imgPicture; //Displays the original image.

    @FXML
    private ImageView imgNewPicture; //Displays the modified image.

    @FXML
    private File selectedFile; /*Handles the actions when the user chooses a file
    Displays the chosen image in the 'imgPicture' ImageView.*/

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    public void initialize() {
        //Adds all the filter options to the ComboBox
        filterComboBox.getItems().addAll( "Grayscale", "Sepia", "Pixelate", "Rotate", "Scale","Remove Filter");
    }

    @FXML
    protected void onChooseFileButtonClick() {
        FileChooser chooser = new FileChooser();
        //This shows the file chooser dialog and retrieves the selected file.
        selectedFile = chooser.showOpenDialog(null);

        if (selectedFile != null) {
            //Displays the selected image in the imgPicture ImageView.
            Image image = new Image(selectedFile.toURI().toString());
            imgPicture.setImage(image);
        }
    }

    @FXML
    public void onApplyFilterButtonClick() throws IOException {
        //This ensures that an image is selected and filter is chosen before proceeding.
        if (selectedFile == null || imgPicture.getImage() == null || filterComboBox.getValue() == null) {
            return; // No image selected or no filter chosen.
        }

        //Gets the selected filter name from the ComboBox.
        String selectedFilter = filterComboBox.getValue();

        //Application of the selected filter name.
        switch (selectedFilter) {
            case "Grayscale":
                    imgNewPicture.setImage(grayScaleFilter.apply(selectedFile));
                break;

            case "Sepia":
                imgNewPicture.setImage(sepiaFilter.apply(selectedFile));
                break;

            case "Pixelate":
                    imgNewPicture.setImage(pixelateFilter.apply(selectedFile));
                break;

            case "Rotate":
                if (imgNewPicture.getImage() == null) {
                    imgNewPicture.setImage(rotateFilter.apply(imgPicture.getImage(), 90)); // Rotates the original image
                } else {
                    imgNewPicture.setImage(rotateFilter.apply(imgNewPicture.getImage(), 90)); // Rotates the filtered image
                }
                break;

            case "Scale":
                if (imgNewPicture.getImage() == null) {
                    imgNewPicture.setImage(scaleFilter.apply(imgPicture.getImage(), 0.5)); // Scales the original image
                } else {
                    imgNewPicture.setImage(scaleFilter.apply(imgNewPicture.getImage(), 0.5)); // Scales the filtered image
                }
                break;

            case "Remove Filter":
                imgNewPicture.setImage(imgPicture.getImage()); // Restore original image
                break;
            default:
                break;
        }
    }
}