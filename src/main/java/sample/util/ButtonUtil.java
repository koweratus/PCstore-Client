package sample.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class ButtonUtil<T> extends TableCell<T, Void> {
    private final StackPane pane = new StackPane();
    private final Button btn = new Button();

    public enum ActionType {
        EDIT,
        DELETE,
        SELECT
    }


    public ButtonUtil(final CrudActions crudActions, final String btnName, final ActionType type) {
        pane.setPadding(new Insets(3));
        pane.getChildren().add(btn);
        btn.setText(btnName);
        showOptions(crudActions, btn, type);
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(pane);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

    private void showOptions(CrudActions crudActions, Button btn, ActionType type) {
        switch (type) {
            case EDIT:
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        crudActions.onEditClicked(getIndex());
                    }
                });
                break;

            case DELETE:
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        T item = (T) getTableView().getItems().get(getIndex());
                        Alert alert = DialogUtil.buildConfirmationDialog("Do you want delete ?", "Do you want delete ?", item.getClass().toString());
                        Optional<ButtonType> result = alert.showAndWait();
                        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                            crudActions.onDeleteClicked(getIndex());
                        }
                    }
                });
                break;
            case SELECT:
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        crudActions.onSelectClicked(getIndex());
                    }
                });
                break;
        }
    }
}