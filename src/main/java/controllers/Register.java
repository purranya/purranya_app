package controllers;

import application.Logging;
import application.PrimaryStageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** kontroler do obsługi tworzenia konta */
public class Register {
    private static Stage createAccountStage;
    private static Scene createAccountScene = loadScene();

    @FXML private ImageView logo;
    @FXML private JFXButton ret;
    @FXML private JFXTextField login;
    @FXML private JFXTextField email;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField repeatedPassword;
    @FXML private Text loginText;
    @FXML private Text emailText;
    @FXML private Text passwordText;
    @FXML private Text repeatedPasswordText;

    private PrimaryStageManager scene;
    private boolean loginIsOK = false;
    private boolean emailIsOK = false;
    private boolean passwordIsOK = false;
    private boolean repeatedPasswordIsOK = false;

    /** (button) zamknięcie okna */
    @FXML void retAction(ActionEvent event) {
        createAccountStage.close();
    }

    /** (button) utworzenie konta */
    @FXML void create(ActionEvent event) {
        /* walidacja */
        if (login.getText().matches("^.{2,15}?$")) {
            loginIsOK = true;
            loginText.setFill(Color.GREEN);
        }
        else {
            loginIsOK = false;
            loginText.setText("Login musi posiadać od 3 do 15 znaków.");
            loginText.setFill(Color.RED);
        }
        if (email.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{1,6})$")) {
            emailIsOK = true;
            emailText.setFill(Color.GREEN);
        }
        else {
            emailIsOK = false;
            emailText.setText("Wpisz poprawny e-mail, np. jan@kowalski.pl.");
            emailText.setFill(Color.RED);
        }
        if (password.getText().matches("^.{8,30}?$")) {
            passwordIsOK = true;
            passwordText.setFill(Color.GREEN);
        }
        else {
            passwordIsOK = false;
            passwordText.setText("Hasło musi posiadać 8-30 znaków.");
            passwordText.setFill(Color.RED);
        }
        if (repeatedPassword.getText().equals(password.getText()) && !repeatedPassword.getText().equals("")) {
            repeatedPasswordIsOK = true;
            repeatedPasswordText.setFill(Color.GREEN);
        }
        else {
            repeatedPasswordIsOK = false;
            repeatedPasswordText.setText("Hasła muszą być identyczne.");
            repeatedPasswordText.setFill(Color.RED);
        }
        if(loginIsOK && emailIsOK && passwordIsOK && repeatedPasswordIsOK) {
            /** TODO wprowadzić obsługę tworzenia konta w bazie danych */
            RegisterConfirm.display();
            createAccountStage.close();
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
        if(createAccountStage ==null) { //zapobieganie wyświetlania okna więcej niż 1 raz
            createAccountStage = new Stage();
            createAccountStage.initModality(Modality.APPLICATION_MODAL);
            createAccountStage.setWidth(328);
            createAccountStage.setHeight(462);
            createAccountStage.setResizable(false);
            createAccountStage.setScene(createAccountScene);
        }
        createAccountStage.setTitle("Utwórz konto - Purranya");

        createAccountStage.showAndWait();
    }
}
