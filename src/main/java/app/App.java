package app;

import javafx.stage.Stage;
import javafx.application.Application;
import models.transfer_models.Login;

public class App extends Application {

    public static Login login = null;
    public static boolean isAuthorized = false;

    @Override
    public void start(Stage primaryStage)
    {
        PrimaryStageManager.initialize(primaryStage);
        new PrimaryStageManager().loadScene("MainMenu");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
