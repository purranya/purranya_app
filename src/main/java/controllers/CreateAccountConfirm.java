package controllers;

import application.SceneOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateAccountConfirm {
    @FXML void login(ActionEvent event) {
        SceneOptions scene = new SceneOptions(event);
        scene.change("LoginUser");
    }

    @FXML
    void ret(ActionEvent event) {
        SceneOptions scene = new SceneOptions(event);
        scene.change("Login");
    }
}
