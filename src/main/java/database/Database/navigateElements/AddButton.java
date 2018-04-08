package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;

public class AddButton<T extends Controllers> extends Button {

    public AddButton (T controller, int layoutX, int prefWidth) {
        super();

        this.setText("Добавить");

        this.setLayoutX(layoutX);
        this.setPrefWidth(prefWidth);

        this.setOnAction(t -> {
            controller.addRecord();
        });
    }


}