package app;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PrimaryStageManager {
    private HashMap<String, Scene> sceneContainer;
    private Stage primaryStage;

    private final int minWidth=1024;
    private final int minHeight=768;

    private final String stageTitle="Purranya";



    public PrimaryStageManager(Stage primary) {
        try {
            this.primaryStage = primary;

            sceneContainer = new HashMap<>();
            sceneContainer.put("BlankScene",new Scene(new HBox()));

            Stage intro = new Stage();
            intro.initStyle(StageStyle.UNDECORATED);
            intro.setWidth(633);
            intro.setHeight(559);
            intro.setScene(loadingScene());
            primaryStage.getIcons().add(new Image("img/PurranyaLogo4.png"));

            double duration = (double)((new Random()).nextInt()%1000 + 3000);
            intro.show();
            PauseTransition delay = new PauseTransition(Duration.millis(duration));
            delay.setOnFinished( event -> {
                            intro.close();
                            primaryStage.setTitle(stageTitle);
                            primaryStage.setWidth(minWidth);
                            primaryStage.setHeight(minHeight);
                            primaryStage.setMinWidth(minWidth);
                            primaryStage.setMinHeight(minHeight);


                            setScene("MainMenu");

                            primaryStage.show();
        });
            delay.play();




        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println("SceneManager initialization failed");
            Logging.Logger.logError("SceneManager initialization failed");
            System.exit(1);
        }
    }

    private Scene loadingScene()
    {
        Image image = new Image("img/loading_bar.gif");
        ImageView view = new ImageView(image);
        AnchorPane pane = new AnchorPane(view);
        Scene scene = new Scene(pane);
        return scene;
    }

    public void setScene(String sceneName) {
        if(sceneContainer.get(sceneName)!=null) {
            primaryStage.setScene(sceneContainer.get("BlankScene"));
            primaryStage.setScene(sceneContainer.get(sceneName));
        }
        else
        {
            loadScene(sceneName);
            setScene(sceneName);
        }
    }

    public void loadScene(String sceneName) {
        try {
            sceneContainer.put(sceneName, new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/"+sceneName+".fxml"))));
        } catch ( Exception e) {
            e.printStackTrace();
            System.err.println("SceneManager initialization failed");
            Logging.Logger.logError("SceneManager initialization failed");
            System.exit(1);
        }
    }

    public void reloadScene(String sceneName)
    {
        loadScene(sceneName);
        setScene(sceneName);
    }

    /** metoda do obsługi popupów
     * @param name nazwa kontrolera oraz pliku FXML
     */
    public void popup(String name, Object controller) {
        Popup popup = new Popup();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + name + ".fxml"));
        loader.setController(controller);
        try {
            popup.getContent().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Popup initialization failed");
            Logging.Logger.logError("Popup initialization failed");
        }
    }
}
