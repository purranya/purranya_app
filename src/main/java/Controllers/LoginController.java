package main.java.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private ImageView login;

    @FXML
    private JFXButton continueWithoutLogin;

    @FXML
    private JFXButton forgotPassword;

    @FXML
    private JFXButton createAccount;

    @FXML
    public void initialize(URL url, ResourceBundle rs) {

    }

}
