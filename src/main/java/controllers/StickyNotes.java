package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.joda.time.DateTime;


import java.net.URL;
import java.util.ResourceBundle;

public class StickyNotes implements Initializable {

    @FXML
    private Text date;

    /** dodanie nowej notatki */
    /** TODO utworzyć scenę dla dodawania notatek */
    @FXML
    void addNote(ActionEvent event) {
        App.primaryStageManager.setScene("AddNote");
    }

    /** wyjście ze Sticky Notes */
    @FXML
    void closeStickyNotes(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTime currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());
    }
}