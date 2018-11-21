package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static application.StageOptions.setStage;

public class CreateAccountConfirm {
    @FXML void login(ActionEvent event) {
        setStage("LoginUser");
    }

    @FXML
    void ret(ActionEvent event) {
        setStage("Login");
    }
}
