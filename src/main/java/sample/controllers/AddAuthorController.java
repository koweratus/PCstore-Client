package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.daos.AuthorDao;
import sample.models.Author;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddAuthorController implements Initializable {

    AuthorDao authorDao = new AuthorDao();

    @FXML
    private TextField firstnameAuthor;

    @FXML
    private TextField lastnameAuthor;

    @FXML
    private Button saveAuthor;

    private Author author;

    List<Author> authorsDao;


    public void init() {
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    @FXML
    public void onClickSaveAuthor() {
        this.author.setFirstname(firstnameAuthor.getText());
        this.author.setLastname(lastnameAuthor.getText());

        authorDao.addItem(this.author);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
