package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.repository.*;
import database.Database.service.ATSService;
import database.Database.service.DistrictService;
import database.Database.service.PersonService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Function;

public class MainController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @FXML
    private TableView atsTableView;
    @FXML
    private Pane additionPane;
    @FXML
    private TableColumn<ATSController, ATSController> atsIdTableColumn;
    @FXML
    private TableColumn<ATSController, ATSController> atsApplyTableColumn;
    @FXML
    private TableColumn<ATSController, ATSController> atsUndoTableColumn;
    @FXML
    private TableColumn<ATSController, ATSController> atsDeleteTableColumn;


    @Autowired
    private ATSService atsService;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DistrictService districtRepository;


    private AddressController addressController;
    private PersonController personController;
    private ATSController atsController;
    private OrganizationController organizationController;


    /**
     * Контрольные точки
     */
    private ObservableList<ATSController> atsControllerList;


    @FXML
    private void initialize() {

    }

    @PostConstruct
    public void init() {
        addressController = new AddressController(atsTableView, additionPane, addressRepository, districtRepository);
        personController = new PersonController(atsTableView, additionPane, personService);
        organizationController = new OrganizationController(atsTableView, additionPane, organisationRepository);
//        atsController = new ATSController(additionPane, atsService);
        AddressDirectoryAction();
//        initATS();
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
