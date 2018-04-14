package database.Database.controller;

import database.Database.controller.modalWindows.DistrictModalController;
import database.Database.controller.modalWindows.RateModalController;
import database.Database.entity.District;
import database.Database.entity.Person;
import database.Database.entity.Rate;
import database.Database.service.RateService;
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

public class RateController {
    @FXML
    private TableView directoryTableView;

    private RateService rateService;

    public RateController(TableView directoryTableView, RateService rateService) {
        this.directoryTableView = directoryTableView;
        this.rateService = rateService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((Rate) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable() {
        ObservableList<Rate> list = FXCollections.observableArrayList(rateService.findAll());

        TableColumn<Person, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("rateId"));
        id.setPrefWidth(100);

        TableColumn<Person, String> year = new TableColumn<>("Год");
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        year.setPrefWidth(100);

        TableColumn<Person, String> month = new TableColumn<>("Месяц");
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        month.setPrefWidth(100);

        TableColumn<Person, String> value = new TableColumn<>("Сумма");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        value.setPrefWidth(100);


        directoryTableView.getColumns().setAll(id, year, month, value);
        directoryTableView.setItems(list);
        directoryTableView.getSortOrder().add(id);
    }

    public void deleteRecord() {
        rateService.delete((Rate) directoryTableView.getSelectionModel().getSelectedItem());
    }

    private void openModal(Rate rate) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/RateModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            RateModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, rate, rateService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
