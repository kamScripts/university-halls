package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uni.universityhalls.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyManager extends VBox {
    private Store store;
    private HBox hallNav = new HBox();
    private Label navLabel= new Label("Select hall: ");
    private ComboBox <String> selectHall = new ComboBox<>();
    private Button newHallBtn = new Button("➕ New hall");
    private final Button returnHome = new Button("⬅ Back");

    private VBox hallSection = new VBox();



    public PropertyManager(Store storeIn) {
        store = storeIn;
        selectHall.getItems().addAll(store.getAllHalls().keySet());
        selectHall.setValue("Select building");
        hallNav.getChildren().addAll(navLabel,selectHall, newHallBtn, returnHome);
        hallNav.setPadding(new Insets(24));
        hallNav.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        )));



        this.getChildren().addAll(hallNav,hallSection);

        newHallBtn.setOnAction(e -> {
            String hallName = getHallName();
            if (!hallName.isEmpty()) {
                store.addHall(new Hall(hallName));
                StoreRepository.save(store, "store1.dat");
                refreshHallList();
                selectHall.setValue(hallName); // optional: auto-select the new one
            }
        });
        selectHall.setOnAction(e -> {
            hallSection.getChildren().setAll(createHallSection(selectHall.getValue()));
        });
    }

    private VBox createHallSection(String hallName) {
        VBox container = new VBox(12);
        container.setPadding(new Insets(16));

        Hall hall = store.getHall(hallName);
        Label label = new Label(hallName);

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

        HBox features = new HBox(8);
        // Render any features the hall already has
        for (FEATURE f : hall.getFeatures()) {
            features.getChildren().add(new Text(f.name()));
        }

        HBox featOptions = new HBox(8);
        Label featureName = new Label("Feature name: ");
        ComboBox<String> featureCombo = new ComboBox<>();
        Button addFeatureBtn = new Button("Add feature");
        Button removeFeatureBtn = new Button("Remove feature");

        for (FEATURE feature : FEATURE.values()) {
            featureCombo.getItems().add(feature.name());
        }
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
        HBox roomForm = new HBox(24);
        Label header = new Label("Room manager");
        Label roomNum = new Label("Select room: ");
        ComboBox<String> roomCombo = new ComboBox<>();
        roomCombo.getItems().addAll(hall.getRoomsNumbers());
        roomCombo.setValue("-- room no --");
        Button newRoom = new Button("➕ New room");
        roomForm.getChildren().addAll(header, roomNum, roomCombo, newRoom);
        container.getChildren().add(roomForm);

        roomCombo.setOnAction(e -> {getRoomdetails(roomCombo.getValue(), hall);});

        return container;
    }

    private VBox getRoomdetails(String roomNum, Hall hall) {
        Label roomNumLabel = new Label("Room "+roomNum);
        Room room = hall.getRoom(roomNum);
        TableView<Room> roomTable = new TableView<>();
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
