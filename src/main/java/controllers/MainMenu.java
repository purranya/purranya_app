package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenu {

    /** Obsługa przycisku "Zaloguj się" */
    @FXML
    void login(ActionEvent event) {
        App.primaryStageManager.setScene("Login");
    }

    /** Obsługa przycisku "Kontynuuj bez zalogowania" */
    @FXML
    void continueWithoutAccount(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }
}
