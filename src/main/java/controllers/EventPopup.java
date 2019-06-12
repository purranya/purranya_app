package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.db_models.Event;
import models.db_models.Label;
import org.joda.time.DateTime;
import utils.DateUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania wydarzeń w kalendarzu */
public class EventPopup implements Initializable
{
    public JFXTimePicker timeOfStart;
    public JFXTimePicker timeOfEnd;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXDatePicker dateOfStart;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox<Label> label;
    @FXML
    private TextArea description;
    @FXML
    private JFXDatePicker dateOfEnd;
    @FXML
    private Text validationText;
    @FXML
    private Text validationData;
    @FXML
    private Text dateValidationText;
    @FXML
    private Button action;

    private static Stage stage;

    private static Event model;
    private static ModelAction modelAction;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        CalendarManager calendarManager = new CalendarManager();

        for (Label l : calendarManager.getAllLabels())
            this.label.getItems().add(l);

        if(modelAction.equals(ModelAction.EDIT))
        {
            Label currentLabel = calendarManager.getLabelById(model.getLabel_id());
            if(currentLabel.getId()!=null)
            {
                label.setValue(currentLabel);

                title.setText(model.getName());
                description.setText(model.getComment());

                DateTime startDate = model.getStartDate();
                dateOfStart.setValue(LocalDate.of(startDate.getYear(),startDate.getMonthOfYear(),startDate.getDayOfMonth()));
                timeOfStart.setValue(LocalTime.of(startDate.getHourOfDay(),startDate.getMinuteOfHour(),startDate.getSecondOfMinute()));

                DateTime endDate = model.getEndDate();
                dateOfEnd.setValue(LocalDate.of(endDate.getYear(),endDate.getMonthOfYear(),endDate.getDayOfMonth()));
                timeOfEnd.setValue(LocalTime.of(endDate.getHourOfDay(),endDate.getMinuteOfHour(),endDate.getSecondOfMinute()));
            }
        }

    }

    @FXML
    void submit(ActionEvent event)
    {
        CalendarManager calendarManager = new CalendarManager();

        model.setName(title.getText());
        model.setComment(description.getText());

        if(dateOfStart.getValue()!=null && timeOfStart.getValue() !=null)
            model.setStartDate(DateUtils.toDateTime(dateOfStart.getValue(),timeOfStart.getValue()));
        if(dateOfEnd.getValue()!=null && timeOfEnd.getValue() !=null)
            model.setEndDate(DateUtils.toDateTime(dateOfEnd.getValue(),timeOfEnd.getValue()));

        if(dateOfEnd.getValue()==null && timeOfEnd.getValue() ==null)
            model.setEndDate(DateUtils.toDateTime(dateOfStart.getValue(),timeOfStart.getValue()));

        if(dateOfStart.getValue()!=null && timeOfStart.getValue() ==null)
            model.setStartDate(DateUtils.toDateTime(dateOfStart.getValue(),LocalTime.of(0,0,0)));

        if(dateOfEnd.getValue()!=null && timeOfEnd.getValue() ==null)
            model.setEndDate(DateUtils.toDateTime(dateOfEnd.getValue(),LocalTime.of(23,59,59)));

        model.setCalendar_id(calendarManager.getCalendar().getId());

        Label selectedLabel = label.getValue();
        if(selectedLabel!=null)
            model.setLabel_id(selectedLabel.getId());

        validationText.setText("");
        validationData.setText("");
        dateValidationText.setText("");

        boolean nameValid = model.isNameValid();
        boolean commentValid = model.isCommentValid();
        boolean dateValid = model.isEndDateValid() && model.isStartDateValid();
        boolean labelValid = model.isLabelIdValid();

        if (!nameValid)
        {
            validationText.setText("Nazwa jest niepoprawna");
            validationText.setFill(Color.rgb(254, 203, 200));
        }
        if (!commentValid)
        {
            validationData.setText("Opis jest niepoprawny");
            validationData.setFill(Color.rgb(254, 203, 200));
        }
        if(!dateValid)
        {
            dateValidationText.setText("Daty są niepoprawne");
            dateValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if (!labelValid)
        {
            validationText.setText("");
            validationData.setText("");
            dateValidationText.setText("Należy wybrać etykietę");
            dateValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if(nameValid && commentValid && dateValid && labelValid)
        {
            boolean status = Server.modelAction(App.login.getUsername(), App.login.getPassword(), model, modelAction);
            if (!status)
                Logging.logError("Saving Event failed\n");
            else
                CalendarManager.reloacCalendar();
            stage.close();
        }

        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished(pauseEvent ->
        {
            validationText.setText("");
            validationData.setText("");
            dateValidationText.setText("");
        });
        delay.play();
    }

    @FXML
    void keyPressed(KeyEvent event)
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            submit(null);
        }
    }

    /**
     * (button) wyjście z okna dodawania wydarzenia do kalendarza
     */
    @FXML
    void cancel(ActionEvent event)
    {
        stage.close();
    }

    private static Scene loadScene()
    {
        try
        {
            URL url = EventPopup.class.getClassLoader().getResource("fxml/EventPopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e)
        {
            Logging.logError("Popup EventPopup initialization failed\n" + e.toString());
        }
        return null;
    }

    /**
     * wyświetlanie popupu
     */
    private static void display()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(362);
        stage.setHeight(500);
        stage.setResizable(false);

        stage.setTitle("Wydarzenie - Purranya");
        stage.setScene(loadScene());

        stage.showAndWait();
        stage = null;
    }

    static void displayEdit(Event calendar)
    {
        modelAction = ModelAction.EDIT;
        model = calendar;
        display();
    }

    static void displayAdd()
    {
        modelAction = ModelAction.ADD;
        model = new Event();
        display();
    }
}