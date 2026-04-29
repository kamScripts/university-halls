package uni.universityhalls.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uni.universityhalls.Hall;
import uni.universityhalls.Store;
import uni.universityhalls.StoreRepository;

import java.util.Optional;

public class PropertyManager extends VBox {
    private Store store;
    private HBox hallNav = new HBox();
    private ComboBox <String> selectHall = new ComboBox<>();
    private Button newHallBtn = new Button("➕ New hall");
    private final Button returnHome = new Button("⬅ Back");



    public PropertyManager(Store storeIn) {
        store = storeIn;
        selectHall.getItems().addAll(store.getAllHalls().keySet());
        selectHall.setValue("Select building");
        hallNav.getChildren().addAll(selectHall, newHallBtn, returnHome);



        this.getChildren().addAll(hallNav);

        newHallBtn.setOnAction(e -> {
            String hallName = getHallName();
            if (!hallName.isEmpty()) {
                store.addHall(new Hall(hallName));
                StoreRepository.save(store, "store1.dat");
                refreshHallList();
                selectHall.setValue(hallName); // optional: auto-select the new one
            }
        });
    }



    private String getHallName() {
        //Modal Form +New Hall
        TextInputDialog hallDialog = new TextInputDialog("New hall name...");
        hallDialog.setTitle("Add new hall");
        Optional<String> response = hallDialog.showAndWait();
        //Function style expression
        return response.orElse("");
    }
    private void refreshHallList() {
        selectHall.getItems().setAll(store.getAllHalls().keySet());
    }

    public Button getReturnHome() {
        return returnHome;
    }
}
