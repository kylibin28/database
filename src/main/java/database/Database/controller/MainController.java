package database.Database.controller;

import database.Database.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController {


    @FXML
    private TableView tableView;

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
    @Autowired
    private RateService rateService;


    private AddressController addressController;
    private PersonController personController;
    private ATSController atsController;
    private OrganizationController organizationController;
    private DistrictController districtController;
    private RateController rateController;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        firstView();
    }


    public void ATSDirectoryAction() {
        atsController = new ATSController(tableView, atsService);
        atsController.setTable();
    }

    public void AddressDirectoryAction() {
        addressController = new AddressController(tableView, addressService, districtService);
        addressController.setTable();
    }

    public void OrganizationDirectoryAction() {
        organizationController = new OrganizationController(tableView, organisationService);
        organizationController.setTable();
    }

    public void PersonDirectoryAction() {
        personController = new PersonController(tableView, personService);
        personController.setTable();
    }

    public void DistrictDirectoryAction() {
        districtController = new DistrictController(tableView, districtService);
        districtController.setTable();
    }

    public void RateDirectoryAction() {
        rateController = new RateController(tableView, rateService);
        rateController.setTable();
    }

    private void firstView(){

    }
}
