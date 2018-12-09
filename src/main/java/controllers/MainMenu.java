package controllers;

import application.App;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import application.PrimaryStageManager;

public class MainMenu {
    @FXML private JFXButton login;
    @FXML private ImageView login1;
    @FXML private JFXButton continueWithoutLogin;
    @FXML private JFXButton forgotPassword;
    @FXML private JFXButton createAccount;
    @FXML private ImageView logo;
    private PrimaryStageManager scene;

    /**
     * Obsługa przycisku "Zaloguj się".
     */
    @FXML
    void login(ActionEvent event) {
        App.primaryStageManager.setScene("Login");
    }

    /**
     * Obsługa przycisku "Zapomniałeś hasła?".
     */
    @FXML
    void restorePassword(ActionEvent event) {
        App.primaryStageManager.setScene("RestorePassword");
    }

    /**
     * Obsługa przycisku "Utwórz konto".
     */
    @FXML
    void createAccount(ActionEvent event) {
        App.primaryStageManager.setScene("CreateAccount");
    }

    /**
     * Obsługa przycisku "Kontynuuj bez zalogowania".
     */
    @FXML
    void continueWithoutAccount(ActionEvent event) {
        App.primaryStageManager.setScene("Calendar");
    }
}
