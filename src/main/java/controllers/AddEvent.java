package controllers;

import application.App;
import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AddEvent {
    static Stage popupStage;
    static Scene popupScene = loadScene();

    /** (button) dodanie wydarzenia do kalendarza */
    @FXML
    void addEvent(ActionEvent event) {
        //tutaj ma być ściąganie danych z okna i przesyłanie do bazy
    }

    /** (button) wyjście z okna dodawania wydarzenia do kalendarza */
    @FXML
    void cancelAddingEvent(ActionEvent event) {
       popupStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    static Scene loadScene() {
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
    public static void display() {
        if(popupStage==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setWidth(362);
            popupStage.setHeight(530);
            popupStage.setResizable(false);
            popupStage.setScene(popupScene);
        }
        popupStage.setTitle("Dodaj wydarzenie - Purranya");

        popupStage.showAndWait();
    }
}
