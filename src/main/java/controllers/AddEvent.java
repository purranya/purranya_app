package controllers;

import application.Logging;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi dodawania wydarzeń w kalendarzu */
public class AddEvent {
    @FXML private JFXDatePicker dateOfStart;
    @FXML private JFXTextField title;
    @FXML private JFXComboBox<?> label;
    @FXML private TextArea description;
    @FXML private JFXDatePicker dateOfEnd;
    @FXML private Text validationText;

    private static Stage addEventStage;
    private static Scene addEventScene = loadScene();

    /** (button) dodanie wydarzenia do kalendarza */
    @FXML
    void addEvent(ActionEvent event) {
        //tutaj ma być ściąganie danych z okna i przesyłanie do bazy
    }

    /** (button) wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancelAddingEvent(ActionEvent event) {
       addEventStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddEvent.class.getClassLoader().getResource("fxml/AddEvent.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddEvent initialization failed");
            Logging.Logger.logError("Popup AddEvent initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(addEventStage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            addEventStage = new Stage();
            addEventStage.initModality(Modality.APPLICATION_MODAL);
            addEventStage.setWidth(362);
            addEventStage.setHeight(542);
            addEventStage.setResizable(false);
            addEventStage.setScene(addEventScene);
        }
        addEventStage.setTitle("Dodaj wydarzenie - Purranya");

        addEventStage.showAndWait();
    }
}
