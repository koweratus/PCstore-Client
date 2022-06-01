package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.daos.ManafacturerDao;
import sample.daos.ComponentDao;
import sample.models.Manafacturer;
import sample.models.Component;

import java.net.URL;
import java.util.*;

public class AddComponentController implements Initializable {

    ManafacturerDao manafacturerDao = new ManafacturerDao();
    ComponentDao componentDao = new ComponentDao();

    @FXML
    private TextField titleComponent;

    @FXML
    private TextField descriptionComponent;

    @FXML
    private ComboBox type;

    @FXML
    private ComboBox manafacturers;

    @FXML
    private Button saveComponent;

    private Component component;

    List<Manafacturer> manafactuerDao;

    public void setComponent(Component component) {
        this.component = component;
    }

    public void init() {
        setComponentTypes();
        setManafacturers();
    }

    private void setManafacturers() {
        manafactuerDao = manafacturerDao.getAllItems();
        this.manafacturers.getItems().addAll(manafactuerDao);
    }

    private void setComponentTypes() {
        type.getItems().addAll(Arrays.asList(Component.Type.values()));
    }


    @FXML
    public void onClickSaveComponent() {
        this.component.setTitle(titleComponent.getText());
        this.component.setDescription(descriptionComponent.getText());
        this.component.setType(Component.Type.valueOf(type.getValue().toString()));

        if(manafacturers.getSelectionModel().getSelectedIndex() != -1) {
            Manafacturer manafacturer = manafactuerDao.get(manafacturers.getSelectionModel().getSelectedIndex());
            this.component.setManafacturer(manafacturer);
        }

        componentDao.addItem(this.component);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
