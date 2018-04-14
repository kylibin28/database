package database.Database.controller.modalWindows;

import database.Database.controller.DistrictController;
import database.Database.entity.District;
import database.Database.service.DistrictService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DistrictModalController {
    @FXML
    private TextField districtNameTextField;

    private DistrictController districtController;

    private Stage stage;

    private District district;


    private DistrictService districtService;

    public void additionalInit(DistrictController districtController, Stage stage, District district, DistrictService districtService) {
        this.districtController = districtController;
        this.stage = stage;
        this.district = district;
        this.districtService = districtService;
    }

    public void applyButtonAction() {
        if (district == null) {
            district = new District(districtNameTextField.getText());
        } else {
            districtService.delete(district);
            district.setDistrictName(districtNameTextField.getText());
        }
        districtService.save(district);
        districtController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        districtController.setTable();
        stage.close();
    }
}
