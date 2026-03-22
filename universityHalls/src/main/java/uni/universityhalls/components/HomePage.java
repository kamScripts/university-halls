package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePage extends VBox {
    private final HBox navigation = new HBox();
    private final HBox dashboard = new HBox();
    private final Label dashLabel = new Label("DASHBOARD");


    private final ButtonWithImage findRoom = new ButtonWithImage("/images/search32x32.png", "Find Vacant Room");
    private final ButtonWithImage tenantsManager = new ButtonWithImage("/images/users32x32.png", "Tenants Manager");
    private final ButtonWithImage propertyManager = new ButtonWithImage("/images/grid32x32.png", "Property Manager");

    public HomePage(double windowHeight){

        navigation.getChildren().addAll(findRoom, tenantsManager, propertyManager);
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(32);

        dashboard.getChildren().add(dashLabel);
        dashboard.setAlignment(Pos.CENTER);

        this.getChildren().addAll(navigation,dashboard);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(16));
        this.setMinHeight(windowHeight);
        this.setMaxHeight(windowHeight);

    }

    public HBox getNavigation() {
        return navigation;
    }

    public HBox getDashboard() {
        return dashboard;
    }

    public Label getDashLabel() {
        return dashLabel;
    }

    public ButtonWithImage getFindRoom() {
        return findRoom;
    }

    public ButtonWithImage getTenantsManager() {
        return tenantsManager;
    }

    public ButtonWithImage getPropertyManager() {
        return propertyManager;
    }
}
