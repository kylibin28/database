package database.Database.controller;

import database.Database.controller.modalWindows.PersonModalController;
import database.Database.entity.Person;
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

public class PersonController {

    @FXML
    private TableView directoryTableView;

    private PersonService personService;


    public PersonController (TableView directoryTableView, PersonService personService) {
        this.directoryTableView = directoryTableView;
        this.personService = personService;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemCreate = new MenuItem("Создать");
        MenuItem itemEdit = new MenuItem("Редактировать");
        MenuItem itemDelete = new MenuItem("Удалить");
        itemCreate.setOnAction(event -> openModal(null));
        itemEdit.setOnAction(event -> openModal((Person) directoryTableView.getSelectionModel().getSelectedItem()));
        itemDelete.setOnAction(event -> deleteRecord());

        contextMenu.getItems().addAll(itemCreate, itemEdit, itemDelete);
        directoryTableView.setOnContextMenuRequested(event ->
                contextMenu.show(directoryTableView, event.getScreenX(), event.getScreenY()));
        directoryTableView.setOnMouseClicked(event -> contextMenu.hide());
    }

    public void setTable(){

        ObservableList<Person> list = FXCollections.observableArrayList(personService.findAll());

        TableColumn<Person, String> pasportNumber = new TableColumn<>("Номер паспорта");
        pasportNumber.setCellValueFactory(new PropertyValueFactory<>("pasportNumber"));
        pasportNumber.setPrefWidth(100);

        TableColumn<Person, String> FIO = new TableColumn<>("ФИО");
        FIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        FIO.setPrefWidth(250);

        TableColumn<Person, String> privilege = new TableColumn<>("Наличие льгот");
        privilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        privilege.setPrefWidth(100);

        directoryTableView.getColumns().setAll(pasportNumber, FIO, privilege);
        directoryTableView.setItems(list);
    }

    public void deleteRecord() {
        personService.delete((Person) directoryTableView.getSelectionModel().getSelectedItem());
    }

    private void openModal(Person person) {
        try {
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Object.class.getResource("/fxml/PersonModal.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initModality(Modality.APPLICATION_MODAL);

            PersonModalController controller = loader.getController();
            controller.additionalInit(this, primaryStage, person, personService);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
