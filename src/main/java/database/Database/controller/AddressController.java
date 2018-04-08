package database.Database.controller;

import database.Database.entity.Address;
import database.Database.entity.District;
import database.Database.navigateElements.AddButton;
import database.Database.navigateElements.CustomComboBox;
import database.Database.navigateElements.DeleteButton;
import database.Database.navigateElements.CustomTextField;
import database.Database.repository.AddressRepository;
import database.Database.repository.DistrictRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class AddressController implements Controllers {

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;


    private AddressRepository addressService;

    private DistrictRepository districtService;


    private CustomTextField nameTextField = new CustomTextField(70, 150);
    private CustomTextField indexTextField = new CustomTextField(220, 100);
    private CustomTextField streetTextField = new CustomTextField(320, 150);
    private CustomTextField buildingTextField = new CustomTextField(470, 50);
    private CustomComboBox districtComboBox;

    public AddressController(TableView directoryTableView, Pane additionPane, AddressRepository addressService, DistrictRepository districtService) {
        this.directoryTableView = directoryTableView;
        this.additionPane = additionPane;
        this.addressService = addressService;
        this.districtService = districtService;

        ObservableList<District> districtObservableList = FXCollections.observableArrayList(districtService.findAll());
        districtComboBox = new CustomComboBox(districtObservableList, 520, 150);
    }

    @Override
    public void setTable() {
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


        AddButton addButton = new AddButton(this, 670, 75);

        DeleteButton deleteButton = new DeleteButton(this, 750, 75);

        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(nameTextField, indexTextField, streetTextField, buildingTextField, districtComboBox, addButton, deleteButton);

    }

    @Override
    public void addRecord() {
        addressService.save(new Address(nameTextField.getText(),
                Integer.valueOf(indexTextField.getText()), streetTextField.getText(),
                Integer.valueOf(buildingTextField.getText()), (District) districtComboBox.getValue()));
        setTable();
    }

    @Override
    public void deleteRecord() {

    }

    @Override
    public void updateRecord() {

    }
}
