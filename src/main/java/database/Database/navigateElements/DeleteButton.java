package database.Database.navigateElements;

import database.Database.controller.Controllers;
        import javafx.scene.control.Button;

public class DeleteButton<T extends Controllers> extends Button {

    public DeleteButton(T controller, int layoutX, int prefWidth) {
        super();

        this.setText("Удалить");

        this.setLayoutX(layoutX);
        this.setPrefWidth(prefWidth);

        this.setOnAction(t -> {
            controller.deleteRecord();
        });
    }
}
