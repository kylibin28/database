package database.Database.controller.modalWindows;

import database.Database.controller.AddressController;
import database.Database.entity.Address;
import database.Database.entity.District;
import database.Database.service.AddressService;
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

    private AddressController addressController;

    private AddressService addressService;

    private DistrictService districtService;


    public void additionalInit(AddressController addressController, Stage stage, Address address, AddressService addressService, DistrictService districtService) {
        this.stage = stage;
        this.address = address;
        this.addressService = addressService;
        this.districtService = districtService;
        this.addressController = addressController;
        if (address != null) {
            sityTextField.setText(address.getStreet());
            indexTextField.setText(address.getIndex().toString());
            buildingNumberTextField.setText(address.getBuildingNumber().toString());
            streetTextField.setText(address.getStreet());
            districtComboBox.setValue(address.getDistrictName());
        }
        districtComboBox.setItems(FXCollections.observableArrayList(districtService.findAll()));
    }

    public void applyButtonAction() {
        if (address == null) {
            address = new Address(sityTextField.getText(),
                    Integer.valueOf(indexTextField.getText()), streetTextField.getText(),
                    Integer.valueOf(buildingNumberTextField.getText()), districtComboBox.getValue());
        } else {
            address.setSity(sityTextField.getText());
            address.setIndex(Integer.valueOf(indexTextField.getText()));
            address.setBuildingNumber(Integer.valueOf(buildingNumberTextField.getText()));
            address.setStreet(streetTextField.getText());
            address.setDistrictName(districtComboBox.getValue());
        }
        addressService.save(address);
        addressController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        addressController.setTable();
        stage.close();
    }
}
