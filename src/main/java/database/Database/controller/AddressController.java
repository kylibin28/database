package database.Database.controller;

import database.Database.controller.modalWindows.AddressModalController;
import database.Database.entity.Address;
import database.Database.entity.District;
import database.Database.navigateElements.*;
import database.Database.repository.AddressRepository;
import database.Database.repository.DistrictRepository;
import database.Database.service.DistrictService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class AddressController implements Controllers {

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;


    private AddressRepository addressService;

    private DistrictService districtService;


    private CustomTextField nameTextField = new CustomTextField(70, 150);
    private CustomTextField indexTextField = new CustomTextField(220, 100);
    private CustomTextField streetTextField = new CustomTextField(320, 150);
    private CustomTextField buildingTextField = new CustomTextField(470, 50);
    private CustomComboBox districtComboBox;


    private ApplyButton applyButton;
    private UndoButton undoButton;
    private DeleteButton deleteButton;

    public AddressController(TableView directoryTableView, Pane additionPane, AddressRepository addressService, DistrictService districtService) {
        this.directoryTableView = directoryTableView;
        this.additionPane = additionPane;
        this.addressService = addressService;
        this.districtService = districtService;

        ObservableList<District> districtObservableList = FXCollections.observableArrayList(districtService.findAll());
        districtComboBox = new CustomComboBox(districtObservableList, 520, 150);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal((Address) directoryTableView.getSelectionModel().getSelectedItem()));
        itemEdit.setOnAction(event -> openModal(null));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
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
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("districtName"));
        districtColumn.setPrefWidth(150);

        directoryTableView.getColumns().setAll(idColumn, nameColumn, indexColumn, streetColumn, buildingColumn, districtColumn);
        directoryTableView.setItems(list);

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

    @Override
    public void apply() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void setApplyButtonDisable(Boolean isDisable) {
        applyButton.setDisable(isDisable);
        undoButton.setDisable(isDisable);
    }

    private void openModal(Address address) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/AddressModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            AddressModalController controller = loader.getController();
            controller.additionalInit(primaryStage, address, districtService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
