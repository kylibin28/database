package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;

public class UndoButton<T extends Controllers> extends Button{

    public UndoButton(T controller) {
        super();

        this.getStyleClass().add("button-small-cancel");

        this.setOnAction(t -> {
            controller.undo();
        });
    }
}
