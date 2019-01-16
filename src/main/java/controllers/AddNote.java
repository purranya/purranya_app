package controllers;

import application.Logging;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi dodawania notatek w StickyNotes */
public class AddNote {
    @FXML private JFXTextField title;
    @FXML private JFXComboBox<?> label;
    @FXML private TextArea description;
    @FXML private Text validationText;

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) dodanie notatki do bazy
     * TODO do zrobienia */
    @FXML
    void add(ActionEvent event) {
    }

    /** (button) zamknięcie okna AddNote */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddNote.class.getClassLoader().getResource("fxml/AddNote.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddNote initialization failed");
            Logging.Logger.logError("Popup AddNote initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(stage == null) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(540);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Dodaj notatkę - Purranya");

        stage.showAndWait();
    }

}
