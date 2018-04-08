package database.Database.navigateElements;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class CustomComboBox<T> extends ComboBox {

    public CustomComboBox(ObservableList<T> items, int layoutX, int prefWidth) {
        super(items);

        this.setLayoutX(layoutX);
        this.setPrefWidth(prefWidth);
    }
}
