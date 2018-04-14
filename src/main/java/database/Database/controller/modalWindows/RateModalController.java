package database.Database.controller.modalWindows;

import database.Database.controller.RateController;
import database.Database.entity.Rate;
import database.Database.service.RateService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RateModalController {

    @FXML
    private TextField yearTextField;
    @FXML
    private TextField monthTextField;
    @FXML
    private TextField valueTextField;

    private RateController rateController;

    private Stage stage;

    private Rate rate;

    private RateService rateService;

    public void additionalInit(RateController rateController, Stage stage, Rate rate, RateService rateService) {
        this.rateController = rateController;
        this.rate = rate;
        this.rateService = rateService;
        this.stage = stage;
        if (rate != null) {
            yearTextField.setText(String.valueOf(rate.getYear()));
            monthTextField.setText(String.valueOf(rate.getMonth()));
            valueTextField.setText(String.valueOf(rate.getValue()));
        }

    }

    public void applyButtonAction() {
        if (rate == null) {
            rate = new Rate(Integer.valueOf(yearTextField.getText()),Integer.valueOf(monthTextField.getText()),
                    Integer.valueOf(valueTextField.getText()));
        } else {
            rate.setYear(Integer.valueOf(yearTextField.getText()));
            rate.setMonth(Integer.valueOf(monthTextField.getText()));
            rate.setValue(Integer.valueOf(valueTextField.getText()));
        }
        rateService.save(rate);
        rateController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        rateController.setTable();
        stage.close();
    }
}
