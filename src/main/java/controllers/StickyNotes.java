package controllers;

import api_client.Server;
import app.App;
import app.GlobalOptions;
import app.Logging;
import app.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.db_models.Note;
import models.transfer_models.UserNoteIndex;
import org.joda.time.DateTime;


import java.net.URL;
import java.util.ResourceBundle;

public class StickyNotes implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text date;
    @FXML
    private JFXMasonryPane notes;

    /** (button) dodanie nowej notatki */
    @FXML
    void addNote(ActionEvent event) {
        //NotePopup.display();
    }

    /** wyjście ze Sticky Notes */
    @FXML
    void closeStickyNotes(ActionEvent event) {
        new PrimaryStageManager().loadScene("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        DateTime currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());

        try
        {
            UserNoteIndex index = Server.getNoteIndex(App.login.getUsername(), App.login.getPassword());

            JFXButton button;

            for(Note n : index.getNotes())
            {
                button = new JFXButton(n.getName());
                button.setOnAction((e)->{
                    //tutaj wyświetlenie popupa
                });

                notes.getChildren().add(button);
            }
        } catch ( Exception e )
        {
            Logging.logError("Cannot download notes");
        }
    }
}