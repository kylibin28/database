package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.entity.Address;
import database.Database.entity.Organisation;
import database.Database.entity.Person;
import database.Database.service.ATSService;
import database.Database.service.AddressService;
import database.Database.service.OrganisationService;
import database.Database.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class AddressController {

    @FXML
    private TableView directoryTableView;

    @Autowired
    private ATSService atsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private PersonService personService;


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
        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));

        directoryTableView.getColumns().setAll(atsId);
        directoryTableView.setItems(list);
    }

    public void AddressDirectoryAction() {
        ObservableList<Address> list = FXCollections.observableArrayList(addressService.findAll());

        TableColumn<Address, String> idColumn = new TableColumn<>("Код адреса");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("addressId"));

        TableColumn<Address, String> nameColumn = new TableColumn<>("Город");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("sity"));

        TableColumn<Address, String> phoneColumn = new TableColumn<>("Индекс");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("index"));

        TableColumn<Address, String> emailColumn = new TableColumn<>("Улица");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Address, String> buildingColumn = new TableColumn<>("Дом");
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("buildingNumber"));

        TableColumn<Address, String> districtColumn = new TableColumn<>("Название района");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("distinctName"));

        directoryTableView.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn, buildingColumn, districtColumn);
        directoryTableView.setItems(list);
    }

    public void OrganizationDirectoryAction() {

        ObservableList<Organisation> list = FXCollections.observableArrayList(organisationService.findAll());

        TableColumn<Organisation, String> registrationNumber = new TableColumn<>("Регистрационный номер");
        registrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

        TableColumn<Organisation, String> name = new TableColumn<>("Название");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        directoryTableView.getColumns().setAll(registrationNumber, name);
        directoryTableView.setItems(list);
    }

    public void PersoneDirectoryAction() {

        ObservableList<Person> list = FXCollections.observableArrayList(personService.findAll());

        TableColumn<Person, String> pasportNumber = new TableColumn<>("Номер паспорта");
        pasportNumber.setCellValueFactory(new PropertyValueFactory<>("pasportNumber"));

        TableColumn<Person, String> FIO = new TableColumn<>("ФИО");
        FIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));

        TableColumn<Person, String> privilege = new TableColumn<>("Наличие льгот");
        privilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));

        directoryTableView.getColumns().setAll(pasportNumber, FIO, privilege);
        directoryTableView.setItems(list);
    }
}
