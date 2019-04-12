package controllers;

import app.App;
import app.Logging;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/** kontroler do obsługi dodawania etykiet */
public class LabelPopup implements Initializable {
    @FXML private JFXTextField title;
    @FXML private Text validationText;
    @FXML private Button action;

    private static Stage stage;

    private static boolean editscene;
    private static int labelId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!editscene)
        {
            action.setText("Dodaj");
            action.setOnAction(e->{
                models.Label l = new Label(title.getText());
                if(l.isValid())
                {
                    validationText.setFill(Color.rgb(185, 230, 223));
                    App.calendarManager.addLabel(l);
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else
                {
                    HashMap<String,String> errors = l.getValidationErrors();
                    if(errors.get("text")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                }
            });
        }
        else
        {
            Label l = App.calendarManager.getLabelById(labelId);
            title.setText(l.text);
            action.setText("Edytuj");
            action.setOnAction(e->{
                models.Label newLabel = new Label(title.getText());
                if(newLabel.isValid())
                {
                    validationText.setFill(Color.rgb(185, 230, 223));
                    l.text = newLabel.text;
                    App.calendarManager.saveCalendar();
                    stage.close();
                }
                else
                {
                    HashMap<String,String> errors = l.getValidationErrors();
                    if(errors.get("text")!=null) {
                        validationText.setFill(Color.rgb(254, 203, 200));
                        validationText.setText(errors.get("text"));
                    }
                }
            });
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
            return new Scene(FXMLLoader.load(LabelPopup.class.getClassLoader().getResource("fxml/LabelPopup.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Popup EventPopup initialization failed");
            Logging.Logger.logError("Popup EventPopup initialization failed");
        }
        return null;
    }

    /** wyświetlanie popupu */
    private static void display() {
        if(stage==null) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(362);
            stage.setHeight(200);
            stage.setResizable(false);
            stage.setTitle("Etykieta");
        }
        stage.setScene(loadScene());
        stage.showAndWait();
    }

    static void displayEdit(int id) {
        editscene = true;
        labelId = id;
        display();
    }

    static void displayAdd() {
        editscene = false;
        display();
    }
}
