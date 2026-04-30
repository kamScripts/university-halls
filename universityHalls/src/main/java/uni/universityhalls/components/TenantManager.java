package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uni.universityhalls.Store;

public class TenantManager extends VBox {
    private final Store store;
    //UI components
    private final Label displayLabel =  new Label("Display Tenants");
    private final RadioButton showTenants = new RadioButton("All Tenants");
    private final RadioButton showStudents = new RadioButton("Student tenants");
    private final RadioButton showEmployees = new RadioButton("Employee tenants");
    private final ToggleGroup displayGroup = new ToggleGroup();
    private final Button submitButton = new Button("Display");
    private final VBox displayFormBox = new VBox(8);
    //Search Tenants by ID
    private final Label searchIdLabel = new Label("Search by ID");
    private final TextField tenantId = new TextField("Tenant ID");
    private final Button searchButton = new Button("Search");
    private final VBox searchByIdBox = new VBox(16);

    //Search tenants assigned to an accommodation
    private final Label searchByLocationLabel = new Label("Search By Location");
    private final ComboBox<String> hallName = new ComboBox<>();
    private final ComboBox<String> roomNumber = new ComboBox<>();
    private final Button submitLocationBtn = new Button("Search");
    private final VBox searchByLocationBox = new VBox(8);

    private final HBox actionsBox = new HBox(64);
    //Return Home Button
    private final Button returnHome = new Button("⬅ Back");

    public TenantManager(Store storeIn) {
        this.store = storeIn;
        // group radio buttons
        displayGroup.getToggles().addAll(showTenants, showStudents, showEmployees);
        displayFormBox.getChildren().addAll(displayLabel, showTenants, showStudents, showEmployees, submitButton);
        searchByIdBox.getChildren().addAll(searchIdLabel, tenantId, searchButton);
        searchByLocationBox.getChildren().addAll(searchByLocationLabel, hallName, roomNumber, submitLocationBtn);
        hallName.setValue("Hall Name");
        roomNumber.setValue("Room Number");
        actionsBox.getChildren().addAll(displayFormBox, searchByIdBox, searchByLocationBox);
        actionsBox.setAlignment(Pos.CENTER);
        actionsBox.setPadding(new Insets(16));
        actionsBox.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        )));
        this.getChildren().addAll(returnHome,actionsBox);
        this.setPadding(new Insets(24));

    }

    public Button getReturnHome() {
        return returnHome;
    }
}
