package controllers;

import application.App;
import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do wyświetlania okna potwierdzającego utworzenie konta */
public class RegisterConfirm {
    private static Stage createAccountConfirmStage;
    private static Scene createAccountConfirmScene = loadScene();

    /** (button) zamknięcie okna */
    @FXML
    void ret(ActionEvent event) {
        createAccountConfirmStage.close();
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
        if(createAccountConfirmStage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            createAccountConfirmStage = new Stage();
            createAccountConfirmStage.initModality(Modality.APPLICATION_MODAL);
            createAccountConfirmStage.setWidth(244);
            createAccountConfirmStage.setHeight(136);
            createAccountConfirmStage.setResizable(false);
            createAccountConfirmStage.setScene(createAccountConfirmScene);
        }
        createAccountConfirmStage.setTitle("");

        createAccountConfirmStage.showAndWait();
    }
}
