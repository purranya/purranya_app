package controllers;

import application.App;
import application.Logging;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Note;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.List;
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
        date.setText(currentDate.toString("dd MMMM YYYY"));
        List<Note> notes = App.calendarManager.getNotesByDate(currentDate);
        for(Note note : notes)
        {
            JFXButton button = new JFXButton(note.title);
            button.setOnAction(e->{
                EventPopup.displayEdit(note.id);
                stage.setScene(loadScene());
            });
            eventList.getChildren().add(button);

        }
    }

    private static Stage stage;
    private static DateTime currentDate;

    public static void display(DateTime currentDate) {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(543);
            stage.setResizable(false);
            stage.setTitle("Wydarzenia");
        }
        DayView.currentDate=currentDate;
        stage.setScene(loadScene());
        stage.showAndWait();
    }

    /** załadowanie sceny */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(LabelPopup.class.getClassLoader().getResource("fxml/DayView.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup EventPopup initialization failed");
            Logging.Logger.logError("Popup EventPopup initialization failed");
        }
        return null;
    }
}
