package database.Database.controller.modalWindows;

import database.Database.entity.ATS;
import database.Database.entity.Phone;
import database.Database.service.ATSService;
import database.Database.service.PhoneService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PhoneModalController {
    @FXML
    private TextField phoneNumTextField;
    @FXML
    private CheckBox isConnectCheckBox;
    @FXML
    private ComboBox atsComboBox;

    private PhoneController phoneController;

    private Stage stage;

    private Phone phone;

    private PhoneService phoneService;

    public void additionalInit(PhoneController phoneController, Stage stage, Phone phone, PhoneService phoneService, ATSService atsService) {
        this.phoneController = phoneController;
        this.stage = stage;
        this.phone = phone;
        this.phoneService = phoneService;

        if (phone != null) {
            phoneNumTextField.setText(String.valueOf(phone.getPhoneNumber()));
            isConnectCheckBox.setSelected(phone.getConnect());
            atsComboBox.setValue(phone.getAtsId());
        }
        atsComboBox.setItems(FXCollections.observableArrayList(atsService.findAll()));
    }

    public void applyButtonAction() {
        if (phone == null) {
            phone = new Phone(Integer.valueOf(phoneNumTextField.getText()), isConnectCheckBox.isSelected(), (ATS) atsComboBox.getValue());
        } else {
            phoneService.delete(phone);
            phone.setPhoneNumber(Integer.valueOf(phoneNumTextField.getText()));
            phone.setConnect(isConnectCheckBox.isSelected());
            phone.setAtsId((ATS) atsComboBox.getValue());
        }
        phoneService.save(phone);
        phoneController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        phoneController.setTable();
        stage.close();
    }
}
