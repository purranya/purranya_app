package controllers;

import app.App;
import app.Logging;
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

/** kontroler do wyświetlania okna potwierdzającego utworzenie konta */
public class RegisterConfirm implements Initializable {
    private static Stage stage;
    private static Scene scene = loadScene();

    @FXML
    private AnchorPane mainPane;
    /** (button) zamknięcie okna */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(RegisterConfirm.class.getClassLoader().getResource("fxml/RegisterConfirm.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup RegisterConfirm initialization failed");
            Logging.Logger.logError("Popup RegisterConfirm initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(244);
            stage.setHeight(136);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("");

        stage.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");
    }
}
