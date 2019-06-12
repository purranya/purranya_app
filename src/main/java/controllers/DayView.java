package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
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
import models.db_models.Event;
import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DayView implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private VBox eventList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        CalendarManager calendarManager = new CalendarManager();
        List<Event> events = calendarManager.getNotesByDate(currentDate);
        for(Event event : events)
        {
            JFXButton button = new JFXButton(event.getName());
            JFXButton delete = new JFXButton("Usuń");
            delete.setStyle("-fx-border-color: #89b2ac");
            HBox hBox = new HBox(button, delete);
            button.setPrefWidth(360);
            button.setOnAction(e->{
                EventPopup.displayEdit(event);
                stage.setScene(loadScene());
            });
            delete.setOnAction((e)->{
                eventList.getChildren().remove(hBox);
                boolean status = Server.modelAction(App.login.getUsername(),App.login.getPassword(),event, ModelAction.DELETE);
                if(!status)
                    Logging.logError("Deleting event failed\n");
                CalendarManager.reloacCalendar();

            });
            eventList.getChildren().add(hBox);

        }
    }

    private static Stage stage;
    private static DateTime currentDate;

    private static Scene loadScene()
    {
        try
        {
            URL url = DayView.class.getClassLoader().getResource("fxml/DayView.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e)
        {
            Logging.logError("Popup DayView initialization failed\n" + e.toString());
        }
        return null;
    }

    public static void display(DateTime time)
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(450);
        stage.setHeight(543);
        stage.setResizable(false);
        stage.setTitle("Wydarzenia");
        currentDate = time;
        stage.setScene(loadScene());
        stage.showAndWait();
        stage = null;

    }
}