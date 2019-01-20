package controllers;

import application.App;
import application.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Label;

import java.util.HashMap;

/** kontroler do obsługi dodawania etykiet */
public class LabelPopup {
    @FXML private JFXTextField title;
    @FXML private Text validationText;

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) dodanie etykiety
     * TODO ściąganie danych z okna i przesyłanie do bazy*/
    @FXML
    void add(ActionEvent event) {
        models.Label l = new Label(title.getText());
        if(l.isValid())
        {
            validationText.setFill(Color.rgb(185, 230, 223));
            App.calendarManager.addLabel(l);
            App.calendarManager.saveCalendar();
            stage.close();
        }
        else
        {
            HashMap<String,String> errors = l.getValidationErrors();
            if(errors.get("text")!=null) {
                validationText.setFill(Color.rgb(254, 203, 200));
                validationText.setText(errors.get("text"));
            }
        }
    }

    /** (button) wyjście z okna dodawania etykiety */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(LabelPopup.class.getClassLoader().getResource("fxml/LabelPopup.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup EventPopup initialization failed");
            Logging.Logger.logError("Popup EventPopup initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(200);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Dodaj etykietę - Purranya");

        stage.showAndWait();
    }
}
