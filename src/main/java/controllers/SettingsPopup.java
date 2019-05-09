package controllers;

import app.App;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class SettingsPopup implements Initializable {
    private static Stage stage;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXComboBox<javafx.scene.control.Label> layout;
    @FXML
    private JFXButton apply;

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void confirm(ActionEvent event) {
        System.out.println(layout.getValue().getText());
        GlobalOptions.userOptions.replace("stylesheet", layout.getValue().getText());
        stage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(EventPopup.class.getClassLoader().getResource("fxml/SettingsPopup.fxml")));
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
            stage.setHeight(543);
            stage.setResizable(false);
            stage.setTitle("Ustawienia");
        }

        stage.setScene(loadScene());
        stage.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");

        String[] themes = {"blue", "chill", "dark", "pastel", "red"};

        for(String s : themes)
            this.layout.getItems().add(new javafx.scene.control.Label(s));
    }
}
