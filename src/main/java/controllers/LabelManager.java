package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
import app.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.db_models.Label;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

public class LabelManager implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private Text date;
    @FXML private VBox labelList;


    private DateTime currentDateTime;

    /** (button) dodawanie etykiety */
    @FXML
    void add(ActionEvent event) {
        LabelPopup.displayAdd();
        new PrimaryStageManager().loadScene("LabelManager");
    }

    /** (button) wyjście z okna obsługi etykiet */
    @FXML
    void cancel(ActionEvent event) {
        new PrimaryStageManager().loadScene("Calendar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        currentDateTime = DateTime.now();
        date.setText(currentDateTime.dayOfMonth().getAsString() + " " + currentDateTime.monthOfYear().getAsText() + " " + currentDateTime.year().getAsString());

        //wyświetlanie wszystkich etykiet jako buttony oraz przycisku do ich usunięcia
        CalendarManager calendarManager = new CalendarManager();
        for(Label label : calendarManager.getAllLabels()) {
            if(label.getName().equals(""))
                continue;
            JFXButton labelTemp = new JFXButton();
            labelTemp.setPrefWidth(600);
            labelTemp.setPrefHeight(30);
            labelTemp.setText(label.getName());
            labelTemp.setOnAction(e -> {
                LabelPopup.displayEdit(label);
                new PrimaryStageManager().loadScene("LabelManager");
            });
            JFXButton delete = new JFXButton("Usuń");
            delete.setOnAction(e->
            {
                boolean status = Server.modelAction(App.login.getUsername(),App.login.getPassword(),label, ModelAction.DELETE);
                CalendarManager.reloacCalendar();
                new PrimaryStageManager().loadScene("LabelManager");
            });
            HBox hBox = new HBox(labelTemp, delete);
            labelList.getChildren().addAll(hBox);
        }
    }
}