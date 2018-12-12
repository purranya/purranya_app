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
     * TODO dodać obsługę zalogowanego/niezalogowanego użytkownika
     * TODO dodać przycisk na utworzenie konta w zależności od tego, czy użytkownik jest zalogowany*/
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

    /** TODO dodać obsługę zalogowanego użytkownika! (dotyczy zmiennej user) */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText("");
        DateTime dateTime = DateTime.now();
        date.setText(dateTime.dayOfMonth().getAsString() + " " + dateTime.monthOfYear().getAsText() + " " + dateTime.year().getAsString());
    }

    /** FUNKCJA TESTOWA */
    @FXML
    void runTest(ActionEvent event) {
        Popup p = new Popup("Zapisz zmiany", "Czy na pewno chcesz zachować zmiany", Popup.POPUP_TYPE.THREE_WAY);
        int answer = p.display();
        if(answer==0)
            System.out.println("Anuluj");
        if(answer==1)
            System.out.println("Tak");
        if(answer==2)
            System.out.println("Nie");
    }
}
