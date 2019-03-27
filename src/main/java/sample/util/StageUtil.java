package sample.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import route.Route;
import sample.controllers.AuthorController;
import sample.controllers.BookController;
import sample.controllers.EditAuthorController;
import sample.controllers.EditBookController;
import sample.models.Author;
import sample.models.Book;

public class StageUtil {


    public static void showAllBooksStage() {
        Stager stager = getStager("books_view.fxml", "Show all books");

        BookController bookController = stager.getLoader().getController();
        bookController.init();

        stager.getStage().show();

    }

    public static void showAllAuthorsStage() {
        Stager stager = getStager("authors_view.fxml", "Show all authors");

        AuthorController authorController = stager.getLoader().getController();
        authorController.init();

        stager.getStage().show();

    }

    public static void showEditAuthorStage(Author author){
        Stager stager = getStager("author_edit.fxml", "Edit book");

        EditAuthorController editAuthorController = stager.getLoader().getController();
        editAuthorController.setAuthor(author);
        editAuthorController.init();

        stager.getStage().show();
    }

    public static void showEditBookStage(Book book){
        Stager stager = getStager("book_edit.fxml", "Edit book");

        EditBookController editBookController = stager.getLoader().getController();
        editBookController.setBook(book);
        editBookController.init();

        stager.getStage().show();
    }


    private static Stager getStager(String view, String title){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Route.path(view));
        try {
            loader.load();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setResizable(false);
        stage.setTitle(title);
        stage.sizeToScene();

        return new Stager(stage, loader);
    }
}
