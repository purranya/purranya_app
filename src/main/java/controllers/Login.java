package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/** TODO zrobić jako popup
 * TODO przebudować całą scenę (dostosować do okienka popup) */
public class Login {

    /** Obsługa przycisku "Powrót" */
    @FXML
    void retAction(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    /** Obsługa przycisku "Utwórz konto" */
    @FXML
    void createAccount(ActionEvent event) {
        App.primaryStageManager.setScene("CreateAccount");
    }

}
