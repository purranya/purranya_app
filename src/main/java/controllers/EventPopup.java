package controllers;

import application.App;
import application.Logging;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Label;
import models.Note;
import org.joda.time.DateTime;
import data_util.DateUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania wydarzeń w kalendarzu */
public class EventPopup implements Initializable {
    @FXML private JFXDatePicker dateOfStart;
    @FXML private JFXTextField title;
    @FXML private JFXComboBox<javafx.scene.control.Label> label;
    @FXML private TextArea description;
    @FXML private JFXDatePicker dateOfEnd;
    @FXML private Text validationText;
    @FXML private Text validationData;
    @FXML private Button action;

    private static Stage stage;

    private static boolean editscene;
    private static int noteId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Label l : App.calendarManager.getAllLabels())
            this.label.getItems().add(new javafx.scene.control.Label(l.text));
        if(!editscene)
        {
            action.setText("Dodaj");
            action.setOnAction(e->{
                Note n = new Note(title.getText(),description.getText(),false,label.getValue()!=null?App.calendarManager.getLabelByText(label.getValue().getText()):App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue()!=null?(DateUtils.toDateTime(dateOfEnd.getValue())):null);
                if(n.startDate.isEqual(n.endDate))
                    n.endDate=null;
                if(n.isValid())
                {
                    App.calendarManager.addNote(n);
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else
                {
                    HashMap<String,String> errors = n.getValidationErrors();
                    if(errors.get("text")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                    if(errors.get("enddate")!=null)
                    {
                        validationData.setFill(Color.rgb(254, 203, 200));
                        validationData.setText(errors.get("enddate"));
                    }
                }
            });
        }
        else
        {
            Note n = App.calendarManager.getNoteById(noteId);
            title.setText(n.title);
            description.setText(n.content);
            dateOfStart.setValue(DateUtils.toLocalDate(n.startDate));
            if(n.endDate!=null)
                dateOfEnd.setValue(DateUtils.toLocalDate(n.endDate));
            ObservableList<javafx.scene.control.Label>list = label.getItems();
            javafx.scene.control.Label currentLabel=null;
            for(javafx.scene.control.Label labelFromList : list)
                if(labelFromList.getText().equals(n.label.text))
                    currentLabel = labelFromList;
            label.setValue(currentLabel);
            action.setText("Edytuj");
            action.setOnAction(e->{
                Note note = new Note(title.getText(),description.getText(),false,label.getValue()!=null?App.calendarManager.getLabelByText(label.getValue().getText()):App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue()!=null?(DateUtils.toDateTime(dateOfEnd.getValue())):null);
                if(note.isValid())
                {
                    validationText.setFill(Color.rgb(185, 230, 223));
                    n.set(title.getText(),description.getText(),false,label.getValue()!=null?App.calendarManager.getLabelByText(label.getValue().getText()):App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue()!=null?(DateUtils.toDateTime(dateOfEnd.getValue())):null);
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else
                {
                    HashMap<String,String> errors = note.getValidationErrors();
                    if(errors.get("text")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                    if(errors.get("enddate")!=null)
                    {
                        validationData.setFill(Color.rgb(254, 203, 200));
                        validationData.setText(errors.get("enddate"));
                    }
                }
            });
        }
    }

    /** (button) dodanie wydarzenia do kalendarza */
    @FXML
    void add(ActionEvent event) {
        Note n = new Note(title.getText(),description.getText(),false,label.getValue()!=null?App.calendarManager.getLabelByText(label.getValue().getText()):App.calendarManager.getLabelByText(""), DateUtils.toDateTime(dateOfStart.getValue()), dateOfEnd.getValue()!=null?(DateUtils.toDateTime(dateOfEnd.getValue())):null);
        if(n.startDate.isEqual(n.endDate))
            n.endDate=null;
        if(n.isValid())
        {
            App.calendarManager.addNote(n);
            App.calendarManager.saveCalendar();
            stage.close();
        }
        else
        {
            HashMap<String,String> errors = n.getValidationErrors();
            if(errors.get("text")!=null) {
                validationText.setFill(Color.rgb(254, 203, 200));
                validationText.setText(errors.get("text"));
            }
            if(errors.get("enddate")!=null)
            {
                validationData.setFill(Color.rgb(254, 203, 200));
                validationData.setText(errors.get("enddate"));
            }
        }
    }

    /** (button) wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancel(ActionEvent event) {
       stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(EventPopup.class.getClassLoader().getResource("fxml/EventPopup.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup EventPopup initialization failed");
            Logging.Logger.logError("Popup EventPopup initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    private static void display() {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(543);
            stage.setResizable(false);
            stage.setTitle("Wydarzenie");
        }

        stage.setScene(loadScene());
        stage.showAndWait();
    }

    static void displayEdit(int id) {
        editscene = true;
        noteId = id;
        display();
    }

    static void displayAdd() {
        editscene = false;
        display();
    }
}
