package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DeleteButton<T extends Controllers> extends Button {

    public DeleteButton(T controller) {
        super();

        this.setGraphic(new StackPane());
        this.getStyleClass().add("button-small-remove");
        Image imageOk = new Image("icons/content/drawable-xxxhdpi/ic_remove_black_24dp.png",22, 22, false, false);
        this.setGraphic(new ImageView(imageOk));

        this.setOnAction(t -> {
            controller.deleteRecord();
        });

    }
}
