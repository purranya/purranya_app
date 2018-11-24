package controllers;

import application.App;
import application.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class RestorePassword {

    @FXML private ImageView logo;
    @FXML private JFXButton ret;
    PrimaryStageManager scene;

    /* przycisk "Powrót" */
    @FXML void retAction(ActionEvent event) {
        App.primaryStageManager.setScene("Login");
    }
}
