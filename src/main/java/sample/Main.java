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
    }

    @FXML
    private void onAllComponentsBtnClick() {
        StageUtil.showAllComponentsStage();
    }

    @FXML
    private void onAllManafacturerBtnClick() {
        StageUtil.showAllManafacturerStage();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
