package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.daos.ManafacturerDao;
import sample.models.Manafacturer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddManafacturerController implements Initializable {

    ManafacturerDao manafacturerDao = new ManafacturerDao();

    @FXML
    private TextField company;

    @FXML
    private Button saveManafacturer;

    private Manafacturer manafacturer;


    public void init() {
    }


    public void setManafacturer(Manafacturer manafacturer) {
        this.manafacturer = manafacturer;
    }

    @FXML
    public void onClickSaveManafacturer() {
        this.manafacturer.setCompany(company.getText());

        manafacturerDao.addItem(this.manafacturer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
