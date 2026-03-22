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
import uni.universityhalls.components.HomePage;
import uni.universityhalls.components.Navbar;



/**
 * JavaFX App
 */
public class App extends Application {

    final double WIDTH = 800;
    final double HEIGHT = 640;
    final double NAVBAR_HEIGHT = 24;
    final double CONTENT_HEIGHT = HEIGHT-NAVBAR_HEIGHT;

    VBox root = new VBox();
    Navbar nav = new Navbar();
    StackPane content = new StackPane();
    Navbar topMenus = new Navbar();
    HomePage homePage = new HomePage(CONTENT_HEIGHT);

    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) {


        content.setMinHeight(HEIGHT - NAVBAR_HEIGHT);
        content.getChildren().setAll(homePage);
        content.setAlignment(Pos.CENTER);

        root.getChildren().addAll(nav,content);
        root.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(root,WIDTH,HEIGHT);

        stage.setScene(scene);
        stage.setTitle("University Halls Manager");
        stage.show();
    }

}