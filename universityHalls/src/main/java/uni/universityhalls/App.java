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
import uni.universityhalls.components.PropertyManager;


/**
 * JavaFX App
 */
public class App extends Application {

    final double WIDTH = 800;
    final double HEIGHT = 640;
    final double NAVBAR_HEIGHT = 64;
    final double CONTENT_HEIGHT = HEIGHT-NAVBAR_HEIGHT;
    final String appTitle = "University Halls Management System";
    VBox root = new VBox();
    Navbar nav = new Navbar();
    VBox content = new VBox();
    Label viewTitle = new Label(appTitle+" - Home");

    HomePage homePage = new HomePage(CONTENT_HEIGHT);
    Button findBtn = homePage.getFindRoom();
    Button tenantsBtn = homePage.getTenantsManager();
    Button propertyBtn = homePage.getPropertyManager();

    Store store = StoreRepository.load("store1.dat");

    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) {

        if (store == null) {
            store = new Store();
        }

        viewTitle.setFont(Font.font("Ariel",20));
        viewTitle.setMinWidth(WIDTH);
        viewTitle.setAlignment(Pos.CENTER);

        homePage.setMinHeight(HEIGHT - NAVBAR_HEIGHT);
        homePage.setMaxHeight(CONTENT_HEIGHT-NAVBAR_HEIGHT);

        root.getChildren().addAll(nav,viewTitle,homePage);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(40);

        Scene scene = new Scene(root,WIDTH,HEIGHT);

        propertyBtn.setOnMouseClicked(e -> {
            root.getChildren().remove(homePage);
            viewTitle.setText(appTitle + " - Property Manager");
            PropertyManager manager =new PropertyManager(store);
            root.getChildren().add(manager);
            Button returnBtn = manager.getReturnHome();
            returnBtn.setOnAction(event-> returnHome(manager)
            );


        });

        stage.setScene(scene);
        stage.setTitle("University Halls Manager");
        stage.show();
    }
    private void returnHome(VBox elToRemove) {
        root.getChildren().remove(elToRemove);
        viewTitle.setText(appTitle + " - Home");
        root.getChildren().add(homePage);
    }

}