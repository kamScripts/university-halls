package uni.universityhalls.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashboardTile extends VBox {
        private final Label tileTitle;
        private final Text tileText;
        private final int value;

        public DashboardTile(String titleIn, String textIn, int valueIn){
            tileTitle = new Label(titleIn);
            value = valueIn;
            tileText = new Text(textIn + ": "+value);

        }
}
