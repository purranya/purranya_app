package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/** TODO zrobić jako popup */
public class AddEventController {

    /** dodanie wydarzenia do kalendarza */
    @FXML
    void addEvent(ActionEvent event) {
        //póki co nic nie robi - po prostu przenosi do kalendarza
        App.primaryStageManager.setScene("Calendar");
    }

    /** wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancelAddingEvent(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }

}
