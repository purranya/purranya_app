package controllers;

import application.App;
import models.TestModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

/** kontroler obsługujący główne menu */
public class MainMenu implements Initializable {
    @FXML private Text user;
    @FXML private Text date;

    /** (button) Obsługa przycisku "Zaloguj się"
     * TODO dodać obsługę zalogowanego/niezalogowanego użytkownika
     * TODO dodać przycisk na utworzenie konta w zależności od tego, czy użytkownik jest zalogowany */
    @FXML
    void loginLogout(ActionEvent event) {
        //jeżeli niezalogowany
        Login.display();
    }

    /** (button) Dodanie nowego kalendarza do bazy */
    @FXML
    void addCalendar(ActionEvent event) {
        controllers.AddCalendar.display();
    }

    /** (button) obsługa przycisku "Menedżer haseł */
    @FXML
    void changeToPasswordManager(ActionEvent event) {
        App.primaryStageManager.setScene("PasswordManager");
    }

    /** (button) obsługa przycisku "Sticky Notes */
    @FXML
    void changeToStickyNotes(ActionEvent event) {
        App.primaryStageManager.setScene("StickyNotes");
    }

    /** TEST
     * TODO usunąć po przeprowadzeniu testów */
    @FXML
    void continueWithoutAccount(ActionEvent event) { App.primaryStageManager.setScene("Calendar");
    }

    /** (button) obsługa przycisku "Załóż konto"
     * TODO ustawić wyświetlanie przycisku w zależności od tego, czy jest zalogowany użytkownik */
    @FXML
    void register(ActionEvent event) {
        Register.display();
    }

    /** TODO dodać obsługę zalogowanego użytkownika! (dotyczy zmiennej user) */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText("");
        DateTime dateTime = DateTime.now();
        date.setText(dateTime.dayOfMonth().getAsString() + " " + dateTime.monthOfYear().getAsText() + " " + dateTime.year().getAsString());
    }

    /** FUNKCJA TESTOWA
     * TODO w późniejszym czasie do usunięcia!*/
    @FXML
    void runTest(ActionEvent event) {
        //App.primaryStageManager.popup("AddEvent", new AddEvent());
        TestModel answer = Popup.display("Zapisz zmiany", "Czy na pewno chcesz zachować zmiany");
        System.out.println(answer);
    }
}
