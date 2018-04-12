package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;

public class DeleteButton<T extends Controllers> extends Button {

    public DeleteButton(T controller) {
        super();

        this.getStyleClass().add("button-small-remove");

        this.setOnAction(t -> {
            controller.deleteRecord();
        });
    }
}
