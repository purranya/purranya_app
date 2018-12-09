package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddEvent {

    /** dodanie wydarzenia do kalendarza */
    @FXML
    void addEvent(ActionEvent event) {
        //nic nie robi - przenosi do kalendarza
        App.primaryStageManager.setScene("Calendar");
    }

    /** wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancelAddingEvent(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }

}
