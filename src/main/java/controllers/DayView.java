package controllers;

import app.App;
import app.Logging;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Note;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DayView implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private VBox eventList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");

        List<Note> notes = App.calendarManager.getNotesByDate(currentDate);
        for(Note note : notes)
        {
            JFXButton button = new JFXButton(note.title);
            JFXButton delete = new JFXButton("Usuń");
            delete.setStyle("-fx-border-color: #89b2ac");
            HBox hBox = new HBox(button, delete);
            button.setPrefWidth(360);
            button.setOnAction(e->{
                EventPopup.displayEdit(note.id);
                stage.setScene(loadScene());
            });
            eventList.getChildren().add(hBox);

        }
    }

    private static Stage stage;
    private static DateTime currentDate;

    public static void display(DateTime currentDate) {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(450);
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
            return new Scene(FXMLLoader.load(DayView.class.getClassLoader().getResource("fxml/DayView.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup EventPopup initialization failed");
            Logging.Logger.logError("Popup EventPopup initialization failed");
        }
        return null;
    }
}
