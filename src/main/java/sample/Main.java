package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import route.Route;
import sample.util.StageUtil;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Route.path("main.fxml"));
        primaryStage.setTitle("Home page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();



//        StageUtil.showAllBooksStage();
    }

    @FXML
    private void onAllBooksBtnClick() {
        StageUtil.showAllBooksStage();
    }

    @FXML
    private void onAllAuthorsBtnClick() {
        StageUtil.showAllAuthorsStage();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
