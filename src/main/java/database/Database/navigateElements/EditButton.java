package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class EditButton<T extends Controllers> extends Button {

    public EditButton(T controller, int layoutX, int prefWidth) {
        super();

        this.setText("Редактировать");

        this.setLayoutX(layoutX);
        this.setPrefWidth(prefWidth);

        this.setOnAction(t -> {
            controller.addRecord();
        });

        this.setGraphic(new StackPane());
    }
}
