package database.Database.controller;

import database.Database.entity.Person;
import database.Database.repository.PersonRepository;
import database.Database.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class PersonController implements Controllers{

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;

    private PersonService personService;

    public PersonController (TableView directoryTableView, Pane additionPane, PersonService personService) {
        this.directoryTableView = directoryTableView;
        this.additionPane = additionPane;
        this.personService = personService;
    }

    @Override
    public void setTable(){

        ObservableList<Person> list = FXCollections.observableArrayList(personService.findAll());

        TableColumn<Person, String> pasportNumber = new TableColumn<>("Номер паспорта");
        pasportNumber.setCellValueFactory(new PropertyValueFactory<>("pasportNumber"));
        pasportNumber.setPrefWidth(100);

        TableColumn<Person, String> FIO = new TableColumn<>("ФИО");
        FIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        FIO.setPrefWidth(250);

        TableColumn<Person, String> privilege = new TableColumn<>("Наличие льгот");
        privilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        privilege.setPrefWidth(100);

        directoryTableView.getColumns().setAll(pasportNumber, FIO, privilege);
        directoryTableView.setItems(list);

        TextField pasportNumberTextField = new TextField();
        pasportNumberTextField.setPrefWidth(100);

        TextField FIOTextField = new TextField();
        FIOTextField.setLayoutX(100);
        FIOTextField.setPrefWidth(250);

        CheckBox privilegeCheckBox = new CheckBox();
        privilegeCheckBox.setLayoutX(400);

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            personService.save(new Person(Integer.valueOf(pasportNumberTextField.getText()), FIOTextField.getText(), privilegeCheckBox.isSelected()));
            setTable();
        });
        addButton.setLayoutX(460);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(pasportNumberTextField, FIOTextField, privilegeCheckBox, addButton);
    }


    @Override
    public void addRecord() {
//        personService.save(new ATS(Integer.valueOf(additionTextField.getText())));
        setTable();
    }

    @Override
    public void deleteRecord() {
//        personService.delete((ATS) directoryTableView.getSelectionModel().getSelectedItem());
        setTable();
    }

    @Override
    public void updateRecord() {
//        additionTextField.setText(String.valueOf(((ATS) directoryTableView.getSelectionModel().getSelectedItem()).getAtsId()));
    }
}
