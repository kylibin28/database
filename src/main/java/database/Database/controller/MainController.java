package database.Database.controller;

import database.Database.controller.modalWindows.PhoneController;
import database.Database.entity.Address;
import database.Database.entity.Phone;
import database.Database.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController {


    @FXML
    private TableView tableView;
    @FXML
    private Label currentTableLabel;

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
    @Autowired
    private PhoneService phoneService;


    private AddressController addressController;
    private PersonController personController;
    private ATSController atsController;
    private OrganizationController organizationController;
    private DistrictController districtController;
    private RateController rateController;
    private PhoneController phoneController;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        firstView();
    }


    public void ATSDirectoryAction() {
        currentTableLabel.setText("Справочник АТС");
        atsController = new ATSController(tableView, atsService);
        atsController.setTable();
    }

    public void AddressDirectoryAction() {
        currentTableLabel.setText("Справочник адресов");
        addressController = new AddressController(tableView, addressService, districtService);
        addressController.setTable();
    }

    public void OrganizationDirectoryAction() {
        currentTableLabel.setText("Справочник организаций");
        organizationController = new OrganizationController(tableView, organisationService);
        organizationController.setTable();
    }

    public void PersonDirectoryAction() {
        currentTableLabel.setText("Справочник частных лиц");
        personController = new PersonController(tableView, personService);
        personController.setTable();
    }

    public void DistrictDirectoryAction() {
        currentTableLabel.setText("Справочник районов");
        districtController = new DistrictController(tableView, districtService);
        districtController.setTable();
    }

    public void RateDirectoryAction() {
        currentTableLabel.setText("Справочник тарифов");
        rateController = new RateController(tableView, rateService);
        rateController.setTable();
    }

    public void paymentInformationAction(){

    }

    public void phoneInformationAction(){
        currentTableLabel.setText("Сведения о телефонах");
        phoneController = new PhoneController(tableView, phoneService, atsService);
        phoneController.setTable();
    }

    private void firstView(){
        currentTableLabel.setText("Основная информация");
        ObservableList<Phone> list = FXCollections.observableArrayList(phoneService.findAll());

        TableColumn<Address, String> phoneNumber = new TableColumn<>("Номер телефона");
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber.setPrefWidth(100);

        TableColumn<Address, String> isConnect = new TableColumn<>("Признак включения");
        isConnect.setCellValueFactory(new PropertyValueFactory<>("isConnect"));
        isConnect.setPrefWidth(750);


        tableView.getColumns().setAll(phoneNumber, isConnect);
        tableView.setItems(list);
        tableView.getSortOrder().add(phoneNumber);

    }
}
