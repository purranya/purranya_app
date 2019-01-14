package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/** TODO zrobić jako popup
 * TODO przebudować całą scenę (dostosować do okienka popup) */
public class LoginController {

    /** Obsługa przycisku "Powrót" */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    /** Obsługa przycisku "Zaloguj"
     * TODO dodać obsługę!
     * */
    @FXML
    void login(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

}
