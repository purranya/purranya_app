package controllers;

import api_client.Server;
import app.App;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** kontroler do obsługi okna logowania */
public class LoginPopup implements Initializable {
    @FXML private AnchorPane mainPane;
    @FXML private JFXTextField login;
    @FXML private JFXPasswordField password;

    private static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void login(ActionEvent event) {

        models.transfer_models.Login login = new models.transfer_models.Login();
        login.setUsername(this.login.getText());
        login.setPassword(this.password.getText());

        if(login.isValid())
        {
            boolean status = Server.logIn(login.getUsername(),login.getPassword());
            if(status)
            {
                App.isAuthorized = true;
                App.login = login;
                stage.close();
            }
        }
    }

    @FXML
    void keyPressed(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            login(null);
        }
    }

    /** załadowanie sceny do zmiennej - zwraca scenę jeśli się powiodło lub null, jeśli nie, zwraca nulla */
    private static Scene loadScene() {
        try {
            URL url = LoginPopup.class.getClassLoader().getResource("fxml/LoginPopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e) {
            Logging.logError("Popup LoginPopup initialization failed\n" + e.toString());
        }
        return null;
    }

    /** wyświetlanie popupu */
    static void display()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(336);
        stage.setHeight(235);
        stage.setResizable(false);

        stage.setTitle("Zaloguj się - Purranya");

        stage.setScene(loadScene());

        stage.showAndWait();
        stage = null;
    }
}
