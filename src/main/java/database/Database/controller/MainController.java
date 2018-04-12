package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.repository.*;
import database.Database.service.ATSService;
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
    private TableView directoryTableView;
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
    private DistrictRepository districtRepository;


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
        addressController = new AddressController(directoryTableView, additionPane, addressRepository, districtRepository);
        personController = new PersonController(directoryTableView, additionPane, personService);
        organizationController = new OrganizationController(directoryTableView, additionPane, organisationRepository);
//        atsController = new ATSController(additionPane, atsService);
//        AddressDirectoryAction();
        initATS();
    }


    private void initATS() {
        try {
            Function<ATSController, ObservableValue<ATSController>> property = ReadOnlyObjectWrapper<ATSController>::new;

            atsIdTableColumn.setCellValueFactory(
                    cellData -> property.apply(cellData.getValue()));
            atsApplyTableColumn.setCellValueFactory(
                    cellData -> property.apply(cellData.getValue()));
            atsUndoTableColumn.setCellValueFactory(
                    cellData -> property.apply(cellData.getValue()));
            atsDeleteTableColumn.setCellValueFactory(
                    cellData -> property.apply(cellData.getValue()));


            atsIdTableColumn.setCellFactory(
                    p -> {
                        TableCell item = new TableCell<ATSController, ATSController>() {
                            @Override
                            public void updateItem(ATSController atsController, boolean empty) {
                                if (atsController == null) {
                                    setGraphic(null);
                                    return;
                                }
                                super.updateItem(atsController, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(atsController.getATSNumberTextField());
                                }
                            }
                        };
                        item.setAlignment(Pos.CENTER);
                        return item;
                    });


            atsApplyTableColumn.setCellFactory(
                    p -> {
                        TableCell item = new TableCell<ATSController, ATSController>() {
                            @Override
                            public void updateItem(ATSController atsController, boolean empty) {
                                if (atsController == null) {
                                    setGraphic(null);
                                    return;
                                }
                                super.updateItem(atsController, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(atsController.getApplyButton());
                                }
                            }
                        };
                        item.setAlignment(Pos.CENTER);
                        return item;
                    });


            atsUndoTableColumn.setCellFactory(
                    p -> {
                        TableCell item = new TableCell<ATSController, ATSController>() {
                            @Override
                            public void updateItem(ATSController atsController, boolean empty) {
                                if (atsController == null) {
                                    setGraphic(null);
                                    return;
                                }
                                super.updateItem(atsController, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(atsController.getUndoButton());
                                }
                            }
                        };
                        item.setAlignment(Pos.CENTER);
                        return item;
                    });


            atsDeleteTableColumn.setCellFactory(
                    p -> {
                        TableCell item = new TableCell<ATSController, ATSController>() {
                            @Override
                            public void updateItem(ATSController atsController, boolean empty) {
                                if (atsController == null) {
                                    setGraphic(null);
                                    return;
                                }
                                super.updateItem(atsController, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(atsController.getDeleteButton());
                                }
                            }
                        };
                        item.setAlignment(Pos.CENTER);
                        return item;
                    });

            directoryTableView.getItems().clear();

            List<ATS> list = atsService.findAll();
            atsControllerList = FXCollections.observableArrayList();

            for (ATS ats : list) {
                atsControllerList.add(new ATSController(additionPane, ats, atsService));
            }

            directoryTableView.setItems(atsControllerList);
        } catch (Exception e) {
            logger.error("Error for collect ControlPoint");
            logger.error(e);
        }
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
