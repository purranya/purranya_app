package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
import app.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;
import models.db_models.Event;
import utils.DateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.joda.time.DateTime;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Calendar implements Initializable {

    private static DateTime currentMonth;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text header;

    @FXML
    private Text date;

    @FXML
    private GridPane days;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        currentMonth = new DateTime(DateTime.now().year().get(), DateTime.now().monthOfYear().get(), 1, 0, 0, 0);
        createCalendar();
    }

    public void createCalendar() {
        CalendarManager calendarManager = new CalendarManager();

        days.getChildren().clear();
        header.setText(DateUtils.getMonthName(currentMonth.monthOfYear().get()) + " " + currentMonth.year().get());
        date.setText(DateTime.now().toString("dd MMMM YYYY"));

        //getting first monday
        DateTime day = currentMonth.minusDays(currentMonth.dayOfWeek().get() - 1);

        for (int week_i = 0; week_i < 6; week_i++) {
            for (int day_i = 0; day_i < 7; day_i++) {
                Text dayLabel = new Text(Integer.toString(day.getDayOfMonth()));
                dayLabel.getStyleClass().add("text-area");
                JFXButton[] events = new JFXButton[3];
                JFXButton moreEvents = new JFXButton("Wyświetl więcej");
                moreEvents.getStyleClass().add("jfx-button-details");
                final DateTime link = day;
                moreEvents.setOnAction(e -> {
                    //DayView.display(link);
                });

                VBox dayField = new VBox(dayLabel);

                List<Event> dayEvents = calendarManager.getNotesByDate(day);

                for (int i = 0; i < 3 && i < dayEvents.size(); i++) {
                    if (dayEvents.get(i).getName().length() > 15)
                        events[i] = new JFXButton(dayEvents.get(i).getName().substring(0, 12) + "...");
                    else
                        events[i] = new JFXButton(dayEvents.get(i).getName());
                    final int index = i;
                    events[i].setOnAction(e->{
                        EventPopup.displayEdit(dayEvents.get(index));
                        new PrimaryStageManager().loadScene("Calendar");
                    });
                    dayField.getChildren().add(events[i]);
                }

                if (dayEvents.size()>3) {
                    dayField.getChildren().add(moreEvents);
                }

                days.add(dayField, day_i, week_i);

                day = day.plusDays(1);
            }
        }
    }

    /**
     * (button) przejście do popupu umożliwiającej dodanie wydarzenia
     */
    @FXML
    void addEvent(ActionEvent event) {
        EventPopup.displayAdd();
        new PrimaryStageManager().loadScene("Calendar");
    }

    /**
     * (button) usunięcie bieżącego kalendarza
     */
    @FXML
    void delete(ActionEvent event) {
        CalendarManager calendarManager = new CalendarManager();
        Server.modelAction(App.login.getUsername(),App.login.getPassword(),calendarManager.getCalendar(), ModelAction.DELETE);
        new PrimaryStageManager().loadScene("MainMenu");
    }

    /**
     * edycja bieżącego kalendarza */
    @FXML
    void edit(ActionEvent event) {
        CalendarManager calendarManager = new CalendarManager();
        CalendarPopup.displayEdit(calendarManager.getCalendar());
        new PrimaryStageManager().loadScene("Calendar");
    }

    /**
     * (button) wyjście z kalendarza
     */
    @FXML
    void cancel(ActionEvent event) {
        new PrimaryStageManager().loadScene("MainMenu");
    }

    /**
     * (button) przejście do sceny zarządzającej etykietami
     */
    @FXML
    void manageLabels(ActionEvent event) {
        new PrimaryStageManager().loadScene("LabelManager");

    }

    /**
     * (button) wyświetlenie miesiąca następnego niż na bieżącej scenie
     */
    @FXML
    void nextMonth(ActionEvent event) {
        currentMonth = currentMonth.plusMonths(1);
        createCalendar();
    }

    /**
     * (button) wyświetlenie miesiąca poprzedniego niż na bieżącej scenie
     */
    @FXML
    void previousMonth(ActionEvent event) {
        currentMonth = currentMonth.minusMonths(1);
        createCalendar();
    }
}