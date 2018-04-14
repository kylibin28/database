package database.Database.controller;

import database.Database.controller.modalWindows.AddressModalController;
import database.Database.entity.Address;
import database.Database.entity.District;
import database.Database.repository.AddressRepository;
import database.Database.service.AddressService;
import database.Database.service.DistrictService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AddressController {

    @FXML
    private TableView directoryTableView;


    private AddressService addressService;

    private DistrictService districtService;


    public AddressController(TableView directoryTableView, AddressService addressService, DistrictService districtService) {
        this.directoryTableView = directoryTableView;
        this.addressService = addressService;
        this.districtService = districtService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((Address) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

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
        directoryTableView.getSortOrder().add(idColumn);

    }


    public void deleteRecord() {
        addressService.delete((Address) directoryTableView.getSelectionModel().getSelectedItem());
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
            controller.additionalInit(this, primaryStage, address, addressService, districtService);

            primaryStage.setOnCloseRequest((WindowEvent event) -> setTable());

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
