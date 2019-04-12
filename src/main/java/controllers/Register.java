package controllers;

import app.Logging;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi tworzenia konta */
public class Register {
    private static Stage stage;
    private static Scene scene = loadScene();

    @FXML private JFXTextField login;
    @FXML private JFXTextField email;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField repeatedPassword;
    @FXML private Text loginValidationText;
    @FXML private Text emailValidationText;
    @FXML private Text passwordValidationText;
    @FXML private Text repeatedPasswordValidationText;

    private boolean loginIsOK = false;
    private boolean emailIsOK = false;
    private boolean passwordIsOK = false;
    private boolean repeatedPasswordIsOK = false;

    /** (button) zamknięcie okna */
    @FXML void cancel(ActionEvent event) {
        stage.close();
    }

    /** (button) utworzenie konta */
    @FXML void create(ActionEvent event) {
        /* walidacja */
        if (login.getText().matches("^.{2,15}?$")) {
            loginIsOK = true;
            loginValidationText.setFill(Color.rgb(185, 230, 223));
        }
        else {
            loginIsOK = false;
            loginValidationText.setText("Login musi posiadać od 3 do 15 znaków.");
            loginValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if (email.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{1,6})$")) {
            emailIsOK = true;
            emailValidationText.setFill(Color.rgb(185, 230, 223));
        }
        else {
            emailIsOK = false;
            emailValidationText.setText("Wpisz poprawny e-mail, np. jan@kowalski.pl.");
            emailValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if (password.getText().matches("^.{8,30}?$")) {
            passwordIsOK = true;
            passwordValidationText.setFill(Color.rgb(185, 230, 223));
        }
        else {
            passwordIsOK = false;
            passwordValidationText.setText("Hasło musi posiadać 8-30 znaków.");
            passwordValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if (repeatedPassword.getText().equals(password.getText()) && !repeatedPassword.getText().equals("")) {
            repeatedPasswordIsOK = true;
            repeatedPasswordValidationText.setFill(Color.rgb(185, 230, 223));
        }
        else {
            repeatedPasswordIsOK = false;
            repeatedPasswordValidationText.setText("Hasła muszą być identyczne.");
            repeatedPasswordValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if(loginIsOK && emailIsOK && passwordIsOK && repeatedPasswordIsOK) {
            /** TODO wprowadzić obsługę tworzenia konta w bazie danych */
            RegisterConfirm.display();
            stage.close();
        }
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            return new Scene(FXMLLoader.load(Register.class.getClassLoader().getResource("fxml/Register.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup Register initialization failed");
            Logging.Logger.logError("Popup Register initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display() {
        if(stage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(328);
            stage.setHeight(462);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        stage.setTitle("Utwórz konto - Purranya");

        stage.showAndWait();
    }
}
