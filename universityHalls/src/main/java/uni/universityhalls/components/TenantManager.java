package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uni.universityhalls.Store;
import uni.universityhalls.TenantRecord;
import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    // Actions
    private final HBox actionsBox = new HBox(64);

    // Results panel populated by any of the three actions
    private final VBox resultsBox = new VBox(8);
    private final TableView<TenantRecord> resultsTable = new TableView<>();

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
        buildResultsTable();
        this.getChildren().addAll(returnHome, actionsBox, resultsBox);
        this.setPadding(new Insets(24));

        submitButton.setOnAction(e -> {
            Toggle selected = displayGroup.getSelectedToggle();
            if (selected == null) return;

            Collection<TenantRecord> all = store.getAllTenantRecords().values();
            List<TenantRecord> filtered = new ArrayList<>();
            // showTenants, based on type
            if (selected == showStudents) {
                for (TenantRecord r : all) {
                    if (r.getTenant() instanceof Student) {
                        filtered.add(r);
                    }
                }
            } else if (selected == showEmployees) {
                for (TenantRecord r : all) {
                    if (r.getTenant() instanceof Employee) {
                        filtered.add(r);
                    }
                }
            } else { // showTenants, include everything
                filtered.addAll(all);
            }

            showRecords(filtered);
        });

    }
    private void showRecords(java.util.Collection<TenantRecord> records) {
        resultsTable.getItems().setAll(records);
        if (!resultsBox.getChildren().contains(resultsTable)) {
            resultsBox.getChildren().add(resultsTable);
        }
    }
    private void buildResultsTable() {
        TableColumn<TenantRecord, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTenant().getId()));

        TableColumn<TenantRecord, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTenant().getName()));

        TableColumn<TenantRecord, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTenant().getClass().getSimpleName()));

        TableColumn<TenantRecord, String> hallCol = new TableColumn<>("Hall");
        hallCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getHallName()));

        TableColumn<TenantRecord, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getRoomNumber()));

        resultsTable.getColumns().addAll(idCol, nameCol, typeCol, hallCol, roomCol);
        resultsTable.setPlaceholder(new Label("No tenants to show"));
    }

    public Button getReturnHome() {
        return returnHome;
    }
}
