package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;

public class ApplyButton<T extends Controllers> extends Button {

    public ApplyButton(T controller) {
        super();

        this.getStyleClass().add("button-small-apply");

        this.setOnAction(t -> {
            controller.apply();
        });
    }
}
