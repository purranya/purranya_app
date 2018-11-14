package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import application.SceneOptions;

public class Login {
    @FXML private JFXButton login;
    @FXML private ImageView login1;
    @FXML private JFXButton continueWithoutLogin;
    @FXML private JFXButton forgotPassword;
    @FXML private JFXButton createAccount;
    @FXML private ImageView logo;
    private SceneOptions scene;

    /**
     * Obsługa przycisku "Zaloguj się".
     */
    @FXML
    void loginUser(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("LoginUser");
    }

    /**
     * Obsługa przycisku "Zapomniałeś hasła?".
     */
    @FXML
    void restorePassword(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("RestorePassword");
    }

    /**
     * Obsługa przycisku "Utwórz konto".
     */
    @FXML
    void createAccount(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("CreateAccount");
    }

    /**
     * Obsługa przycisku "Kontynuuj bez zalogowania".
     */
    @FXML
    void continueWithoutAccount(ActionEvent event) {
        scene = new SceneOptions(event);
        scene.change("Calendar");
    }
}
