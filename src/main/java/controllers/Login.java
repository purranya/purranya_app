package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;

import application.Main;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML private JFXButton login;
    @FXML private ImageView login1;
    @FXML private JFXButton continueWithoutLogin;
    @FXML private JFXButton forgotPassword;
    @FXML private JFXButton createAccount;
    @FXML private ImageView logo;

    /* przycisk "Zaloguj się" */
    @FXML void loginUser(ActionEvent event) {
        Parent loginUserParent = null;
        try {
            loginUserParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoginUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginUserScene = new Scene(loginUserParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginUserScene);
        window.show();
    }
}
