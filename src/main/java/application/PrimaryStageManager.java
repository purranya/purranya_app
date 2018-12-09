package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class PrimaryStageManager {
    private HashMap<String, Scene> sceneContainer;
    private Stage primaryStage;

    private final int minWidth=1024;
    private final int minHeight=768;

    private final String stageTitle="Purranya";
    private final String[] scenes = {
            "Calendar","CreateAccount","CreateAccountConfirm",
            "MainMenu","Login","RestorePassword",
            "StickyNotes", "AddEvent"
    };


    public PrimaryStageManager(Stage primary) {
        try {
            this.primaryStage = primary;

            sceneContainer = new HashMap<>();

            for(String scene : scenes)
                loadScene(scene);

            primaryStage.setTitle(stageTitle);
            primaryStage.setWidth(minWidth);
            primaryStage.setHeight(minHeight);
            primaryStage.setMinWidth(minWidth);
            primaryStage.setMinHeight(minHeight);

            setScene("MainMenu");

            primaryStage.show();

        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println("SceneMenager initialization failed");
            Logging.Logger.logError("SceneMenager initialization failed");
            System.exit(1);
        }
    }

    public void setScene(String sceneName) {
        primaryStage.setScene(sceneContainer.get(sceneName));
    }

    public void loadScene(String sceneName) {
        try {
            sceneContainer.put(sceneName, new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/"+sceneName+".fxml"))));
        } catch ( Exception e) {
            e.printStackTrace();
            System.err.println("SceneMenager initialization failed - ");
            Logging.Logger.logError("SceneMenager initialization failed");
            System.exit(1);
        }
    }
}
