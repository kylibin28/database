package database.Database.navigateElements;

import database.Database.controller.ATSController;
import javafx.scene.control.TextField;

public class ATSNumberTextField extends TextField {

    public ATSNumberTextField(ATSController atsController) {
        super();

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(""))
                atsController.setApplyButtonDisable(false);

        });
        this.getStyleClass().add("text-field-odd");
    }
}
