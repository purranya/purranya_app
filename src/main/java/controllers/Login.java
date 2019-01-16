package controllers;

import application.App;
import application.Logging;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi okna logowania
 * TODO przebudować całą scenę (dostosować do okienka popup) */
public class Login {
    @FXML private JFXTextField login;
    @FXML private JFXPasswordField password;

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) Obsługa przycisku "Powrót" */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    /** (button) Obsługa przycisku "Zaloguj"
     * TODO usunać przycisk! i dodać w mainmenu (ze względu na popup)
     * */
    @FXML
    void login(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(Login.class.getClassLoader().getResource("fxml/Login.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup Login initialization failed");
            Logging.Logger.logError("Popup Login initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(336);
            stage.setHeight(235);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Zaloguj się - Purranya");

        stage.showAndWait();
    }
}
