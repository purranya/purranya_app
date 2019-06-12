package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.CalendarManager;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.db_models.Calendar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalendarPopup implements Initializable {
    public Text titleValidationText;
    public Text descriptionValidationText;
    @FXML
    private AnchorPane mainPane;

    @FXML private JFXTextField name;
    @FXML private TextArea description;
    @FXML private Button action;

    private static Stage stage;
    private static ModelAction modelAction;
    private static Calendar model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        if(modelAction.equals(ModelAction.EDIT))
        {
            name.setText(model.getName());
            description.setText(model.getComment());
        }
    }

    /**
     * @TODO dodać do styli kolor niepoprawnej walidacji
     */
    @FXML
    void submit(ActionEvent event)
    {
        model.setName(name.getText());
        model.setComment(description.getText());

        titleValidationText.setText("");
        descriptionValidationText.setText("");

        boolean nameValid = model.isNameValid();
        boolean commentValid = model.isCommentValid();

        if(!nameValid)
        {
            titleValidationText.setText("Nazwa jest niepoprawna");
            titleValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if(!commentValid)
        {
            descriptionValidationText.setText("Opis jest niepoprawny");
            descriptionValidationText.setFill(Color.rgb(254, 203, 200));
        }
        if(nameValid && commentValid)
        {
            boolean status = Server.modelAction(App.login.getUsername(),App.login.getPassword(),model,modelAction);
            if(!status)
                Logging.logError("Saving Calendar failed\n");
            else
                CalendarManager.reloacCalendar();
            stage.close();
        }

        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished(pauseEvent ->
        {
            titleValidationText.setText("");
            descriptionValidationText.setText("");
        });
        delay.play();
    }

    @FXML
    void keyPressed(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            submit(null);
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    private static Scene loadScene() {
        try {
            URL url = CalendarPopup.class.getClassLoader().getResource("fxml/CalendarPopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e) {
            Logging.logError("Popup CalendarPopup initialization failed\n" + e.toString());
        }
        return null;
    }

    private static void display()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(362);
        stage.setHeight(305);
        stage.setResizable(false);

        stage.setTitle("Kalendarz - Purranya");
        stage.setScene(loadScene());

        stage.showAndWait();
        stage = null;
    }

    static void displayEdit(Calendar calendar) {
        modelAction = ModelAction.EDIT;
        model = calendar;
        display();

    }

    static void displayAdd() {
        modelAction = ModelAction.ADD;
        model = new Calendar();
        display();
    }

}
