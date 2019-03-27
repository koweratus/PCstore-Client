package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.daos.AuthorDao;
import sample.daos.BookDao;
import sample.models.Author;
import sample.models.Book;


import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.List;

public class EditBookController implements Initializable {

    AuthorDao authorDao = new AuthorDao();
    BookDao bookDao = new BookDao();

    @FXML
    private TextField titleBook;

    @FXML
    private TextField descriptionBook;

    @FXML
    private ComboBox type;

    @FXML
    private ComboBox authors;

    @FXML
    private Button saveBook;

    private Book book;

    List<Author> authorsDao;

    public void setBook(Book book) {
        this.book = book;
    }

    public void init() {
        setBookTypes();
        setAuthors();
        setInitBook();
    }

    private void setAuthors() {
        authorsDao = authorDao.getAllItems();
        this.authors.getItems().addAll(authorsDao);
    }

    private void setBookTypes() {
        type.getItems().addAll(Arrays.asList(Book.Type.values()));
    }

    private void setInitBook() {
        titleBook.setText(book.getTitle());
        descriptionBook.setText(book.getDescription());
        type.setValue(book.getType().name());
        authors.setValue(book.getAuthor().toString());
    }

    @FXML
    public void onClickSaveBook() {
        this.book.setTitle(titleBook.getText());
        this.book.setDescription(descriptionBook.getText());
        this.book.setType(Book.Type.valueOf(type.getValue().toString()));

        if(authors.getSelectionModel().getSelectedIndex() != -1) {
            Author myauthor = authorsDao.get(authors.getSelectionModel().getSelectedIndex());
            this.book.setAuthor(myauthor);
        }

        bookDao.updateItem(this.book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
