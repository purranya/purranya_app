package controllers;

import api_client.ModelAction;
import api_client.Server;
import app.App;
import app.GlobalOptions;
import app.Logging;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.db_models.Note;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania notatek w StickyNotes */
public class NotePopup implements Initializable {

    @FXML private Text titleValidationText;
    @FXML private Text descriptionValidationText;
    @FXML private AnchorPane mainPane;
    @FXML private JFXTextField title;
    @FXML private JFXComboBox<?> label;
    @FXML private TextArea description;

    private static Stage stage;
    private static ModelAction modelAction;
    private static Note model;

    /** (button) za mknięcie okna NotePopup */
    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) {
        modelAction = ModelAction.DELETE;
        Server.modelAction(App.login.getUsername(),App.login.getPassword(),model,modelAction);
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().add(getClass().getResource("/css/") + (new GlobalOptions()).get("stylesheet") + ".css");

        if(modelAction.equals(ModelAction.EDIT))
        {
            title.setText(model.getName());
            description.setText(model.getComment());
        }
    }

    @FXML
    void submit(ActionEvent event)
    {
        model.setName(title.getText());
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
        if( nameValid && commentValid )
        {
            boolean status = Server.modelAction(App.login.getUsername(),App.login.getPassword(),model,modelAction);
            if(!status)
                Logging.logError("Saving Note failed\n");
            stage.close();
        }
    }

    @FXML
    void keyPressed(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            submit(null);
        }
    }

    private static Scene loadScene() {
        try {
            URL url = NotePopup.class.getClassLoader().getResource("fxml/NotePopup.fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            return new Scene(FXMLLoader.load(url));
        } catch (IOException e) {
            Logging.logError("Popup NotePopup initialization failed\n" + e.toString());
        }
        return null;
    }

    private static void display()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(362);
        stage.setHeight(540);
        stage.setResizable(false);

        stage.setTitle("Notatka - Purranya");
        stage.setScene(loadScene());

        stage.showAndWait();
        stage = null;
    }

    static void displayEdit(Note calendar) {
        modelAction = ModelAction.EDIT;
        model = calendar;
        display();
    }

    static void displayAdd() {
        modelAction = ModelAction.ADD;
        model = new Note();
        display();
    }


}