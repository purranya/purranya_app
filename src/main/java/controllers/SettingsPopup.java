package controllers;

import app.Logging;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsPopup {
    private static Stage stage;

    @FXML
    private JFXComboBox<?> layout;

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void confirm(ActionEvent event) {

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

}
