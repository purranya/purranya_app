package controllers;

import application.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi dodawania kalendarzy */
public class AddCalendar {
    private static Stage addCalendarStage;
    private static Scene addCalendarScene = loadScene();

    /** (button) dodanie kalendarza */
    @FXML
    void addCalendar(ActionEvent event) {
        //sciaganie danych z okna
    }

    /** (button) wyjście z okna dodawania kalendarza */
    @FXML
    void cancelAddingCalendar(ActionEvent event) {
        addCalendarStage.close();
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(AddCalendar.class.getClassLoader().getResource("fxml/AddCalendar.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup AddCalendar initialization failed");
            Logging.Logger.logError("Popup AddCalendar initialization failed");
        }
        return null;
    }

    /** metoda obsługująca wyświetlanie okna */
    static void display() {
        if(addCalendarStage == null) {
            addCalendarStage = new Stage();
            addCalendarStage.initModality(Modality.APPLICATION_MODAL);
            addCalendarStage.setWidth(362);
            addCalendarStage.setHeight(305);
            addCalendarStage.setResizable(false);
            addCalendarStage.setScene(addCalendarScene);
        }
        addCalendarStage.setTitle("Dodaj kalendarz - Purranya");

        addCalendarStage.showAndWait();
    }

}

