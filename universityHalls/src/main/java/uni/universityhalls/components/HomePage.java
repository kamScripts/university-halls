package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.layout.VBox;

public class HomePage extends VBox {


    private final ButtonWithImage tenantsManager = new ButtonWithImage("/images/users32x32.png", "Tenants Manager");
    private final ButtonWithImage propertyManager = new ButtonWithImage("/images/grid32x32.png", "Property Manager");
    public HomePage(double windowHeight){

        double sectionHeight = (windowHeight)/2;
        this.getChildren().addAll(tenantsManager, propertyManager);


        this.setMinHeight(sectionHeight);
        this.setMaxHeight(sectionHeight);
        this.setSpacing(48);

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(16));

    }

    public ButtonWithImage getTenantsManager() {
        return tenantsManager;
    }

    public ButtonWithImage getPropertyManager() {
        return propertyManager;
    }
}
