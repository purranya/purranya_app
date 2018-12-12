package controllers;


import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddCalendar {

    @FXML
    void addCalendar(ActionEvent event) {
        //póki co nic nie robi - po prostu przenosi do głównego menu
        App.primaryStageManager.setScene("MainMenu");
    }

    @FXML
    void cancelAddingCalendar(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

}

