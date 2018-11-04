package controllers;

import application.SceneOptions;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class RestorePassword {

    @FXML private ImageView logo;
    @FXML private JFXButton ret;
    SceneOptions scene;

    /* przycisk "Powrót" */
    @FXML void retAction(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("Login");
    }
}
