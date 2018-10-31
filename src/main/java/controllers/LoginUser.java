package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginUser {
    @FXML private ImageView logo;
    @FXML private JFXButton ret;
    @FXML private JFXButton forgotPassword;
    @FXML private JFXButton createAccount;

    /* przycisk "Powrót" */
    @FXML void retAction(ActionEvent event) {
        Parent loginParent = null;
        try {
            loginParent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginUserScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginUserScene);
        window.show();
    }

}
