package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.navigateElements.AddButton;
import database.Database.navigateElements.CustomTextField;
import database.Database.navigateElements.DeleteButton;
import database.Database.repository.ATSRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ATSController implements Controllers {

    @FXML
    private TableView directoryTableView;

    @FXML
    private Pane additionPane;


    private ATSRepository atsService;

    public ATSController(TableView directoryTableView, Pane additionPane, ATSRepository atsService) {
        this.directoryTableView = directoryTableView;
        this.additionPane = additionPane;
        this.atsService = atsService;
    }

    CustomTextField additionTextField = new CustomTextField(0, 100);

    @Override
    public void setTable() {
        ObservableList<ATS> list = FXCollections.observableArrayList(atsService.findAll());

        TableColumn<ATS, String> atsId = new TableColumn<>("Номер АТС");
        atsId.setPrefWidth(100);
        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));

        directoryTableView.getColumns().setAll(atsId);
        directoryTableView.setItems(list);


        AddButton addButton = new AddButton(this, 110, 75);

        DeleteButton deleteButton = new DeleteButton(this, 170, 75);


        additionPane.getChildren().clear();
        additionPane.getChildren().addAll(additionTextField, addButton, deleteButton);
    }

    @Override
    public void addRecord() {
        atsService.save(new ATS(Integer.valueOf(additionTextField.getText())));
        setTable();
    }

    @Override
    public void deleteRecord() {
        atsService.delete((ATS) directoryTableView.getSelectionModel().getSelectedItem());
        setTable();
    }

    @Override
    public void updateRecord() {
        additionTextField.setText(String.valueOf(((ATS) directoryTableView.getSelectionModel().getSelectedItem()).getAtsId()));
    }
}
