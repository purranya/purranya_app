package main.java.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton ok;

    @FXML
    private JFXButton continueWithoutLogin;

    @FXML
    void makeLogin(ActionEvent event) {
        String e_mail = email.getText();
        String pass = password.getText();
        if(e_mail.equals("test"))
            System.out.println("dziala");
    }

    @FXML public void initialize(URL url, ResourceBundle rb) {
        
    }

}
