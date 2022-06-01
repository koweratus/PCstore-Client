package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.daos.ComponentDao;
import sample.daos.CrudDao;
import sample.models.Component;
import sample.util.ButtonUtil;
import sample.util.CrudActions;
import sample.util.DialogUtil;
import sample.util.StageUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ComponentController implements Initializable, CrudActions {

    CrudDao<Component> componentDao = new ComponentDao();

    @FXML
    TableView<Component> componentTable;

    @FXML
    TableColumn<Component, String> company;

    @FXML
    TableColumn<Component, String> title;

    @FXML
    TableColumn<Component, String> description;

    @FXML
    TableColumn<Component, String> type;


    public void init() {
        initilizeColumnName();
        showComponents();
        addDeleteButton();
        addEditButton();
    }


    private void showComponents() {
        List<Component> components = componentDao.getAllItems();

        ObservableList<Component> observableList = FXCollections.observableArrayList(components);

        componentTable.getItems().setAll(observableList);
    }

    private void initilizeColumnName() {
        company.setCellValueFactory(new PropertyValueFactory<Component, String>("company"));
        title.setCellValueFactory(new PropertyValueFactory<Component, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<Component, String>("description"));
        type.setCellValueFactory(new PropertyValueFactory<Component, String>("type"));
    }

    private void addDeleteButton() {
        TableColumn<Component, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(new Callback<TableColumn<Component, Void>, TableCell<Component, Void>>() {
            @Override
            public TableCell<Component, Void> call(TableColumn<Component, Void> param) {
                return new ButtonUtil<>(ComponentController.this, "Delete", ButtonUtil.ActionType.DELETE);
            }
        });

        componentTable.getColumns().add(deleteColumn);
    }

    private void addEditButton() {
        TableColumn<Component, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Component, Void>, TableCell<Component, Void>>() {
            @Override
            public TableCell<Component, Void> call(TableColumn<Component, Void> param) {
                return new ButtonUtil<Component>(ComponentController.this, "Edit", ButtonUtil.ActionType.EDIT);
            }
        });

        componentTable.getColumns().add(editColumn);
    }

    @FXML
    public void onClickRefreshComponent() {
        this.showComponents();
    }

    @FXML
    public void onClickAddComponent() {
        Component component = new Component();
        StageUtil.showAddComponentStage(component);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void onDeleteClicked(int index) {
        Component component = componentTable.getItems().get(index);
        if(!componentDao.deleteItem(component.getId())) {
            DialogUtil.buildSimpleDialog("Api not work", null, null, Alert.AlertType.ERROR).showAndWait();
            return;
        }

        componentTable.getItems().remove(component);
    }

    @Override
    public void onEditClicked(int index) {
        Component component = componentTable.getItems().get(index);
        StageUtil.showEditComponentStage(component);
    }


    @Override
    public void onSelectClicked(int index) {

    }
}
