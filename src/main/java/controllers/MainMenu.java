package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import data.CalendarManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

/** kontroler obsługujący główne menu */
public class MainMenu implements Initializable {
    @FXML private HBox calendarList = new HBox(5);
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
        CalendarPopup.display();
        App.primaryStageManager.reloadScene("MainMenu");
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

    /** (button) obsługa przycisku "Załóż konto"
     * TODO ustawić wyświetlanie przycisku w zależności od tego, czy jest zalogowany użytkownik */
    @FXML
    void register(ActionEvent event) {
        Register.display();
    }

    /** TODO dodać obsługę zalogowanego użytkownika! (dotyczy zmiennej user) */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wyświetlanie aktualnej daty
        user.setText("");
        DateTime dateTime = DateTime.now();
        date.setText(dateTime.dayOfMonth().getAsString() + " " + dateTime.monthOfYear().getAsText() + " " + dateTime.year().getAsString());

        //wyświetlanie wszystkich kalendarzy jako buttony
        CalendarManager calendars = App.calendarManager;
        for(String calendarName : calendars.getCalendarIndex()) {
            JFXButton buttonTemp = new JFXButton();
            buttonTemp.setPrefWidth(150);
            buttonTemp.setPrefHeight(150);
            buttonTemp.setText(calendarName);
            buttonTemp.setOnAction(e -> {
                calendars.loadCalendar(calendarName);
                App.primaryStageManager.setScene("Calendar");
            });
            calendarList.getChildren().add(buttonTemp);
        }
    }
}
