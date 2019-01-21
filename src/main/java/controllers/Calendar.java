package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import data_util.DateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Note;
import org.joda.time.DateTime;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Calendar implements Initializable {

    private static DateTime currentMonth;

    @FXML
    private Text header;

    @FXML
    private Text date;

    @FXML
    private GridPane days;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentMonth = new DateTime(DateTime.now().year().get(), DateTime.now().monthOfYear().get(), 1, 0, 0, 0);
        createCalendar();
    }

    public void createCalendar() {
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
                    DayView.display(link);
                });

                VBox dayField = new VBox(dayLabel);

                List<Note> notes = App.calendarManager.getNotesByDate(day);

                for (int i = 0; i < 3 && i < notes.size(); i++) {
                    if (notes.get(i).title.length() > 15)
                        events[i] = new JFXButton(notes.get(i).title.substring(0, 12) + "...");
                    else
                        events[i] = new JFXButton(notes.get(i).title);
                    final int index = i;
                    events[i].setOnAction(e->{
                        EventPopup.displayEdit(notes.get(index).id);
                        App.primaryStageManager.reloadScene("Calendar");
                    });
                    dayField.getChildren().add(events[i]);
                }

                if (notes.size()>3) {
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
        App.primaryStageManager.reloadScene("Calendar");
    }

    /**
     * (button) usunięcie bieżącego kalendarza
     */
    @FXML
    void delete(ActionEvent event) {
        boolean answer = ConfirmPopup.display("Czy na pewno chcesz usunąć kalendarz\n \"" + App.calendarManager.getCalendarName() + "\"?");
        if (answer) {
            App.calendarManager.deleteCalendar();
            App.primaryStageManager.reloadScene("MainMenu");
        }
    }

    /**
     * edycja bieżącego kalendarza
     * TODO wczytywanie informacji z bieżącego kalendarza (może w konstruktorze
     */
    @FXML
    void edit(ActionEvent event) {
        CalendarPopup.displayEdit();
    }

    /**
     * (button) wyjście z kalendarza
     */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.reloadScene("MainMenu");
    }

    /**
     * (button) przejście do sceny zarządzającej etykietami
     */
    @FXML
    void manageLabels(ActionEvent event) {
        App.primaryStageManager.reloadScene("LabelManager");

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
