package sample.util;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Stager {
    private Stage stage;
    private FXMLLoader loader;

    public Stager(Stage stage, FXMLLoader loader) {
        this.stage = stage;
        this.loader = loader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }
}
