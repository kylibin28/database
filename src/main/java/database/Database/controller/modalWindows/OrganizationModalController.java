package database.Database.controller.modalWindows;

import database.Database.controller.OrganizationController;
import database.Database.entity.Organisation;
import database.Database.service.OrganisationService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrganizationModalController {

    @FXML
    private TextField regNumTextField;
    @FXML
    private TextField nameTextField;

    private Stage stage;

    private Organisation organisation;

    private OrganizationController organizationController;

    private OrganisationService organisationService;


    public void additionalInit(OrganizationController organizationController, Stage stage, Organisation organisation, OrganisationService organisationService) {
        this.stage = stage;
        this.organisation = organisation;
        this.organisationService = organisationService;
        this.organizationController = organizationController;
        if (organisation != null) {
            regNumTextField.setText(String.valueOf(organisation.getRegistrationNumber()));
            nameTextField.setText(organisation.getName());
        }
    }

    public void applyButtonAction() {
        if (organisation == null) {
            organisation = new Organisation(Integer.valueOf(regNumTextField.getText()), nameTextField.getText());
        } else {
            organisation.setRegistrationNumber(Integer.valueOf(regNumTextField.getText()));
            organisation.setName(nameTextField.getText());
        }
        organisationService.save(organisation);
        organizationController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        stage.close();
    }
}
