package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML private Text user;
    @FXML private Text date;

    /** Obsługa przycisku "Zaloguj się"
     * TODO dodać obsługę zalogowanego/niezalogowanego użytkownika */
    @FXML
    void loginLogout(ActionEvent event) {
        //jeżeli niezalogowany
        App.primaryStageManager.setScene("Login");
    }

    /** Dodanie nowego kalendarza do bazy
     * TODO uzupełnić! */
    @FXML
    void addCalendar(ActionEvent event) {
        App.primaryStageManager.setScene("AddCalendar");
    }

    /** obsługa przycisku "Menedżer haseł */
    @FXML
    void changeToPasswordManager(ActionEvent event) {
        App.primaryStageManager.setScene("PasswordManager");
    }

    /** obsługa przycisku "Sticky Notes */
    @FXML
    void changeToStickyNotes(ActionEvent event) {
        App.primaryStageManager.setScene("StickyNotes");
    }

    /** TEST
     * TODO usunąć po przeprowadzeniu testów */
    @FXML
    void continueWithoutAccount(ActionEvent event) { App.primaryStageManager.setScene("Calendar");
    }

    /** TODO dodać obsługę zalogowanego użytkownika! */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText("");
        DateTime dateTime = DateTime.now();
        date.setText(dateTime.dayOfMonth().getAsString() + " " + dateTime.monthOfYear().getAsText() + " " + dateTime.year().getAsString());
    }
}
