package controllers;

import app.App;
import app.Logging;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/** kontroler do obsługi okna logowania */
public class Login implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private JFXTextField login;
    @FXML private JFXPasswordField password;

    private static Stage stage;
    private static Scene scene = loadScene();

    /** (button) Obsługa przycisku "Powrót" */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** (button) Obsługa przycisku "Zaloguj"
     * TODO dodać obsługę
     * */
    @FXML
    void login(ActionEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");
    }
}
