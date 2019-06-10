package app;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class PrimaryStageManager
{

    private static Stage primaryStage;

    private static final int minWidth = 1024;
    private static final int minHeight = 768;

    private static final String stageTitle = "Purranya";

    private static final Scene blankScene = new Scene(new Label(""));

    public static void initialize(Stage primary)
    {
        primaryStage = primary;
        primaryStage.getIcons().add(new Image("img/PurranyaSmallLogo.png"));
        primaryStage.setTitle(stageTitle);
        primaryStage.setWidth(minWidth);
        primaryStage.setHeight(minHeight);
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        Stage intro = getIntroStage();

        intro.show();

        double duration = 1;
        PauseTransition delay = new PauseTransition(Duration.millis(duration));
        delay.setOnFinished(event ->
        {
            intro.close();
            primaryStage.show();
        });
        delay.play();
    }

    public PrimaryStageManager()
    {
        if(primaryStage==null)
            throw new RuntimeException("Primary stage not initialized");
    }

    public void loadScene(String sceneName)
    {
        try
        {
            URL url = getClass().getClassLoader().getResource("fxml/" + sceneName + ".fxml");
            if (url == null)
                throw new IOException("Cannot get url");
            Scene s = new Scene(FXMLLoader.load(url));
            primaryStage.setScene(s);
        } catch (IOException e)
        {
            Logging.logError("SceneManager " +
                    "initialization failed\n"
                    + e.toString());
            System.exit(1);
        }
    }

    private static Stage getIntroStage()
    {
        Stage intro = new Stage();
        intro.initStyle(StageStyle.UNDECORATED);
        intro.setWidth(633);
        intro.setHeight(559);
        intro.setScene(loadingScene());
        return intro;
    }

    private static Scene loadingScene()
    {
        Image image = new Image("img/loading_bar.gif");
        ImageView view = new ImageView(image);
        AnchorPane pane = new AnchorPane(view);
        return new Scene(pane);
    }
}
