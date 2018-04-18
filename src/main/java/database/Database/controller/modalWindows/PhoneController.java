package database.Database.controller.modalWindows;

import database.Database.entity.ATS;
import database.Database.entity.Organisation;
import database.Database.entity.Phone;
import database.Database.service.ATSService;
import database.Database.service.OrganisationService;
import database.Database.service.PhoneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PhoneController {
    @FXML
    private TableView tableView;

    private PhoneService phoneService;

    private ATSService atsService;

    public PhoneController(TableView tableView, PhoneService phoneService, ATSService atsService) {
        this.tableView = tableView;
        this.phoneService = phoneService;
        this.atsService = atsService;
        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((Phone) tableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        tableView.setOnContextMenuRequested(event ->
                contextMenu.show(tableView, event.getScreenX(), event.getScreenY()));
        tableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable() {

        ObservableList<Phone> list = FXCollections.observableArrayList(phoneService.findAll());

        TableColumn<ATS, String> atsId = new TableColumn<>("Номер АТС");
        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));
        atsId.setPrefWidth(100);

        TableColumn<Phone, String> phoneNumber = new TableColumn<>("Номер телефона");
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber.setPrefWidth(150);

        TableColumn<Phone, String> isConnect = new TableColumn<>("Признак отключения");
        isConnect.setCellValueFactory(new PropertyValueFactory<>("isConnect"));
        isConnect.setPrefWidth(150);


        tableView.getColumns().setAll(atsId, phoneNumber, isConnect);
        tableView.setItems(list);
        tableView.getSortOrder().add(phoneNumber);
    }

    public void deleteRecord() {
        phoneService.delete((Phone) tableView.getSelectionModel().getSelectedItem());
        setTable() ;
    }


    private void openModal(Phone phone) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/PhoneModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            PhoneModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, phone, phoneService, atsService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
