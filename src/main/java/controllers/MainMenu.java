package controllers;

import api_client.Server;
import app.App;
import app.GlobalOptions;
import app.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.db_models.Calendar;
import models.transfer_models.UserCalendarIndex;
import org.joda.time.DateTime;

import java.net.URL;
import java.util.ResourceBundle;

/** kontroler obsługujący główne menu */
public class MainMenu implements Initializable
{
    public JFXButton registerBtn;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private HBox calendarList = new HBox(5);
    @FXML
    private Text user;
    @FXML
    private Text date;
    @FXML
    private JFXButton logInOut;
    @FXML
    private JFXButton addCalendarBtn;
    @FXML
    private JFXButton stickyNotesBtn;

    @FXML
    void loginLogout(ActionEvent event)
    {
        if(!App.isAuthorized)
        {
            LoginPopup.display();
            if(App.isAuthorized)
                new PrimaryStageManager().loadScene("MainMenu");
        }
        else
        {
            App.login=null;
            App.isAuthorized=false;
            new PrimaryStageManager().loadScene("MainMenu");
        }
    }

    /**
     * (button) Dodanie nowego kalendarza do bazy
     */
    @FXML
    void addCalendar(ActionEvent event)
    {
        CalendarPopup.displayAdd();
        new PrimaryStageManager().loadScene("MainMenu");
    }

    /**
     * (button) obsługa przycisku "Sticky Notes
     */
    @FXML
    void changeToStickyNotes(ActionEvent event)
    {
        new PrimaryStageManager().loadScene("StickyNotes");
    }

    /**
     * (button) obsługa przycisku "Załóż konto"
     * TODO ustawić wyświetlanie przycisku w zależności od tego, czy jest zalogowany użytkownik
     */
    @FXML
    void register(ActionEvent event)
    {

        RegisterPopup.display();
        if (App.isAuthorized)
            new PrimaryStageManager().loadScene("MainMenu");
    }

    @FXML
    void settings(ActionEvent event)
    {
        SettingsPopup.display();
        new PrimaryStageManager().loadScene("MainMenu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        //wyświetlanie aktualnej daty
        DateTime dateTime = DateTime.now();
        date.setText(dateTime.dayOfMonth().getAsString() + " " + dateTime.monthOfYear().getAsText() + " " + dateTime.year().getAsString());

        if(App.isAuthorized)
        {
            logInOut.setText("[Wyloguj]");
            user.setText(App.login.getUsername());
            registerBtn.setVisible(false);


            UserCalendarIndex index = Server.getCalendarIndex(App.login.getUsername(),App.login.getPassword());

            for(Calendar calendar : index.getCalendars())
            {
                JFXButton buttonTemp = new JFXButton();
                buttonTemp.setPrefWidth(150);
                buttonTemp.setPrefHeight(150);
                buttonTemp.setText(calendar.getName());
                buttonTemp.setOnAction(e ->
                {
                    //Tutaj odpalanie kalendarza
                });
                calendarList.getChildren().add(buttonTemp);
            }
        }
        else
        {
            user.setText("");
            addCalendarBtn.setDisable(true);
            stickyNotesBtn.setDisable(true);
        }
    }
}
