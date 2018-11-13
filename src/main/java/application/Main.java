package application;

import data_util.OperationSystemData;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
        /* ustawienia okna */
        primaryStage.setTitle("Purranya");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
