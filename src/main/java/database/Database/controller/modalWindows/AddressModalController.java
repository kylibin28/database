package database.Database.controller.modalWindows;

import database.Database.entity.Address;
import database.Database.entity.District;
import database.Database.service.DistrictService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddressModalController {

    @FXML
    private TextField sityTextField;
    @FXML
    private TextField indexTextField;
    @FXML
    private TextField buildingNumberTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private ComboBox<District> districtComboBox;

    private Stage stage;

    private Address address;


    public void additionalInit(Stage stage, Address address, DistrictService districtService) {
        this.stage = stage;
        this.address = address;
        if (address != null) {
            sityTextField.setText(address.getStreet());
            indexTextField.setText(address.getIndex().toString());
            buildingNumberTextField.setText(address.getBuildingNumber().toString());
            streetTextField.setText(address.getStreet());
            districtComboBox.setValue(address.getDistrictName());
            districtComboBox.setItems(FXCollections.observableArrayList(districtService.findAll()));
        }
    }

    public void applyButtonAction() {

    }

    public void undoButtonAction() {
        stage.close();
    }
}
