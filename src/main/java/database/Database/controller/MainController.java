package database.Database.controller;

import database.Database.entity.*;
import database.Database.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController {

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;

    @Autowired
    private ATSService atsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private DistrictService districtService;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        AddressDirectoryAction();
    }

    public void ATSDirectoryAction() {

        ObservableList<ATS> list = FXCollections.observableArrayList(atsService.findAll());

        TableColumn<ATS, String> atsId = new TableColumn<>("Номер АТС");
        atsId.setPrefWidth(100);
        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));

        directoryTableView.getColumns().setAll(atsId);
        directoryTableView.setItems(list);


        TextField additionTextField = new TextField();
        additionTextField.setPrefWidth(100);
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            atsService.save(new ATS(Integer.valueOf(additionTextField.getText())));
            ATSDirectoryAction();
        });
        addButton.setLayoutX(110);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(additionTextField, addButton);
    }

    public void AddressDirectoryAction() {
        ObservableList<Address> list = FXCollections.observableArrayList(addressService.findAll());

        TableColumn<Address, String> idColumn = new TableColumn<>("Код адреса");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        idColumn.setPrefWidth(70);

        TableColumn<Address, String> nameColumn = new TableColumn<>("Город");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("sity"));
        nameColumn.setPrefWidth(150);

        TableColumn<Address, String> indexColumn = new TableColumn<>("Индекс");
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        indexColumn.setPrefWidth(100);

        TableColumn<Address, String> streetColumn = new TableColumn<>("Улица");
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        streetColumn.setPrefWidth(150);

        TableColumn<Address, String> buildingColumn = new TableColumn<>("Дом");
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("buildingNumber"));
        buildingColumn.setPrefWidth(50);

        TableColumn<Address, String> districtColumn = new TableColumn<>("Название района");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("distinctName"));
        districtColumn.setPrefWidth(150);

        directoryTableView.getColumns().setAll(idColumn, nameColumn, indexColumn, streetColumn, buildingColumn, districtColumn);
        directoryTableView.setItems(list);

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(70);
        nameTextField.setPrefWidth(150);

        TextField indexTextField = new TextField();
        indexTextField.setLayoutX(220);
        indexTextField.setPrefWidth(100);

        TextField streetTextField = new TextField();
        streetTextField.setLayoutX(320);
        streetTextField.setPrefWidth(150);

        TextField buildingTextField = new TextField();
        buildingTextField.setLayoutX(470);
        buildingTextField.setPrefWidth(50);

        ObservableList<District> districtObservableList = FXCollections.observableArrayList(districtService.findAll());
        ComboBox districtComboBox = new ComboBox(districtObservableList);
        districtComboBox.setLayoutX(520);
        districtComboBox.setPrefWidth(150);

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            addressService.save(new Address(nameTextField.getText(),
                    Integer.valueOf(indexTextField.getText()), streetTextField.getText(),
                    Integer.valueOf(buildingTextField.getText()), (District)districtComboBox.getValue()));
            AddressDirectoryAction();
        });
        addButton.setLayoutX(670);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(nameTextField, indexTextField, streetTextField, buildingTextField, districtComboBox, addButton);
    }

    public void OrganizationDirectoryAction() {

        ObservableList<Organisation> list = FXCollections.observableArrayList(organisationService.findAll());

        TableColumn<Organisation, String> registrationNumber = new TableColumn<>("Регистрационный номер");
        registrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        registrationNumber.setPrefWidth(100);

        TableColumn<Organisation, String> name = new TableColumn<>("Название");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(150);

        directoryTableView.getColumns().setAll(registrationNumber, name);
        directoryTableView.setItems(list);

        TextField registrationNumberField = new TextField();
        registrationNumberField.setPrefWidth(100);

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(100);
        nameTextField.setPrefWidth(150);

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            organisationService.save(new Organisation(Integer.valueOf(registrationNumberField.getText()), nameTextField.getText()));
            OrganizationDirectoryAction();
        });
        addButton.setLayoutX(260);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(registrationNumberField, nameTextField, addButton);
    }

    public void PersoneDirectoryAction() {

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
            PersoneDirectoryAction();
        });
        addButton.setLayoutX(460);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(pasportNumberTextField, FIOTextField, privilegeCheckBox, addButton);
    }
}
