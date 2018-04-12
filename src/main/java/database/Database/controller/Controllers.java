package database.Database.controller;

public interface Controllers {

    public void setTable();

    public void addRecord();

    public void deleteRecord();

    public void updateRecord();

    public void undo();

    public void apply();

    public void setApplyButtonDisable(Boolean isDisable);



}
