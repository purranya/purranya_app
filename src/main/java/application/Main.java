package application;


import javafx.application.Application;
import javafx.stage.Stage;

import static application.StageOptions.setStage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        StageOptions stageOptions = new StageOptions();
        setStage("Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
