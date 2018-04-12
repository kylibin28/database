package database.Database.controller;

import database.Database.entity.ATS;
import database.Database.navigateElements.*;
import database.Database.service.ATSService;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ATSController implements Controllers {

    @FXML
    private Pane additionPane;


    private ATS ats;

    private ATSService atsService;



    private ApplyButton applyButton;
    private UndoButton undoButton;
    private DeleteButton deleteButton;
    private ATSNumberTextField ATSNumberTextField;


    public ATSController(Pane additionPane, ATS ats, ATSService atsService) {
        this.additionPane = additionPane;
        this.ats = ats;
        this.atsService = atsService;

        generateElements();
    }

    private void generateElements(){
        applyButton = new ApplyButton(this);
        undoButton = new UndoButton(this);
        deleteButton = new DeleteButton(this);
        ATSNumberTextField = new ATSNumberTextField(this);
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
    public void apply(){

    }

    @Override
    public void undo(){

    }

    public void setApplyButtonDisable(Boolean isDisable){

    }

    public ApplyButton getApplyButton() {
        return applyButton;
    }

    public UndoButton getUndoButton() {
        return undoButton;
    }

    public DeleteButton getDeleteButton() {
        return deleteButton;
    }

    public database.Database.navigateElements.ATSNumberTextField getATSNumberTextField() {
        return ATSNumberTextField;
    }
}
