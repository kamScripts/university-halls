package uni.universityhalls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import uni.universityhalls.components.Navbar;



/**
 * JavaFX App
 */
public class App extends Application {

    final double WIDTH = 800;
    final double HEIGHT = 640;
    final double NAVBAR_HEIGHT = 24;

    VBox root = new VBox();
    Navbar nav = new Navbar();
    StackPane content = new StackPane();
    Navbar topMenus = new Navbar();

    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) {

        Rectangle rect = new Rectangle(300,300,20,20);
        rect.setFill(Color.rgb(10,150,100));

        content.setMinHeight(HEIGHT - NAVBAR_HEIGHT);
        content.getChildren().setAll(rect);
        content.setAlignment(Pos.CENTER);

        root.getChildren().addAll(nav,content);
        root.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(root,WIDTH,HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Draw rectangle");
        stage.show();
    }

}