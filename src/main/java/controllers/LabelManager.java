package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import data.CalendarManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Label;
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

    /** TODO zmienic funkcjonowanie popupu edytowania etykiety */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());


        //wyświetlanie wszystkich etykiet jako buttony oraz przycisku do ich usunięcia
        CalendarManager labels = App.calendarManager;
        for(Label labelName : labels.getAllLabels()) {
            if(labelName.text.equals(""))
                continue;
            JFXButton labelTemp = new JFXButton();
            labelTemp.setPrefWidth(600);
            labelTemp.setPrefHeight(30);
            labelTemp.setText(labelName.text);
            labelTemp.setOnAction(e -> {
                //zmienic zeby edytowalo a nie dodawalo nowe
                LabelPopup.display();
                App.primaryStageManager.reloadScene("LabelManager");
            });
            JFXButton delete = new JFXButton("Usuń");
            delete.setStyle("-fx-border-color: #89b2ac");
            HBox hBox = new HBox(labelTemp, delete);
            labelList.getChildren().addAll(hBox);
        }
    }
}
