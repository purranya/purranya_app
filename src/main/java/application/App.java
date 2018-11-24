package application;

import javafx.stage.Stage;
import javafx.application.Application;

public class App extends Application {
    public static HomeFolderManager homeFolderManager;
    public static PrimaryStageManager primaryStageManager;

    public static void initialize(Stage primary) {

        homeFolderManager = new HomeFolderManager();
        if(primary!=null)primaryStageManager = new PrimaryStageManager(primary);
    }

    @Override
    public void start(Stage primaryStage){
        App.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
