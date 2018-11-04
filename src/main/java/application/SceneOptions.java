package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneOptions {
    private ActionEvent event;
    public SceneOptions(ActionEvent event) {
        this.event = event;
    }

    public void change(String name) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/" + name + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new javafx.scene.Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
