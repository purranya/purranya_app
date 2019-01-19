package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

public class DayView implements Initializable {
    @FXML private Text date;
    @FXML private VBox eventList;

    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTime currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());
    }
}
