package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.daos.ManafacturerDao;
import sample.daos.CrudDao;
import sample.models.Manafacturer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import sample.util.ButtonUtil;
import sample.util.CrudActions;
import sample.util.DialogUtil;
import sample.util.StageUtil;


public class ManafacturerController implements Initializable, CrudActions {

    CrudDao<Manafacturer> manafacturerDao = new ManafacturerDao();

    @FXML
    TableView<Manafacturer> manafacturerTable;

    @FXML
    TableColumn<Manafacturer, String> company;

    @FXML
    TableColumn<Manafacturer, String> lastname;


    public void init() {
        initilizeColumnName();
        showManafacturer();
        addDeleteButton();
        addEditButton();
    }

    private void showManafacturer() {
        List<Manafacturer> manafacturers = manafacturerDao.getAllItems();

        ObservableList<Manafacturer> observableList = FXCollections.observableArrayList(manafacturers);

        manafacturerTable.getItems().setAll(observableList);
    }

    private void initilizeColumnName() {
        company.setCellValueFactory(new PropertyValueFactory<Manafacturer, String>("company"));
    }

    private void addDeleteButton() {
        TableColumn<Manafacturer, Void> deleteColumn = new TableColumn<>("Delete");

        deleteColumn.setCellFactory(new Callback<TableColumn<Manafacturer, Void>, TableCell<Manafacturer, Void>>() {
            @Override
            public TableCell<Manafacturer, Void> call(TableColumn<Manafacturer, Void> param) {
                return new ButtonUtil<Manafacturer>(ManafacturerController.this, "Delete", ButtonUtil.ActionType.DELETE);
            }
        });


        manafacturerTable.getColumns().add(deleteColumn);
    }

    private void addEditButton() {
        TableColumn<Manafacturer, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Manafacturer, Void>, TableCell<Manafacturer, Void>>() {
            @Override
            public TableCell<Manafacturer, Void> call(TableColumn<Manafacturer, Void> param) {
                return new ButtonUtil<Manafacturer>(ManafacturerController.this, "Edit", ButtonUtil.ActionType.EDIT);
            }
        });

        manafacturerTable.getColumns().add(editColumn);
    }


    @Override
    public void onDeleteClicked(int index) {
        Manafacturer manafacturer = manafacturerTable.getItems().get(index);
        if(!manafacturerDao.deleteItem(manafacturer.getId())) {
            DialogUtil.buildSimpleDialog("Api not work", null, null, Alert.AlertType.ERROR).showAndWait();
            return;
        }

        manafacturerTable.getItems().remove(manafacturer);
    }

    @Override
    public void onEditClicked(int index) {
        Manafacturer manafacturer = manafacturerTable.getItems().get(index);
        StageUtil.showEditManafacturerStage(manafacturer);
    }

    @FXML
    public void onClickRefreshManafacturer() {
        this.showManafacturer();
    }

    @FXML
    public void onClickAddManafacturer() {
        Manafacturer manafacturer = new Manafacturer();
        StageUtil.showAddManafacturerStage(manafacturer);
    }

    @Override
    public void onSelectClicked(int index) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
