package database.Database.controller;

import database.Database.entity.Organisation;
import database.Database.repository.OrganisationRepository;
import database.Database.service.OrganisationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class OrganizationController {

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;


    private OrganisationRepository organisationService;

    public OrganizationController(TableView directoryTableView, Pane additionPane, OrganisationRepository organisationService) {
        this.directoryTableView = directoryTableView;
        this.additionPane = additionPane;
        this.organisationService = organisationService;
    }

    public void setOrganizationTable() {

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
            setOrganizationTable();
        });
        addButton.setLayoutX(260);
        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(registrationNumberField, nameTextField, addButton);
    }

}
