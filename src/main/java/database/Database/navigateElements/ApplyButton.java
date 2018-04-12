package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ApplyButton<T extends Controllers> extends Button {

    public ApplyButton(T controller) {
        super();

        this.setGraphic(new StackPane());
        this.getStyleClass().add("button-small-apply");

        Image imageOk = new Image("icons/action/drawable-xxxhdpi/ic_done_black_24dp.png",22, 22, false, false);
        this.setGraphic(new ImageView(imageOk));


        this.setOnAction(t -> {
            controller.apply();
        });

    }
}
