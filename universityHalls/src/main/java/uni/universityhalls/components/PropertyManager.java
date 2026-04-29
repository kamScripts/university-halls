package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uni.universityhalls.*;

import java.util.Optional;

public class PropertyManager extends VBox {
    private final Store store;
    private final HBox hallNav = new HBox(16);
    private final ComboBox <String> selectHall = new ComboBox<>();
    private final Button returnHome = new Button("⬅ Back");
    private final VBox hallSection = new VBox();



    public PropertyManager(Store storeIn) {
        store = storeIn;
        selectHall.getItems().addAll(store.getAllHalls().keySet());
        selectHall.setValue("Select building");
        Label navLabel = new Label("Select hall: ");
        Button newHallBtn = new Button("➕ New hall");
        Button deleteHallBtn = new Button("❌ delete hall");
        hallNav.getChildren().addAll(navLabel,selectHall, newHallBtn, deleteHallBtn, returnHome);
        hallNav.setPadding(new Insets(24));
        hallNav.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        )));
        hallNav.setAlignment(Pos.CENTER);



        this.getChildren().addAll(hallNav,hallSection);

        newHallBtn.setOnAction(e -> {
            String hallName = getHallName();
            if (!hallName.isEmpty()) {
                store.addHall(new Hall(hallName));
                StoreRepository.save(store, "store1.dat");
                refreshHallList();
                selectHall.setValue(hallName); // auto-select the new one
            }
        });
        selectHall.setOnAction(e -> {
            hallSection.getChildren().setAll(createHallSection(selectHall.getValue()));
        });
        deleteHallBtn.setOnAction(e -> {
            String hallName = selectHall.getValue();
            store.deleteHall(hallName);
            // ----- Override current file |-> make copy or db ? -----
            StoreRepository.save(store, "store1.dat");
            refreshHallList();
            hallSection.getChildren().clear();
        });
    }

    private VBox createHallSection(String hallName) {
        VBox container = new VBox(12);
        container.setPadding(new Insets(16));
        // --- Current Hall ---
        Hall hall = store.getHall(hallName);
        Label label = new Label(hallName);
        label.setFont(new Font("Arial", 16));

        // --- Stats ---
        HBox stats = new HBox(24);
        VBox roomStats = new VBox(4);
        VBox bedsStats = new VBox(4);

        Label totalRooms = new Label("Total rooms: " + hall.getRoomsNumbers().size());
        Label emptyRooms = new Label("Empty rooms: " + hall.countEmptyRooms());
        roomStats.getChildren().addAll(totalRooms, emptyRooms);

        Label totalBeds = new Label("Total beds: " + hall.getTotalBeds());
        Label emptyBeds = new Label("Empty beds: " + hall.getEmptyBeds());
        bedsStats.getChildren().addAll(totalBeds, emptyBeds);

        stats.getChildren().addAll(roomStats, bedsStats);

        // --- Features ---
        VBox hallFeatures = new VBox(8);
        Label featuresLabel = new Label("Hall features: ");
        featuresLabel.setFont(new Font("Arial", 16));

        HBox features = new HBox(8);
        // --- Load the hall of residences features ---
        for (FEATURE f : hall.getFeatures()) {
            features.getChildren().add(new Text(f.name()));
        }
        // --- Hall's features menu ---
        HBox featOptions = new HBox(16);
                featOptions.setPadding(new Insets(24,8,24,8));
        Label featureName = new Label("Hall feature name: ");
        featureName.setFont(new Font("Arial", 12));
        ComboBox<String> featureCombo = new ComboBox<>();
        Button addFeatureBtn = new Button("➕ Add feature");
        Button removeFeatureBtn = new Button("❌ Remove feature");
        //--- FEATURE select options ---
        for (FEATURE feature : FEATURE.values()) {
            featureCombo.getItems().add(feature.name());
        }
        // placeholder
        featureCombo.setValue("--Select feature--");

        featOptions.getChildren().addAll(
                featureName, featureCombo, addFeatureBtn, removeFeatureBtn
        );

        hallFeatures.getChildren().addAll(featOptions, featuresLabel, features);

        container.getChildren().addAll(label, stats, hallFeatures, createRoomSection(hall));

        // --- Add feature ---
        addFeatureBtn.setOnAction(e -> {
            String val = featureCombo.getValue();
            if (val == null || val.equals("--Select feature--")) return;

            FEATURE candidate = FEATURE.valueOf(val);
            // Set add method returns false if value present
            if (hall.addFeature(candidate)) {
                features.getChildren().add(new Text(val));
                StoreRepository.save(store, "store1.dat");
            }
        });

        // --- Remove feature ---
        removeFeatureBtn.setOnAction(e -> {
            String val = featureCombo.getValue();
            if (val == null || val.equals("--Select feature--")) return;

            FEATURE candidate = FEATURE.valueOf(val);
            // Collection removeIf method returns false if not present
            if (hall.removeFeature(candidate)) {
                features.getChildren().removeIf(node ->
                        node instanceof Text && ((Text) node).getText().equals(val)
                );
                StoreRepository.save(store, "store1.dat");
            }
        });

        return container;
    }
    private VBox createRoomSection(Hall hall) {
        VBox container = new VBox(12);
        //-- Room Navigation ---
        HBox roomForm = new HBox(16);
        roomForm.setAlignment(Pos.CENTER);
        Label header = new Label("Room manager");
        header.setFont(new Font("Arial", 12));
        Label roomNum = new Label("Select room: ");
        ComboBox<String> roomCombo = new ComboBox<>();
        roomCombo.getItems().addAll(hall.getRoomsNumbers());
        roomCombo.setValue("-- room no --");
        Button newRoom = new Button("➕ New room");
        roomForm.getChildren().addAll(header, roomNum, roomCombo, newRoom);

        //--- Container for the details panel, refreshed on change---
        VBox detailView = new VBox();

        container.getChildren().addAll(roomForm, detailView);
        //--- Room select event listener - show room detail view ---
        roomCombo.setOnAction(e -> {
            String selected = roomCombo.getValue();
            if (selected == null || selected.equals("-- room no --")) return;
            detailView.getChildren().setAll(buildRoomDetails(selected, hall));
        });
        //--- Add new room to current hall ---
        newRoom.setOnAction(e -> {
            Room created = promptNewRoom(hall);
            if (created != null) {
                hall.addRoom(created);
                StoreRepository.save(store, "store1.dat");
                roomCombo.getItems().setAll(hall.getRoomsNumbers());
                roomCombo.setValue(created.getRoomNumber());
                detailView.getChildren().setAll(buildRoomDetails(created.getRoomNumber(), hall));
            }
        });
        container.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        )));
        container.setPadding(new Insets(8));
        container.setAlignment(Pos.CENTER);

        return container;
    }

    private VBox buildRoomDetails(String roomNum, Hall hall) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(12, 0, 0, 0));

        Room room = hall.getRoom(roomNum);
        if (room == null) return box;

        Label title = new Label("Room " + roomNum);

        // --- Read-only stats ---
        Label occupancy = new Label("Occupancy: " + room.getCount() + " / " + room.getCapacity());
        Label floor = new Label("Ground floor: " + (room.onGroundFloor() ? "yes" : "no"));

        // --- Editable fields ---
        HBox capacityRow = new HBox(8);
        Label capLabel = new Label("Capacity: ");
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, room.getCapacity());
        Button saveCapacity = new Button("Update");
        capacityRow.getChildren().addAll(capLabel, capacitySpinner, saveCapacity);

        HBox typeRow = new HBox(8);
        Label typeLabel = new Label("Room type: ");
        ComboBox<ROOM_TYPE> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(ROOM_TYPE.values());
        typeCombo.setValue(room.getRoomType());
        Button saveType = new Button("Update");
        typeRow.getChildren().addAll(typeLabel, typeCombo, saveType);

        // --- Actions ---
        HBox actions = new HBox(8);
        //--- Restore tenants to 0 and room type to EMPTY ---
        Button clearBtn = new Button("♻ Restore default settings");
        actions.getChildren().add(clearBtn);

        box.getChildren().addAll(title, occupancy, floor, capacityRow, typeRow, actions);

        // --- Handlers ---
        saveCapacity.setOnAction(e -> {
            int newCap = capacitySpinner.getValue();
            // Don't shrink below current occupancy
            if (newCap < room.getCount()) {
                capacitySpinner.getValueFactory().setValue(room.getCapacity());
                return;
            }
            room.setCapacity(newCap);
            StoreRepository.save(store, "store1.dat");
            occupancy.setText("Occupancy: " + room.getCount() + " / " + room.getCapacity());
        });

        saveType.setOnAction(e -> {
            room.setRoomType(typeCombo.getValue());
            StoreRepository.save(store, "store1.dat");
        });

        clearBtn.setOnAction(e -> {
            room.clearRoom();
            StoreRepository.save(store, "store1.dat");
            typeCombo.setValue(ROOM_TYPE.EMPTY);
            occupancy.setText("Occupancy: " + room.getCount() + " / " + room.getCapacity());
        });

        return box;
    }

    private Room promptNewRoom(Hall hall) {
        // ---Custom Dialog modal with 4 fields ---
        Dialog<Room> dialog = new Dialog<>();
        dialog.setTitle("Add new room");
        dialog.setHeaderText("Enter room details");

        ButtonType createBtn = new ButtonType("➕ Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createBtn, ButtonType.CANCEL);

        TextField roomNumField = new TextField();
        roomNumField.setPromptText("Room number");
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, 1);
        ComboBox<ROOM_TYPE> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(ROOM_TYPE.values());
        typeCombo.setValue(ROOM_TYPE.EMPTY);
        CheckBox groundFloor = new CheckBox("Ground floor");

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(16));
        grid.add(new Label("Room number:"), 0, 0); grid.add(roomNumField, 1, 0);
        grid.add(new Label("Capacity:"),    0, 1); grid.add(capacitySpinner, 1, 1);
        grid.add(new Label("Type:"),        0, 2); grid.add(typeCombo, 1, 2);
        grid.add(groundFloor, 1, 3);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button != createBtn) return null;
            String num = roomNumField.getText().trim();
            if (num.isEmpty() || hall.getRoom(num) != null) return null; // reject empty or duplicate
            return new Room(num, capacitySpinner.getValue(), typeCombo.getValue(), groundFloor.isSelected());
        });

        return dialog.showAndWait().orElse(null);
    }

    private String getHallName() {
        //Modal Form +New Hall
        TextInputDialog hallDialog = new TextInputDialog("Hall name...");
        hallDialog.setTitle("Add new hall");
        hallDialog.setHeaderText("Add new Hall");
        hallDialog.setGraphic(null);
        Optional<String> response = hallDialog.showAndWait();
        //Functional style expression
        return response.orElse("");
    }
    private void refreshHallList() {
        selectHall.getItems().setAll(store.getAllHalls().keySet());
    }
    // Expose to higher scope - for event handlers
    public Button getReturnHome() {
        return returnHome;
    }
}
