package edu.okcu.imagefx.filters;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.SnapshotParameters;
 import javafx.scene.transform.Rotate;

public class RotateFilter {
    //method to apply rotation to an image.
    public Image apply(Image image, double angle){
        if (image == null){
            return null;
        }

        //creates an image view to hold the object
        ImageView imageView = new ImageView(image);
        //set the rotation angle for the object
        imageView.setRotate(angle);

        SnapshotParameters params = new SnapshotParameters();

        //This sets the rotation around the center of the image
        params.setTransform(new Rotate(angle, image.getWidth()/2, image.getHeight()/2));

        //captures the image with the applied rotation and returns the new rotated image.
        return imageView.snapshot(params, null);
    }
}

//Source Stack Overflow
// https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://stackoverflow.com/questions/40059836/rotating-image-in-javafx&ved=2ahUKEwj5kfXg5OaLAxViL0QIHUYwHZ0QrAIoAHoECBkQAQ&usg=AOvVaw3C6SPDudiCkOJOMOLpQ6FV