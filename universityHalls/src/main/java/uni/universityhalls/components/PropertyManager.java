package uni.universityhalls.components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uni.universityhalls.FEATURE;
import uni.universityhalls.Hall;
import uni.universityhalls.Store;
import uni.universityhalls.StoreRepository;

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
        VBox container = new VBox();
        Hall hall = store.getHall(hallName);
        Label label = new Label(hallName);
        HBox stats = new HBox();

        VBox roomStats = new VBox();
        VBox bedsStats = new VBox();
        //Room Stats
        Label totalRooms = new Label();
        int total = hall.getRoomsNumbers().size();
        totalRooms.setText("Total rooms: " + total);
        Label emptyRooms = new Label();
        int empty = hall.countEmptyRooms();
        emptyRooms.setText("Empty rooms: " + empty);
        roomStats.getChildren().addAll(totalRooms, emptyRooms);
        // Beds Stats
        Label totalBeds = new Label();
        Label emptyBeds = new Label();
        int tBeds = hall.getTotalBeds();
        totalBeds.setText("Total beds: " + tBeds);
        int eBeds = hall.getEmptyBeds();
        emptyBeds.setText("Empty beds: " + eBeds);
        bedsStats.getChildren().addAll(totalBeds, emptyBeds);
        stats.getChildren().addAll(roomStats, bedsStats);

        VBox hallFeatures = new VBox();
        Label featuresLabel = new Label("Hall features: ");

        HBox featOptions = new HBox();
        Label featureName = new Label("Feature name: ");
        ComboBox <String> addFeature = new ComboBox<>();
        Button addFeatureBtn = new Button("Add feature");
        addFeature.setValue("--New Hall Feature--");
        featOptions.getChildren().addAll(featureName,addFeature, addFeatureBtn);


        for (FEATURE feature : FEATURE.values()) {
            addFeature.getItems().add(feature.name());
        }
        HBox features = new HBox();


        hallFeatures.getChildren().addAll(featOptions,featuresLabel, features);

        container.getChildren().addAll(label,stats,hallFeatures);

        addFeatureBtn.setOnAction(e -> {
            if(!addFeature.getValue().equals("--New Hall Feature--") ){

                features.getChildren().add(new Text(addFeature.getValue()));
            }
        });

        return container;

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
