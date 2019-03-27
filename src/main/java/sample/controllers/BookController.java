package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.daos.BookDao;
import sample.daos.CrudDao;
import sample.models.Book;
import sample.util.ButtonUtil;
import sample.util.CrudActions;
import sample.util.DialogUtil;
import sample.util.StageUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable, CrudActions {

    CrudDao<Book> booksDao = new BookDao();

    @FXML
    TableView<Book> bookTable;

    @FXML
    TableColumn<Book, String> fullName;

    @FXML
    TableColumn<Book, String> title;

    @FXML
    TableColumn<Book, String> description;

    @FXML
    TableColumn<Book, String> type;


    public void init() {
        initilizeColumnName();
        showBooks();
        addDeleteButton();
        addEditButton();
    }


    private void showBooks() {
        List<Book> books = booksDao.getAllItems();

        ObservableList<Book> observableList = FXCollections.observableArrayList(books);

        bookTable.getItems().setAll(observableList);
    }

    private void initilizeColumnName() {
        fullName.setCellValueFactory(new PropertyValueFactory<Book, String>("fullName"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
        type.setCellValueFactory(new PropertyValueFactory<Book, String>("type"));
    }

    private void addDeleteButton() {
        TableColumn<Book, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(TableColumn<Book, Void> param) {
                return new ButtonUtil<>(BookController.this, "Delete", ButtonUtil.ActionType.DELETE);
            }
        });

        bookTable.getColumns().add(deleteColumn);
    }

    private void addEditButton() {
        TableColumn<Book, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(TableColumn<Book, Void> param) {
                return new ButtonUtil<Book>(BookController.this, "Edit", ButtonUtil.ActionType.EDIT);
            }
        });

        bookTable.getColumns().add(editColumn);
    }

    @FXML
    public void onClickRefreshBooks() {
        this.showBooks();
    }

    @FXML
    public void onClickAddBooks() {
        Book book = new Book();
        StageUtil.showAddBookStage(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void onDeleteClicked(int index) {
        Book book = bookTable.getItems().get(index);
        if(!booksDao.deleteItem(book.getId())) {
            DialogUtil.buildSimpleDialog("Api not work", null, null, Alert.AlertType.ERROR).showAndWait();
            return;
        }

        bookTable.getItems().remove(book);
    }

    @Override
    public void onEditClicked(int index) {
        Book book = bookTable.getItems().get(index);
        StageUtil.showEditBookStage(book);
    }


    @Override
    public void onSelectClicked(int index) {

    }
}
