package database.Database.controller;

import database.Database.repository.*;
import database.Database.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController {

    @FXML
    private TableView directoryTableView;
    @FXML
    private Pane additionPane;


    @Autowired
    private ATSRepository atsRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DistrictRepository districtRepository;


    private AddressController addressController;
    private PersonController personController;
    private ATSController atsController;
    private OrganizationController organizationController;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        addressController = new AddressController(directoryTableView, additionPane, addressRepository, districtRepository);
        personController = new PersonController(directoryTableView, additionPane, personService);
        organizationController = new OrganizationController(directoryTableView, additionPane, organisationRepository);
        atsController = new ATSController(directoryTableView, additionPane, atsRepository);
        AddressDirectoryAction();
    }

    public void ATSDirectoryAction() {
        atsController.setTable();
    }

    public void AddressDirectoryAction() {
        addressController.setTable();
    }

    public void OrganizationDirectoryAction() {
       organizationController.setOrganizationTable();
    }

    public void PersonDirectoryAction() {
        personController.setTable();
    }
}
