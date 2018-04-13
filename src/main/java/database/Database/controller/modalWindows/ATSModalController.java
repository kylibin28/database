package database.Database.controller.modalWindows;

import database.Database.controller.ATSController;
import database.Database.entity.ATS;
import database.Database.service.ATSService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ATSModalController {

    @FXML
    private TextField atsTextField;

    private ATSController atsController;

    private Stage stage;

    private ATS ats;

    private ATSService atsService;

    public void additionalInit(ATSController atsController, Stage stage, ATS ats, ATSService atsService) {
        this.atsController = atsController;
        this.ats = ats;
        this.atsService = atsService;
        this.stage = stage;
        if (ats != null) {
            atsTextField.setText(String.valueOf(ats.getAtsId()));
        }

    }

    public void applyButtonAction() {
        if (ats == null) {
            ats = new ATS(Integer.valueOf(atsTextField.getText()));
        } else {
            atsService.delete(ats);
            ats.setAtsId(Integer.valueOf(atsTextField.getText()));
        }
        atsService.save(ats);
        atsController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        atsController.setTable();
        stage.close();
    }
}
