package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.joda.time.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/** kontroler obsługujący kalendarz
 * TODO dodać obsługę wydarzeń
 * TODO dodać obsługę przycisku zarządzania etykietami */
public class Calendar implements Initializable {
    @FXML private Text header;
    @FXML private Text date;

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
        controllers.AddEvent.display();
    }

    /** (button) wyjście z kalendarza */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());
        createCalendar(currentDateTime);
    }
}
