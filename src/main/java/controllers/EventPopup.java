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
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
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
import utils.DateUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania wydarzeń w kalendarzu */
public class EventPopup implements Initializable
{
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXDatePicker dateOfStart;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox<javafx.scene.control.Label> label;
    @FXML
    private TextArea description;
    @FXML
    private JFXDatePicker dateOfEnd;
    @FXML
    private Text validationText;
    @FXML
    private Text validationData;
    @FXML
    private Button action;

    private static Stage stage;

    private static Event model;
    private static ModelAction modelAction;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        for (Label l : App.calendarManager.getAllLabels())
            this.label.getItems().add(new javafx.scene.control.Label(l.text));
        if (!editscene)
        {
            action.setText("Dodaj");
            action.setOnAction(e ->
            {
                Note n = new Note(title.getText(), description.getText(), false, label.getValue() != null ? App.calendarManager.getLabelByText(label.getValue().getText()) : App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue() != null ? (DateUtils.toDateTime(dateOfEnd.getValue())) : null);
                if (n.startDate.isEqual(n.endDate))
                    n.endDate = null;
                if (n.isValid())
                {
                    App.calendarManager.addNote(n);
                    App.calendarManager.saveCalendar();
                    stage.close();
                } else
                {
                    HashMap<String, String> errors = n.getValidationErrors();
                    if (errors.get("text") != null)
                    {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                    if (errors.get("enddate") != null)
                    {
                        validationData.setFill(Color.rgb(254, 203, 200));
                        validationData.setText(errors.get("enddate"));
                    }
                }
            });
        } else
        {
            Note n = App.calendarManager.getNoteById(noteId);
            title.setText(n.title);
            description.setText(n.content);
            dateOfStart.setValue(DateUtils.toLocalDate(n.startDate));
            if (n.endDate != null)
                dateOfEnd.setValue(DateUtils.toLocalDate(n.endDate));
            ObservableList<javafx.scene.control.Label> list = label.getItems();
            javafx.scene.control.Label currentLabel = null;
            for (javafx.scene.control.Label labelFromList : list)
                if (labelFromList.getText().equals(n.label.text))
                    currentLabel = labelFromList;
            label.setValue(currentLabel);
            action.setText("Edytuj");
            action.setOnAction(e ->
            {
                Note note = new Note(title.getText(), description.getText(), false, label.getValue() != null ? App.calendarManager.getLabelByText(label.getValue().getText()) : App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue() != null ? (DateUtils.toDateTime(dateOfEnd.getValue())) : null);
                if (note.isValid())
                {
                    validationText.setFill(Color.rgb(185, 230, 223));
                    n.set(title.getText(), description.getText(), false, label.getValue() != null ? App.calendarManager.getLabelByText(label.getValue().getText()) : App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue() != null ? (DateUtils.toDateTime(dateOfEnd.getValue())) : null);
                    App.calendarManager.saveCalendar();
                    stage.close();
                } else
                {
                    HashMap<String, String> errors = note.getValidationErrors();
                    if (errors.get("text") != null)
                    {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                    if (errors.get("enddate") != null)
                    {
                        validationData.setFill(Color.rgb(254, 203, 200));
                        validationData.setText(errors.get("enddate"));
                    }
                }
            });
        }
    }

    /**
     * (button) dodanie wydarzenia do kalendarza
     */
    @FXML
    void add(ActionEvent event)
    {
        Note n = new Note(title.getText(), description.getText(), false, label.getValue() != null ? App.calendarManager.getLabelByText(label.getValue().getText()) : App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue() != null ? (DateUtils.toDateTime(dateOfEnd.getValue())) : null);
        if (n.startDate.isEqual(n.endDate))
            n.endDate = null;
        if (n.isValid())
        {
            App.calendarManager.addNote(n);
            App.calendarManager.saveCalendar();
            stage.close();
        } else
        {
            HashMap<String, String> errors = n.getValidationErrors();
            if (errors.get("text") != null)
            {
                validationText.setFill(Color.rgb(254, 203, 200));
                validationText.setText(errors.get("text"));
            }
            if (errors.get("enddate") != null)
            {
                validationData.setFill(Color.rgb(254, 203, 200));
                validationData.setText(errors.get("enddate"));
            }
        }
    }

    @FXML
    void submit(ActionEvent event)
    {
        CalendarManager calendarManager = new CalendarManager();

        model.setName(title.getText());
        model.setComment(description.getText());

        model.setStartDate(DateUtils.toDateTime(dateOfStart.getValue()));
        model.setEndDate(DateUtils.toDateTime(dateOfEnd.getValue()));

        long id = calendarManager.getLa

        model.

        validationText.setText("");
        validationData.setText("");


        boolean nameValid = model.isNameValid();
        boolean commentValid = model.isCommentValid();

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
        if (nameValid && commentValid)
        {
            boolean status = Server.modelAction(App.login.getUsername(), App.login.getPassword(), model, modelAction);
            if (!status)
                Logging.logError("Saving Calendar failed\n");
            stage.close();
        }

        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished(pauseEvent ->
        {
            titleValidationText.setText("");
            descriptionValidationText.setText("");
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
        stage.setHeight(305);
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