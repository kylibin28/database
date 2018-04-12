package database.Database.navigateElements;

import database.Database.controller.Controllers;
import javafx.scene.control.TextField;

public class ATSNumberTextField<T extends Controllers> extends TextField {

    public ATSNumberTextField(T controller) {
        super();

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals(""))
                controller.setApplyButtonDisable(false);
        });

        this.getStyleClass().add("text-field-odd");
    }
}
