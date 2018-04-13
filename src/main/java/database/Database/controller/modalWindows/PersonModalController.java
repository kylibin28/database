package database.Database.controller.modalWindows;

import database.Database.controller.PersonController;
import database.Database.entity.Person;
import database.Database.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PersonModalController {

    @FXML
    private TextField pasportTextField;
    @FXML
    private TextField fioTextField;
    @FXML
    private CheckBox privilegeCheckBox;

    private PersonController personController;


    private Stage stage;
    private Person person;
    private PersonService personService;

    public void additionalInit(PersonController personController, Stage stage, Person person, PersonService personService) {
        this.stage = stage;
        this.person = person;
        this.personService = personService;
        this.personController = personController;
        if (person != null) {
            pasportTextField.setText(String.valueOf(person.getPasportNumber()));
            fioTextField.setText(person.getFIO());
            privilegeCheckBox.setSelected(person.getPrivilege());
        }
    }

    public void applyButtonAction() {
        if (person == null) {
            person = new Person(Integer.valueOf(pasportTextField.getText()), fioTextField.getText(), privilegeCheckBox.isSelected());
        } else {
            person.setPasportNumber(Integer.valueOf(pasportTextField.getText()));
            person.setFIO(fioTextField.getText());
            person.setPrivilege(privilegeCheckBox.isSelected());
        }
        personService.save(person);
        personController.setTable();
        stage.close();
    }

    public void undoButtonAction() {
        personController.setTable();
        stage.close();
    }


}
