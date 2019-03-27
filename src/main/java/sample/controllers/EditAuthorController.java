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
import java.util.List;
import java.util.ResourceBundle;

public class EditAuthorController implements Initializable {

    AuthorDao authorDao = new AuthorDao();

    @FXML
    private TextField firstnameAuthor;

    @FXML
    private TextField lastnameAuthor;

    @FXML
    private Button saveAuthor;

    private Author author;

    List<Author> authorsDao;

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void init() {
        setInitAuthor();
    }

    private void setInitAuthor() {
        firstnameAuthor.setText(author.getFirstname());
        lastnameAuthor.setText(author.getLastname());
    }

    @FXML
    public void onClickSaveAuthor() {
        this.author.setFirstname(firstnameAuthor.getText());
        this.author.setLastname(lastnameAuthor.getText());

        authorDao.updateItem(this.author);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
