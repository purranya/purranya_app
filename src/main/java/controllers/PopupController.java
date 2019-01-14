package controllers;

import com.jfoenix.controls.JFXTextField;
import models.TestModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupController implements Initializable {
    @FXML
    Text message;
    @FXML
    JFXTextField textfield;

    @FXML
    void yesAction(ActionEvent event) {
        answer = new TestModel(1,textfield.getText());
        popupStage.close();
    }

    @FXML
    void noAction(ActionEvent event) {
        answer = new TestModel(2,null);
        popupStage.close();
    }

    @FXML
    void cancelAction(ActionEvent event) {
        answer = new TestModel(0,null);
        popupStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setText(messageString);
        textfield.setPromptText("odpowiedź");
    }


    static String title;
    static String messageString;

    static TestModel answer = null;

    static final int width = 362;
    static final int height = 198;

    static Stage popupStage;
    static Scene popupScene = loadScene();

    public static TestModel display(String title, String msg) {

        if(popupStage==null) {
            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setWidth(width);
            popupStage.setHeight(height);
            popupStage.setResizable(false);
            popupStage.setScene(popupScene);
        }
        popupStage.setTitle(title);

        ((Text)popupScene.lookup("#message")).setText(msg);

        popupStage.showAndWait();

        return answer;
    }

    static Scene loadScene()
    {
        Scene s = null;
        try{
            s = new Scene(FXMLLoader.load(PopupController.class.getClassLoader().getResource("fxml/TestController.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}