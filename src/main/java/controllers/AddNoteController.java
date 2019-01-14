package controllers;


import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddNoteController {

    /** dodanie notatki do bazy
     * TODO zrobić obsługę z bazą
     */
    @FXML
    void addNote(ActionEvent event) {

    }

    /** zamknięcie okna AddNoteController */
    @FXML
    void cancelAddingNote(ActionEvent event) {
        App.primaryStageManager.setScene("StickyNotes");
    }

}
