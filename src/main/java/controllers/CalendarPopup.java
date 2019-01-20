package controllers;

import application.App;
import application.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;


/** kontroler do obsługi dodawania kalendarzy */
public class CalendarPopup {
    @FXML private JFXTextField name;
    @FXML private Text validationText;
    @FXML private TextArea description;

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) dodanie kalendarza
     * TODO sciaganie danych z okna
     * TODO wprowadzenie walidacji nazwy */
    @FXML
    void add(ActionEvent event) {
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
    static void display() {
        if(stage == null) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(305);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Dodaj kalendarz - Purranya");

        stage.showAndWait();
    }

}

