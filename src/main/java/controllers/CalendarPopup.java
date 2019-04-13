package controllers;

import app.App;
import app.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


/** kontroler do obsługi dodawania kalendarzy */
public class CalendarPopup implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML private JFXTextField name;
    @FXML private Text validationText;
    @FXML private TextArea description;
    @FXML private Button action;

    private static Stage stage;
    private static boolean editscene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");

        if(!editscene)
        {
            action.setText("Dodaj");
            action.setOnAction(e->{
                models.Calendar c = new models.Calendar(name.getText(),description.getText());
                if(c.isValid()) {
                    validationText.setFill(Color.rgb(185, 230, 223));
                    App.calendarManager.saveCalendar(c);
                    stage.close();
                }
                else {
                    HashMap<String, String> errors = c.getValidationErrors();
                    if(errors.get("name")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("name"));
                    }
                }
            });
        }
        else
        {
            name.setText(App.calendarManager.getCalendarName());
            description.setText(App.calendarManager.getCalendarComment());
            action.setText("Edytuj");
            action.setOnAction(e->{
                models.Calendar c = new models.Calendar(name.getText(),description.getText());
                boolean extraValid = true;
                for(String name : App.calendarManager.getCalendarIndex())
                    if(name.equalsIgnoreCase(c.name))
                        extraValid=false;
                boolean nameEdited = !App.calendarManager.getCalendarName().equals(c.name);
                if(!nameEdited)
                {
                    App.calendarManager.getCalendar().comment = c.comment;
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else if(c.isValid() && extraValid) {
                    App.calendarManager.renameCalendar(App.calendarManager.getCalendarName(),c.name);
                    App.calendarManager.loadCalendar(c.name);
                    App.calendarManager.getCalendar().name = c.name;
                    App.calendarManager.getCalendar().comment = c.comment;
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else {
                    HashMap<String, String> errors = c.getValidationErrors();
                    if(errors.get("name")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("name"));
                    }
                    else{
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("Błąd walidacji"));
                    }
                }
            });
        }
    }

    /** (button) wyjście z okna dodawania kalendarza */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(CalendarPopup.class.getClassLoader().getResource("fxml/CalendarPopup.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup CalendarPopup initialization failed");
            Logging.Logger.logError("Popup CalendarPopup initialization failed");
        }
        return null;
    }

    /** metoda obsługująca wyświetlanie okna */
    private static void display() {
        if(stage == null) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(305);
            stage.setResizable(false);
        }
        stage.setTitle("Kalendarz");
        stage.setScene(loadScene());

        stage.showAndWait();
    }

    static void displayEdit() {
        editscene = true;
        display();
    }

    static void displayAdd() {
        editscene = false;
        display();
    }

}

