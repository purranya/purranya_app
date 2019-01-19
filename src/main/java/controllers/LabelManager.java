package controllers;

import application.App;
import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

public class LabelManager implements Initializable {
    @FXML private Text date;

    private DateTime currentDateTime;

    /** (button) dodawanie etykiety
     * TODO zrobić obsługę! */
    @FXML
    void add(ActionEvent event) {
        controllers.AddLabel.display();
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
    }
}
