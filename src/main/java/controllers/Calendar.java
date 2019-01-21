package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Note;
import org.joda.time.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/** kontroler obsługujący kalendarz
 * TODO dodać obsługę wydarzeń
 * TODO zrobić coś z */
public class Calendar implements Initializable {
    @FXML private Text header;
    @FXML private Text date;

    //dni miesiąca
    @FXML private Text dayNumber00;
    @FXML private Text dayNumber01;
    @FXML private Text dayNumber02;
    @FXML private Text dayNumber03;
    @FXML private Text dayNumber04;
    @FXML private Text dayNumber05;
    @FXML private Text dayNumber06;
    @FXML private Text dayNumber10;
    @FXML private Text dayNumber11;
    @FXML private Text dayNumber12;
    @FXML private Text dayNumber13;
    @FXML private Text dayNumber14;
    @FXML private Text dayNumber15;
    @FXML private Text dayNumber16;
    @FXML private Text dayNumber20;
    @FXML private Text dayNumber21;
    @FXML private Text dayNumber22;
    @FXML private Text dayNumber23;
    @FXML private Text dayNumber24;
    @FXML private Text dayNumber25;
    @FXML private Text dayNumber26;
    @FXML private Text dayNumber30;
    @FXML private Text dayNumber31;
    @FXML private Text dayNumber32;
    @FXML private Text dayNumber33;
    @FXML private Text dayNumber34;
    @FXML private Text dayNumber35;
    @FXML private Text dayNumber36;
    @FXML private Text dayNumber40;
    @FXML private Text dayNumber41;
    @FXML private Text dayNumber42;
    @FXML private Text dayNumber43;
    @FXML private Text dayNumber44;
    @FXML private Text dayNumber45;
    @FXML private Text dayNumber46;
    @FXML private Text dayNumber50;
    @FXML private Text dayNumber51;
    @FXML private Text dayNumber52;
    @FXML private Text dayNumber53;
    @FXML private Text dayNumber54;
    @FXML private Text dayNumber55;
    @FXML private Text dayNumber56;

    //miejsce na ostatnie 3 wydarzenia
    @FXML private VBox events00;
    @FXML private VBox events01;
    @FXML private VBox events02;
    @FXML private VBox events03;
    @FXML private VBox events04;
    @FXML private VBox events05;
    @FXML private VBox events06;
    @FXML private VBox events10;
    @FXML private VBox events11;
    @FXML private VBox events12;
    @FXML private VBox events13;
    @FXML private VBox events14;
    @FXML private VBox events15;
    @FXML private VBox events16;
    @FXML private VBox events20;
    @FXML private VBox events21;
    @FXML private VBox events22;
    @FXML private VBox events23;
    @FXML private VBox events24;
    @FXML private VBox events25;
    @FXML private VBox events26;
    @FXML private VBox events30;
    @FXML private VBox events31;
    @FXML private VBox events32;
    @FXML private VBox events33;
    @FXML private VBox events34;
    @FXML private VBox events35;
    @FXML private VBox events36;
    @FXML private VBox events40;
    @FXML private VBox events41;
    @FXML private VBox events42;
    @FXML private VBox events43;
    @FXML private VBox events44;
    @FXML private VBox events45;
    @FXML private VBox events46;
    @FXML private VBox events50;
    @FXML private VBox events51;
    @FXML private VBox events52;
    @FXML private VBox events53;
    @FXML private VBox events54;
    @FXML private VBox events55;
    @FXML private VBox events56;

    //skrócona lista wydarzeń
    @FXML private JFXButton moreEvents00;
    @FXML private JFXButton moreEvents01;
    @FXML private JFXButton moreEvents02;
    @FXML private JFXButton moreEvents03;
    @FXML private JFXButton moreEvents04;
    @FXML private JFXButton moreEvents05;
    @FXML private JFXButton moreEvents06;
    @FXML private JFXButton moreEvents10;
    @FXML private JFXButton moreEvents11;
    @FXML private JFXButton moreEvents12;
    @FXML private JFXButton moreEvents13;
    @FXML private JFXButton moreEvents14;
    @FXML private JFXButton moreEvents15;
    @FXML private JFXButton moreEvents16;
    @FXML private JFXButton moreEvents20;
    @FXML private JFXButton moreEvents21;
    @FXML private JFXButton moreEvents22;
    @FXML private JFXButton moreEvents23;
    @FXML private JFXButton moreEvents24;
    @FXML private JFXButton moreEvents25;
    @FXML private JFXButton moreEvents26;
    @FXML private JFXButton moreEvents30;
    @FXML private JFXButton moreEvents31;
    @FXML private JFXButton moreEvents32;
    @FXML private JFXButton moreEvents33;
    @FXML private JFXButton moreEvents34;
    @FXML private JFXButton moreEvents35;
    @FXML private JFXButton moreEvents36;
    @FXML private JFXButton moreEvents40;
    @FXML private JFXButton moreEvents41;
    @FXML private JFXButton moreEvents42;
    @FXML private JFXButton moreEvents43;
    @FXML private JFXButton moreEvents44;
    @FXML private JFXButton moreEvents45;
    @FXML private JFXButton moreEvents46;
    @FXML private JFXButton moreEvents50;
    @FXML private JFXButton moreEvents51;
    @FXML private JFXButton moreEvents52;
    @FXML private JFXButton moreEvents53;
    @FXML private JFXButton moreEvents54;
    @FXML private JFXButton moreEvents55;
    @FXML private JFXButton moreEvents56;

    private DateTime currentDateTime;

    /** tworzenie kalendarza na bieżącej scenie */
    private void createCalendar(DateTime dt) {
        //_____________________________________________________
        //nagłówek
        String month = "";
        switch(dt.monthOfYear().getAsString()) {
            case "1":
                month = "Styczeń";
                break;
            case "2":
                month = "Luty";
                break;
            case "3":
                month = "Marzec";
                break;
            case "4":
                month = "Kwiecień";
                break;
            case "5":
                month = "Maj";
                break;
            case "6":
                month = "Czerwiec";
                break;
            case "7":
                month = "Lipiec";
                break;
            case "8":
                month = "Sierpień";
                break;
            case "9":
                month = "Wrzesień";
                break;
            case "10":
                month = "Październik";
                break;
            case "11":
                month = "Listopad";
                break;
            case "12":
                month = "Grudzień";
                break;
        }
        header.setText(month + " " + dt.getYear());

        //_____________________________________________________
        //dni miesiąca
        List<Text> dayNumber = new ArrayList<>();
        dayNumber.add(dayNumber00);
        dayNumber.add(dayNumber01);
        dayNumber.add(dayNumber02);
        dayNumber.add(dayNumber03);
        dayNumber.add(dayNumber04);
        dayNumber.add(dayNumber05);
        dayNumber.add(dayNumber06);
        dayNumber.add(dayNumber10);
        dayNumber.add(dayNumber11);
        dayNumber.add(dayNumber12);
        dayNumber.add(dayNumber13);
        dayNumber.add(dayNumber14);
        dayNumber.add(dayNumber15);
        dayNumber.add(dayNumber16);
        dayNumber.add(dayNumber20);
        dayNumber.add(dayNumber21);
        dayNumber.add(dayNumber22);
        dayNumber.add(dayNumber23);
        dayNumber.add(dayNumber24);
        dayNumber.add(dayNumber25);
        dayNumber.add(dayNumber26);
        dayNumber.add(dayNumber30);
        dayNumber.add(dayNumber31);
        dayNumber.add(dayNumber32);
        dayNumber.add(dayNumber33);
        dayNumber.add(dayNumber34);
        dayNumber.add(dayNumber35);
        dayNumber.add(dayNumber36);
        dayNumber.add(dayNumber40);
        dayNumber.add(dayNumber41);
        dayNumber.add(dayNumber42);
        dayNumber.add(dayNumber43);
        dayNumber.add(dayNumber44);
        dayNumber.add(dayNumber45);
        dayNumber.add(dayNumber46);
        dayNumber.add(dayNumber50);
        dayNumber.add(dayNumber51);
        dayNumber.add(dayNumber52);
        dayNumber.add(dayNumber53);
        dayNumber.add(dayNumber54);
        dayNumber.add(dayNumber55);
        dayNumber.add(dayNumber56);

        //dzień w kalendarzu - zaczyna się zawsze od pierwszego
        DateTime dayInCalendar = dt.withDayOfMonth(1);
        //cofnięcie kalendarza w celu uzupełnienia tygodni
        while (!dayInCalendar.dayOfWeek().getAsString().equals("1"))
                dayInCalendar = dayInCalendar.minusDays(1);

        for(Text day : dayNumber) {
            day.setText(dayInCalendar.dayOfMonth().getAsString());
            dayInCalendar = dayInCalendar.plusDays(1);
        }
    }

    /** (button) przejście do sceny zarządzającej etykietami */
    @FXML
    void manageLabels(ActionEvent event) {
        App.primaryStageManager.reloadScene("LabelManager");
    }

    /** (button) wyświetlenie miesiąca następnego niż na bieżącej scenie */
    @FXML
    void nextMonth(ActionEvent event) {
        currentDateTime = currentDateTime.plusMonths(1);
        createCalendar(currentDateTime);
    }

    /** (button) wyświetlenie miesiąca poprzedniego niż na bieżącej scenie */
    @FXML
    void previousMonth(ActionEvent event) {
        currentDateTime = currentDateTime.minusMonths(1);
        createCalendar(currentDateTime);
    }

    /** (button) przejście do popupu umożliwiającej dodanie wydarzenia */
    @FXML
    void addEvent(ActionEvent event) {
        EventPopup.display();
        App.primaryStageManager.reloadScene("Calendar");
    }

    /** (button) usunięcie bieżącego kalendarza */
    @FXML
    void delete(ActionEvent event) {
        boolean answer = ConfirmPopup.display("Czy na pewno chcesz usunąć kalendarz\n \"" + App.calendarManager.getCalendarName() + "\"?");
        if(answer) {
            App.calendarManager.deleteCalendar();
            App.primaryStageManager.reloadScene("MainMenu");
        }
    }

    /** edycja bieżącego kalendarza
     * TODO wczytywanie informacji z bieżącego kalendarza (może w konstruktorze */
    @FXML
    void edit(ActionEvent event) {
        CalendarPopup.displayEdit();
    }

    /** (button) wyjście z kalendarza */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.reloadScene("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());
        createCalendar(currentDateTime);
    }
}
