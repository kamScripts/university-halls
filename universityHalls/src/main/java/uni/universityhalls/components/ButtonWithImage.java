package uni.universityhalls.components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class ButtonWithImage extends Button {

    public ButtonWithImage(String resourcePath, String value){
        var url = getClass().getResource(resourcePath);
        if (url == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        Image image = new Image(url.toExternalForm());


        ImageView imageView = new ImageView(image);
        this.setGraphic(imageView);
        this.setText(value);
        this.setFont(Font.font("Ariel",20));
    }

}
