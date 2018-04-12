package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.navigateElements.*;
import database.Database.service.ATSService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ATSController implements Controllers {

    @FXML
    private Pane additionPane;


    private ATS ats;

    private ATSService atsService;


    private Button applyButton;
    private Button undoButton;
    private Button deleteButton;
    private ATSNumberTextField ATSNumberTextField;


    public ATSController(Pane additionPane, ATS ats, ATSService atsService) {
        this.additionPane = additionPane;
        this.ats = ats;
        this.atsService = atsService;

        generateElements();
    }

    private void generateElements() {
        applyButton = new ApplyButton(this);
        undoButton = new UndoButton(this);
        deleteButton = new DeleteButton(this);
        ATSNumberTextField = new ATSNumberTextField(this);
       refresh();
        setApplyButtonDisable(true);
    }

    public void refresh(){
        ATSNumberTextField.setText(String.valueOf(ats.getAtsId()));
    }


    @Override
    public void setTable() {
//        ObservableList<ATS> list = FXCollections.observableArrayList(atsService.findAll());
//
//        TableColumn<ATS, String> atsId = new TableColumn<>("Номер АТС");
//        atsId.setPrefWidth(100);
//        atsId.setCellValueFactory(new PropertyValueFactory<>("atsId"));
//
//        directoryTableView.getColumns().setAll(atsId);
//        directoryTableView.setItems(list);
//
//
//        AddButton addButton = new AddButton(this, 110, 75);
//
//        DeleteButton deleteButton = new DeleteButton(this, 170, 75);
//
//
//        additionPane.getChildren().clear();
//        additionPane.getChildren().addAll(additionTextField, addButton, deleteButton);
    }

    @Override
    public void addRecord() {
    }

    @Override
    public void deleteRecord() {
        atsService.delete(ats);
    }

    @Override
    public void updateRecord() {

    }

    @Override
    public void apply() {
        ats.setAtsId(Integer.valueOf(ATSNumberTextField.getText()));
        atsService.save(ats);
        setApplyButtonDisable(true);
    }

    @Override
    public void undo() {
        refresh();
        setApplyButtonDisable(true);
    }

    public void setApplyButtonDisable(Boolean isDisable) {
        applyButton.setDisable(isDisable);
        undoButton.setDisable(isDisable);
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public database.Database.navigateElements.ATSNumberTextField getATSNumberTextField() {
        return ATSNumberTextField;
    }
}
