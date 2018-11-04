package controllers;

import application.SceneOptions;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class CreateAccount {

    @FXML private ImageView logo;
    @FXML private JFXButton ret;
    private SceneOptions scene;

    /* przycisk "Powrót" */
    @FXML void retAction(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("Login");
    }

}
