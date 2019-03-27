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
import sample.daos.AuthorDao;
import sample.daos.CrudDao;
import sample.models.Author;
import sample.models.Book;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import sample.util.ButtonUtil;
import sample.util.CrudActions;
import sample.util.DialogUtil;
import sample.util.StageUtil;


public class AuthorController implements Initializable, CrudActions {

    CrudDao<Author> authorDao = new AuthorDao();

    @FXML
    TableView<Author> authorTable;

    @FXML
    TableColumn<Author, String> firstname;

    @FXML
    TableColumn<Author, String> lastname;


    public void init() {
        initilizeColumnName();
        showAuthors();
        addDeleteButton();
        addEditButton();
    }

    private void showAuthors() {
        List<Author> authors = authorDao.getAllItems();

        ObservableList<Author> observableList = FXCollections.observableArrayList(authors);

        authorTable.getItems().setAll(observableList);
    }

    private void initilizeColumnName() {
        firstname.setCellValueFactory(new PropertyValueFactory<Author, String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<Author, String>("lastname"));
    }

    private void addDeleteButton() {
        TableColumn<Author, Void> deleteColumn = new TableColumn<>("Delete");

        deleteColumn.setCellFactory(new Callback<TableColumn<Author, Void>, TableCell<Author, Void>>() {
            @Override
            public TableCell<Author, Void> call(TableColumn<Author, Void> param) {
                return new ButtonUtil<Author>(AuthorController.this, "Delete", ButtonUtil.ActionType.DELETE);
            }
        });


        authorTable.getColumns().add(deleteColumn);
    }

    private void addEditButton() {
        TableColumn<Author, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Author, Void>, TableCell<Author, Void>>() {
            @Override
            public TableCell<Author, Void> call(TableColumn<Author, Void> param) {
                return new ButtonUtil<Author>(AuthorController.this, "Edit", ButtonUtil.ActionType.EDIT);
            }
        });

        authorTable.getColumns().add(editColumn);
    }


    @Override
    public void onDeleteClicked(int index) {
        Author author = authorTable.getItems().get(index);
        if(!authorDao.deleteItem(author.getId())) {
            DialogUtil.buildSimpleDialog("Api not work", null, null, Alert.AlertType.ERROR).showAndWait();
            return;
        }

        authorTable.getItems().remove(author);
    }

    @Override
    public void onEditClicked(int index) {
        Author author = authorTable.getItems().get(index);
        StageUtil.showEditAuthorStage(author);
    }

    @FXML
    public void onClickRefreshAuthors() {
        this.showAuthors();
    }

    @Override
    public void onSelectClicked(int index) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
