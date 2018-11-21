package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class StageOptions {
    private static HashMap<String, Scene> sceneContainer;
    private static Stage privateStage = new Stage();

    public StageOptions() throws Exception {
        sceneContainer = new HashMap<>();
        sceneContainer.put("Calendar", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Calendar.fxml"))));
        sceneContainer.put("CreateAccount", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/CreateAccount.fxml"))));
        sceneContainer.put("CreateAccountConfirm", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/CreateAccountConfirm.fxml"))));
        sceneContainer.put("Login", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"))));
        sceneContainer.put("LoginUser", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoginUser.fxml"))));
        sceneContainer.put("RestorePassword", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RestorePassword.fxml"))));
        sceneContainer.put("StickyNotes", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StickyNotes.fxml"))));
        sceneContainer.put("AddEvent", new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AddEvent.fxml"))));
    }

    public static void setStage(String stageName) {
        privateStage.setTitle("Purranya");
        privateStage.setScene(sceneContainer.get(stageName));
        privateStage.setWidth(1024);
        privateStage.setHeight(768);
        privateStage.setMinWidth(privateStage.getWidth());
        privateStage.setMinHeight(privateStage.getHeight());
        privateStage.show();
    }
}
