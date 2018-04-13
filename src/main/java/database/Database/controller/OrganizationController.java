package database.Database.controller;

import database.Database.controller.modalWindows.OrganizationModalController;
import database.Database.entity.Organisation;
import database.Database.service.OrganisationService;
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

public class OrganizationController {

    @FXML
    private TableView directoryTableView;

    private OrganisationService organisationService;

    public OrganizationController(TableView directoryTableView, OrganisationService organisationService) {
        this.directoryTableView = directoryTableView;
        this.organisationService = organisationService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((Organisation) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable() {

        ObservableList<Organisation> list = FXCollections.observableArrayList(organisationService.findAll());

        TableColumn<Organisation, String> registrationNumber = new TableColumn<>("Регистрационный номер");
        registrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        registrationNumber.setPrefWidth(100);

        TableColumn<Organisation, String> name = new TableColumn<>("Название");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(150);

        directoryTableView.getColumns().setAll(registrationNumber, name);
        directoryTableView.setItems(list);
    }

    public void deleteRecord() {
        organisationService.delete((Organisation) directoryTableView.getSelectionModel().getSelectedItem());
    }


    private void openModal(Organisation organisation) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/OrganizationModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            OrganizationModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, organisation, organisationService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
