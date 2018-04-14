package database.Database.controller;

import database.Database.controller.modalWindows.ATSModalController;
import database.Database.entity.ATS;
import database.Database.service.ATSService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

public class ATSController {

    @FXML
    private TableView directoryTableView;


    private ATSService atsService;


    public ATSController(TableView directoryTableView, ATSService atsService) {
        this.directoryTableView = directoryTableView;
        this.atsService = atsService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((ATS) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable() {
        ObservableList<ATS> list = FXCollections.observableArrayList(atsService.findAll());

        TableColumn<ATS, String> atsId = new TableColumn<>("Номер АТС");
        atsId.setPrefWidth(100);
        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));

        directoryTableView.getColumns().setAll(atsId);
        directoryTableView.setItems(list);
        directoryTableView.getSortOrder().add(atsId);

    }

    public void deleteRecord() {
        atsService.delete((ATS) directoryTableView.getSelectionModel().getSelectedItem());
    }


    private void openModal(ATS ats) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/ATSModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            ATSModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, ats, atsService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
