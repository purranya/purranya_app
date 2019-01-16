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

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) dodanie wydarzenia do kalendarza
     * TODO ściąganie danych z okna i przesyłanie do bazy*/
    @FXML
    void add(ActionEvent event) {

    }

    /** (button) wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancel(ActionEvent event) {
       stage.close();
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
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(542);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Dodaj wydarzenie - Purranya");

        stage.showAndWait();
    }
}
