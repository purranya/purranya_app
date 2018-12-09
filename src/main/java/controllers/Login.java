package controllers;

import application.App;
import application.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Login {

    /** Obsługa przycisku "Powrót" */
    @FXML
    void retAction(ActionEvent event) {
        App.primaryStageManager.setScene("MainMenu");
    }

    /** Obsługa przycisku "Utwórz konto" */
    @FXML
    void createAccount(ActionEvent event) {
        App.primaryStageManager.setScene("CreateAccount");
    }

}
