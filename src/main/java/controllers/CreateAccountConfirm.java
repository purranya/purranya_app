package controllers;

import application.App;
import application.PrimaryStageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateAccountConfirm {
    @FXML void login(ActionEvent event) {
        App.primaryStageManager.setScene("Login");
    }

    @FXML
    void ret(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }
}
