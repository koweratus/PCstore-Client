package sample.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import route.Route;
import sample.controllers.*;
import sample.models.Manafacturer;
import sample.models.Component;

public class StageUtil {


    public static void showAllComponentsStage() {
        Stager stager = getStager("component_view.fxml", "Show all components");

        ComponentController componentController = stager.getLoader().getController();
        componentController.init();

        stager.getStage().show();

    }

    public static void showEditComponentStage(Component component){
        Stager stager = getStager("component_edit.fxml", "Edit component");

        EditComponentController editComponentController = stager.getLoader().getController();
        editComponentController.setComponent(component);
        editComponentController.init();

        stager.getStage().show();
    }

    public static void showAddComponentStage(Component component){
        Stager stager = getStager("component_add.fxml", "Add component");

        AddComponentController addComponentController = stager.getLoader().getController();
        addComponentController.setComponent(component);
        addComponentController.init();

        stager.getStage().show();
    }

    public static void showAllManafacturerStage() {
        Stager stager = getStager("manafacturer_view.fxml", "Show all manafacturer");

        ManafacturerController manafacturerController = stager.getLoader().getController();
        manafacturerController.init();

        stager.getStage().show();

    }

    public static void showEditManafacturerStage(Manafacturer manafacturer){
        Stager stager = getStager("manafacturer_edit.fxml", "Edit manafacturer");

        EditManafacturerController editManafacturerController = stager.getLoader().getController();
        editManafacturerController.setManafacturer(manafacturer);
        editManafacturerController.init();

        stager.getStage().show();
    }

    public static void showAddManafacturerStage(Manafacturer manafacturer){
        Stager stager = getStager("manafacturer_add.fxml", "Add manafacturer");

        AddManafacturerController addManafacturerController = stager.getLoader().getController();
        addManafacturerController.setManafacturer(manafacturer);
        addManafacturerController.init();

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
