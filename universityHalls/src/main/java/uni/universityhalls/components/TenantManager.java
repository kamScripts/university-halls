package uni.universityhalls.components;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uni.universityhalls.Store;
import uni.universityhalls.StoreRepository;
import uni.universityhalls.TenantRecord;
import uni.universityhalls.exceptions.HallNotFoundException;
import uni.universityhalls.exceptions.RoomFull;
import uni.universityhalls.exceptions.RoomNotFoundException;
import uni.universityhalls.exceptions.TenantRecordNotFound;
import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;
import uni.universityhalls.people.Tenant;

import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private final TextField tenantId = new TextField();
    private final Button searchButton = new Button("Search");
    private final VBox searchByIdBox = new VBox(16);

    // Add tenant form TODO: add hall-features combobox
    private final Label addTenantLabel = new Label("Add Tenant");
    private final ToggleGroup tenantTypeGroup = new ToggleGroup();
    private final RadioButton studentRadio = new RadioButton("Student");
    private final RadioButton employeeRadio = new RadioButton("Employee");
    private final TextField newIdField = new TextField();
    private final TextField newNameField = new TextField();
    private final TextField newAgeField = new TextField();
    private final TextField newEmailField = new TextField();
    private final ComboBox<Gender> genderCombo = new ComboBox<>();
    private final CheckBox groundFloorOnly = new CheckBox("Ground floor only");
    private final Button findRoomsBtn = new Button("Find rooms");
    private final ComboBox<String> addHallCombo = new ComboBox<>();
    private final ComboBox<String> addRoomCombo = new ComboBox<>();
    private final Button addTenantBtn = new Button("➕ Add Tenant");
    private final VBox addTenantBox = new VBox(6);

    // Holds the candidate tenant between "findAvailableRooms" and "addTenant"
    private Tenant pendingTenant = null;
    // Cache of available rooms per hall, populated by Hall.findAvailableRooms(preferredType, isGroundFloor)
    private Map<String, Set<String>> availableRooms = new HashMap<>();

    // Delete tenant by ID
    private final Label deleteIdLabel = new Label("Delete Tenant");
    private final TextField deleteIdField = new TextField();
    private final Button deleteIdBtn = new Button("❌ Delete");
    private final VBox deleteByIdBox = new VBox(16);

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
        tenantId.setPromptText("Tenant ID");
        //delete tenant
        deleteIdBtn.setBackground(new Background(new BackgroundFill(Color.INDIANRED, null, null)));
        deleteIdField.setPromptText("Tenant ID");
        deleteByIdBox.getChildren().addAll(deleteIdLabel, deleteIdField, deleteIdBtn);

        //New Tenant Form
        tenantTypeGroup.getToggles().addAll(studentRadio, employeeRadio);
        studentRadio.setSelected(true);
        HBox typeRow = new HBox(8, studentRadio, employeeRadio);

        newIdField.setPromptText("ID");
        newNameField.setPromptText("Name");
        newAgeField.setPromptText("Age");
        newEmailField.setPromptText("Email");
        genderCombo.getItems().addAll(Gender.values());
        genderCombo.setPromptText("Gender");

        addHallCombo.setPromptText("Hall");
        addRoomCombo.setPromptText("Room");
        addHallCombo.setDisable(true);
        addRoomCombo.setDisable(true);
        addTenantBtn.setDisable(true);
        addTenantBox.getChildren().addAll(
                addTenantLabel, typeRow,
                newIdField, newNameField, newAgeField, newEmailField,
                genderCombo, groundFloorOnly, findRoomsBtn,
                addHallCombo, addRoomCombo, addTenantBtn
        );

        actionsBox.getChildren().addAll(addTenantBox,displayFormBox, searchByIdBox, deleteByIdBox);
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
        showRecords(store.getAllTenantRecords().values());

        // Delete tenant by ID
        deleteIdBtn.setOnAction(e -> {
            String id = deleteIdField.getText().trim();
            if (id.isEmpty()) return;

            // Confirm before deleting
            Alert confirm = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Delete tenant " + id + "?",
                    ButtonType.OK, ButtonType.CANCEL
            );
            confirm.setHeaderText(null);
            Optional<ButtonType> response = confirm.showAndWait();
            if (response.isEmpty() || response.get() != ButtonType.OK) return;

            try {
                store.removeTenant(id);
                StoreRepository.save(store, "store1.dat");
                deleteIdField.clear();
                showRecords(store.getAllTenantRecords().values());
            } catch (TenantRecordNotFound ex) {
                showError("No tenant with ID: " + id);
            } catch (RoomNotFoundException ex) {
                showError("Room not found: " + ex.getMessage());
            } catch (HallNotFoundException ex) {
                showError("Hall not found: " + ex.getMessage());
            }
        });

        // Search tenant by ID
        searchButton.setOnAction(e -> {
            String id = tenantId.getText().trim();
            if (id.isEmpty()) return;

            TenantRecord record = store.getTenantRecord(id);
            List<TenantRecord> results = new ArrayList<>();
            if (record != null) {
                results.add(record);
            }
            showRecords(results);
        });
        // Find room
        findRoomsBtn.setOnAction(e -> {
            Tenant tenant = buildTenantFromForm();
            if (tenant == null) return;

            // FEATURE IMPROVEMENT: Add hall features filter to the form, for now pass empty list
            availableRooms = store.findRoom(
                    Collections.emptyList(),
                    tenant.preferredRoomType(),
                    groundFloorOnly.isSelected()
            );

            if (availableRooms.isEmpty()) {
                showError("No available rooms match this tenant");
                return;
            }

            pendingTenant = tenant;
            addHallCombo.getItems().setAll(availableRooms.keySet());
            addHallCombo.setDisable(false);
            addRoomCombo.setDisable(false);
            addTenantBtn.setDisable(false);
        });
        //Submit Form
        addTenantBtn.setOnAction(e -> {
            if (pendingTenant == null) return;

            String hall = addHallCombo.getValue();
            String room = addRoomCombo.getValue();
            if (hall == null || room == null) {
                showError("Please pick a hall and room");
                return;
            }

            try {
                boolean added = store.addTenant(pendingTenant, hall, room);
                if (!added) {
                    showError("A tenant with that ID already exists");
                    return;
                }
                StoreRepository.save(store, "store1.dat");
                clearAddForm();
                showRecords(store.getAllTenantRecords().values());
            } catch (HallNotFoundException | RoomNotFoundException | RoomFull ex) {
                showError(ex.getMessage());
            }
        });
        // New Tenant Form hall combobox - When hall combobox changes,
        // show only the rooms available in that hall (results of store.findRooms)
        addHallCombo.setOnAction(e -> {
            String selectedHall = addHallCombo.getValue();
            addRoomCombo.getItems().clear();
            if (selectedHall != null && availableRooms.containsKey(selectedHall)) {
                addRoomCombo.getItems().addAll(availableRooms.get(selectedHall));
            }
        });

        // Display tenants based on type
        submitButton.setOnAction(e -> {
            Toggle selected = displayGroup.getSelectedToggle();

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
                new SimpleStringProperty(cell.getValue().getTenant().getId()));

        TableColumn<TenantRecord, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTenant().getClass().getSimpleName()));

        TableColumn<TenantRecord, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTenant().getName()));

        TableColumn<TenantRecord, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cell ->
                new SimpleIntegerProperty(cell.getValue().getTenant().getAge()).asObject());

        TableColumn<TenantRecord, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTenant().getEmail()));

        TableColumn<TenantRecord, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTenant().getGender().name()));

        TableColumn<TenantRecord, String> hallCol = new TableColumn<>("Hall");
        hallCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getHallName()));

        TableColumn<TenantRecord, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getRoomNumber()));

        TableColumn<TenantRecord, String> createdCol = new TableColumn<>("Joined");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        createdCol.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTenant().getCreatedAt().format(fmt)));

        resultsTable.getColumns().addAll(
                idCol, typeCol, nameCol, ageCol, emailCol, genderCol, hallCol, roomCol, createdCol
        );
        resultsTable.setPlaceholder(new Label("No tenants to show"));
        resultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private Tenant buildTenantFromForm() {
        String id = newIdField.getText().trim();
        String name = newNameField.getText().trim();
        String email = newEmailField.getText().trim();
        String ageText = newAgeField.getText().trim();
        Gender gender = genderCombo.getValue();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || ageText.isEmpty() || gender == null) {
            showError("Please fill in all tenant fields");
            return null;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            showError("Age must be a number");
            return null;
        }

        if (tenantTypeGroup.getSelectedToggle() == studentRadio) {
            return new Student(name, age, email, gender, id);
        } else {
            return new Employee(name, age, email, gender, id);
        }
    }
    // Reset Form inputs
    private void clearAddForm() {
        newIdField.clear();
        newNameField.clear();
        newAgeField.clear();
        newEmailField.clear();
        genderCombo.setValue(null);
        groundFloorOnly.setSelected(false);
        addHallCombo.getItems().clear();
        addHallCombo.setValue(null);
        addRoomCombo.getItems().clear();
        addRoomCombo.setValue(null);
        addHallCombo.setDisable(true);
        addRoomCombo.setDisable(true);
        addTenantBtn.setDisable(true);
        studentRadio.setSelected(true);
        pendingTenant = null;
        availableRooms.clear();
    }

    public Button getReturnHome() {
        return returnHome;
    }
}