package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.joda.time.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Calendar implements Initializable {
    @FXML private Text headerOfCalendar;
    @FXML private GridPane calendarGrid;

    private DateTime currentDateTime;
    List<BorderPane> day = new ArrayList<>(42);

    private void createCalendar(DateTime dt) {
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
        headerOfCalendar.setText(month + " " + dt.getYear());

    }

    @FXML
    void nextMonth(ActionEvent event) {
        currentDateTime = currentDateTime.plusMonths(1);
        createCalendar(currentDateTime);
    }

    @FXML
    void previousMonth(ActionEvent event) {
        currentDateTime = currentDateTime.minusMonths(1);
        createCalendar(currentDateTime);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDateTime = DateTime.now();
        createCalendar(currentDateTime);
    }
}
