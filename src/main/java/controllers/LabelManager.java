package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import data.CalendarManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

public class LabelManager implements Initializable {
    @FXML private Text date;
    @FXML private VBox labelList;


    private DateTime currentDateTime;

    /** (button) dodawanie etykiety
     * TODO zrobić obsługę! */
    @FXML
    void add(ActionEvent event) {
        LabelPopup.display();
        App.primaryStageManager.reloadScene("LabelManager");
    }

    /** (button) wyjście z okna obsługi etykiet */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());


        //wyświetlanie wszystkich etykiet jako buttony
        CalendarManager labels = App.calendarManager;
        for(String labelName : labels.getLabels()) {
            if(labelName.equals("null"))
                continue;
            JFXButton labelTemp = new JFXButton();
            labelTemp.setPrefWidth(400);
            labelTemp.setPrefHeight(30);
            labelTemp.setText(labelName);
            //labelTemp.setOnAction(e -> {
            //    calendars.loadCalendar(calendarName);
            //    App.primaryStageManager.setScene("Calendar");
            //});
            labelList.getChildren().add(labelTemp);
        }
    }
}
