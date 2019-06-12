package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.db_models.Label;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania etykiet */
public class LabelPopup implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML private JFXTextField title;
    @FXML private Text validationText;
    @FXML private Button action;

    private static Stage stage;
    private static ModelAction modelAction;
    private static Label model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        if(modelAction.equals(ModelAction.EDIT))
        {
            title.setText(model.getName());
        }
    }

    @FXML
    void submit(ActionEvent event)
    {
        CalendarManager calendarManager = new CalendarManager();

        model.setName(title.getText());

        model.setColor_b(100);
        model.setColor_g(100);
        model.setColor_r(100);

        model.setCalendar_id(calendarManager.getCalendar().getId());

        boolean nameValid = model.isNameValid();
        boolean colorValid = model.isColorValid();

        validationText.setText("");

        if(!nameValid)
        {
            validationText.setText("Nazwa jest niepoprawna");
            validationText.setFill(Color.rgb(254, 203, 200));
        }
        if(!colorValid)
        {
            //tutaj kolor
        }
        if(nameValid && colorValid)
        {
            boolean status = Server.modelAction(App.login.getUsername(),App.login.getPassword(),model,modelAction);
            if(!status)
                Logging.logError("Saving Label failed\n");
            else
                CalendarManager.reloacCalendar();
            stage.close();
        }
    }


    /** (button) wyjście z okna dodawania etykiety */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    /** załadowanie sceny */
    private static Scene loadScene() {
        try {
            URL url = LabelPopup.class.getClassLoader().getResource("fxml/LabelPopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e) {
            Logging.logError("Popup LabelPopup initialization failed\n" + e.toString());
        }
        return null;
    }

    /** wyświetlanie popupu */
    private static void display() {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(200);
            stage.setResizable(false);
            stage.setTitle("Etykieta");
        stage.setScene(loadScene());
        stage.showAndWait();
    }

    static void displayEdit(Label label) {
        modelAction = ModelAction.EDIT;
        model = label;
        display();

    }

    static void displayAdd() {
        modelAction = ModelAction.ADD;
        model = new Label();
        display();
    }
}