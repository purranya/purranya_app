package controllers;

import application.App;
import application.Logging;
import application.PrimaryStageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do wyświetlania okna potwierdzającego utworzenie konta */
public class CreateAccountConfirm {
    private static Stage createAccountConfirmStage;
    private static Scene createAccountConfirmScene = loadScene();

    @FXML
    void login(ActionEvent event) {
        /** TODO przycisk do usunięcia przy edytowaniu wyglądu! (ze względu na bycie popupem) */
        App.primaryStageManager.setScene("Login");
    }

    /** (button) zamknięcie okna */
    @FXML
    void ret(ActionEvent event) {
        createAccountConfirmStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddEvent.class.getClassLoader().getResource("fxml/CreateAccountConfirm.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddEvent initialization failed");
            Logging.Logger.logError("Popup AddEvent initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(createAccountConfirmStage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            createAccountConfirmStage = new Stage();
            createAccountConfirmStage.initModality(Modality.APPLICATION_MODAL);
            createAccountConfirmStage.setWidth(1000);
            createAccountConfirmStage.setHeight(1000);
            createAccountConfirmStage.setResizable(false);
            createAccountConfirmStage.setScene(createAccountConfirmScene);
        }
        createAccountConfirmStage.setTitle("");

        createAccountConfirmStage.showAndWait();
    }
}
