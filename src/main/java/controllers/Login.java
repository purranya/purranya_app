package controllers;

import application.App;
import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi okna logowania
 * TODO przebudować całą scenę (dostosować do okienka popup) */
public class Login {
    private static Stage loginStage;
    private static Scene loginScene = loadScene();

    /** (button) Obsługa przycisku "Powrót" */
    @FXML
    void cancel(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    /** Obsługa przycisku "Zaloguj"
     * TODO usunać przycisk! i dodać w mainmenu (ze względu na popup)
     * */
    @FXML
    void login(ActionEvent event) {
        loginStage.close();
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
        if(loginStage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            loginStage = new Stage();
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setWidth(336);
            loginStage.setHeight(235);
            loginStage.setResizable(false);
            loginStage.setScene(loginScene);
        }
        loginStage.setTitle("Zaloguj się - Purranya");

        loginStage.showAndWait();
    }
}
