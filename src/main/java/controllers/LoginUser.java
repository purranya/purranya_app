package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import static application.StageOptions.setStage;

public class LoginUser {
    @FXML private ImageView logo;
    @FXML private JFXButton ret;

    /* przycisk "Powrót" */
    @FXML void retAction(ActionEvent event) {
        setStage("Login");
    }

}
