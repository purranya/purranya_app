package controllers;

import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPopup implements Initializable {
    private static Stage stage;
    private static String tempTheme;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXComboBox<javafx.scene.control.Label> layout;
    @FXML
    private JFXButton apply;

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void confirm(ActionEvent event) {
        System.out.println(layout.getValue().getText());
        (new GlobalOptions()).set("stylesheet", layout.getValue().getText());
        stage.close();
    }

    private static Scene loadScene() {
        try {
            URL url = SettingsPopup.class.getClassLoader().getResource("fxml/SettingsPopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e) {
            Logging.logError("Popup SettingsPopup initialization failed\n" + e.toString());
        }
        return null;
    }

    static void display()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(362);
        stage.setHeight(543);
        stage.setResizable(false);
        stage.setTitle("Ustawienia - Purranya");

        stage.setScene(loadScene());
        stage.showAndWait();
        stage = null;
        tempTheme = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String theme;
        if(tempTheme==null)
            theme = (new GlobalOptions()).get("stylesheet");
        else
            theme = tempTheme;
        
        mainPane.getStylesheets().add(getClass().getResource("/css/") + theme + ".css");

        String[] themes = {"blue", "chill", "dark", "pastel", "red"};

        Label[] labels = new Label[themes.length];

        for(int i=0;i<themes.length;i++)
            labels[i] = new Label(themes[i]);

        for(Label l : labels)
            this.layout.getItems().add(l);

        for(Label l : labels)
            if(l.getText().equals(theme))
                layout.setValue(l);

        layout.setOnHidden(event -> {
            tempTheme = layout.getValue().getText();
            stage.setScene(loadScene());
        });
    }
}