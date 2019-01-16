package controllers;

import application.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi dodawania kalendarzy */
public class AddCalendar {
    @FXML private JFXTextField name;
    @FXML private Text validationText;
    @FXML private TextArea calendarDescription;

    private static Stage addCalendarStage;
    private static Scene addCalendarScene = loadScene();

    /** (button) dodanie kalendarza
     * TODO sciaganie danych z okna
     * TODO wprowadzenie walidacji nazwy */
    @FXML
    void add(ActionEvent event) {
    }

    /** (button) wyjście z okna dodawania kalendarza */
    @FXML
    void cancel(ActionEvent event) {
        addCalendarStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddCalendar.class.getClassLoader().getResource("fxml/AddCalendar.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddCalendar initialization failed");
            Logging.Logger.logError("Popup AddCalendar initialization failed");
        }
        return null;
    }

    /** metoda obsługująca wyświetlanie okna */
    static void display() {
        if(addCalendarStage == null) {
            addCalendarStage = new Stage();
            addCalendarStage.initModality(Modality.APPLICATION_MODAL);
            addCalendarStage.setWidth(362);
            addCalendarStage.setHeight(305);
            addCalendarStage.setResizable(false);
            addCalendarStage.setScene(addCalendarScene);
        }
        addCalendarStage.setTitle("Dodaj kalendarz - Purranya");

        addCalendarStage.showAndWait();
    }

}

