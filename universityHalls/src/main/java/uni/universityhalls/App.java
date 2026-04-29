package uni.universityhalls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uni.universityhalls.components.HomePage;
import uni.universityhalls.components.Navbar;



/**
 * JavaFX App
 */
public class App extends Application {

    final double WIDTH = 800;
    final double HEIGHT = 640;
    final double NAVBAR_HEIGHT = 64;
    final double CONTENT_HEIGHT = HEIGHT-NAVBAR_HEIGHT;

    VBox root = new VBox();
    Navbar nav = new Navbar();
    VBox content = new VBox();
    Label viewTitle = new Label("University Halls Management System - Home");

    HomePage homePage = new HomePage(CONTENT_HEIGHT);
    Button findBtn = homePage.getFindRoom();
    Button tenantsBtn = homePage.getTenantsManager();
    Button propertyBtn = homePage.getPropertyManager();

    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) {

        viewTitle.setFont(Font.font("Ariel",20));
        viewTitle.setMinWidth(WIDTH);
        viewTitle.setAlignment(Pos.CENTER);
        content.setMinHeight(HEIGHT - NAVBAR_HEIGHT);
        content.setMaxHeight(CONTENT_HEIGHT-NAVBAR_HEIGHT);
        content.getChildren().setAll(homePage);

        root.getChildren().addAll(nav,viewTitle,content);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(40);

        Scene scene = new Scene(root,WIDTH,HEIGHT);

        propertyBtn.setOnMouseClicked(e -> {
            root.getChildren().remove(content);
            root.getChildren().add(new Label("This is property manager"));
        });

        stage.setScene(scene);
        stage.setTitle("University Halls Manager");
        stage.show();
    }

}