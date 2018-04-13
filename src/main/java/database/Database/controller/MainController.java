package database.Database.controller;

import database.Database.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController {


    @FXML
    private TableView atsTableView;


    @Autowired
    private ATSService atsService;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DistrictService districtService;


    private AddressController addressController;
    private PersonController personController;
    private ATSController atsController;
    private OrganizationController organizationController;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        AddressDirectoryAction();
    }



    public void ATSDirectoryAction() {
        atsController = new ATSController(atsTableView, atsService);
        atsController.setTable();
    }

    public void AddressDirectoryAction() {
        addressController = new AddressController(atsTableView, addressService, districtService);
        addressController.setTable();
    }

    public void OrganizationDirectoryAction() {
        organizationController = new OrganizationController(atsTableView, organisationService);
        organizationController.setTable();
    }

    public void PersonDirectoryAction() {
        personController = new PersonController(atsTableView, personService);
        personController.setTable();
    }
}
