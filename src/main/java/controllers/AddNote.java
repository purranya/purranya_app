package controllers;

import application.App;
import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi dodawania notatek w StickyNotes */
public class AddNote {
    private static Stage addNoteStage;
    private static Scene addNoteScene = loadScene();

    /** (button) dodanie notatki do bazy */
    @FXML
    void addNote(ActionEvent event) {
        /** TODO do zrobienia */
    }

    /** (button) zamknięcie okna AddNote */
    @FXML
    void cancelAddingNote(ActionEvent event) {
        addNoteStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddEvent.class.getClassLoader().getResource("fxml/AddNote.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddNote initialization failed");
            Logging.Logger.logError("Popup AddNote initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(addNoteStage == null) {
            addNoteStage = new Stage();
            addNoteStage.initModality(Modality.APPLICATION_MODAL);
            addNoteStage.setWidth(362);
            addNoteStage.setHeight(510);
            addNoteStage.setResizable(false);
            addNoteStage.setScene(addNoteScene);
        }
        addNoteStage.setTitle("Dodaj notatkę - Purranya");

        addNoteStage.showAndWait();
    }

}
