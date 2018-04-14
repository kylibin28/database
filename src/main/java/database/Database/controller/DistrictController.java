package database.Database.controller;

import database.Database.controller.modalWindows.DistrictModalController;
import database.Database.controller.modalWindows.PersonModalController;
import database.Database.entity.District;
import database.Database.entity.Person;
import database.Database.service.DistrictService;
import database.Database.service.PersonService;
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

public class DistrictController {
    @FXML
    private TableView directoryTableView;

    private DistrictService districtService;

    public DistrictController(TableView directoryTableView, DistrictService districtService) {
        this.directoryTableView = directoryTableView;
        this.districtService = districtService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((District) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable(){

        ObservableList<District> list = FXCollections.observableArrayList(districtService.findAll());

        TableColumn<Person, String> districtName = new TableColumn<>("Название района");
        districtName.setCellValueFactory(new PropertyValueFactory<>("districtName"));
        districtName.setPrefWidth(200);


        directoryTableView.getColumns().setAll(districtName);
        directoryTableView.setItems(list);
        directoryTableView.getSortOrder().add(districtName);
    }

    public void deleteRecord() {
        districtService.delete((District) directoryTableView.getSelectionModel().getSelectedItem());
    }

    private void openModal(District district) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/DistrictModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            DistrictModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, district, districtService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
