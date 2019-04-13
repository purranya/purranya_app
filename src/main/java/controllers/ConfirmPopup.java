package controllers;

import app.App;
import app.Logging;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmPopup implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private Text popupText;
    @FXML private JFXButton yes;
    @FXML private JFXButton no;

    private static Stage stage;
    private static boolean answer;
    private static String message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + App.globalOptions.getUserOptions("stylesheet") + ".css");

        popupText.setText(message);
    }

    @FXML
    void cancel(ActionEvent event) {
        answer = false;
        stage.close();
    }

    @FXML
    void confirm(ActionEvent event) {
        answer = true;
        stage.close();
    }

    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(CalendarPopup.class.getClassLoader().getResource("fxml/ConfirmPopup.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup CalendarPopup initialization failed");
            Logging.Logger.logError("Popup CalendarPopup initialization failed");
        }
        return null;
    }

    public static boolean display(String mes) {
        if(stage==null) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(214);
            stage.setResizable(false);
            stage.setTitle("Potwierdź");

        }
        message = mes;
        answer = false;
        stage.setScene(loadScene());
        stage.showAndWait();
        return answer;
    }

}
