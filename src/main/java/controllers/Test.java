package controllers;


import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Test {

    @FXML
    void anuluj(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    @FXML
    void gotowe(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

}