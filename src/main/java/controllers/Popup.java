package controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
    public enum POPUP_TYPE { ALERT ,BOOLEAN, THREE_WAY }
    //Alert ma tylko przycisk ok, zawsze zwraca 0
    //boolean ma tak=1/nie=2, a zamknięcie okna daje anuluj=0
    //threeway ma tak=1,nie=2 i anuluj=0

    String title;
    String message;
    POPUP_TYPE type;

    int answer=0;

    final int width=300;
    final int height=150;

    public Popup(String title,String message) {this(title,message,POPUP_TYPE.BOOLEAN);}
    public Popup(String title,String message,POPUP_TYPE type){
        this.type=type;
        this.title=title;
        this.message=message;
    }

    public int display() {
        //przygotowanie okna
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);
        popupStage.setWidth(width);
        popupStage.setHeight(height);
        popupStage.setResizable(false);
        //popupStage.setAlwaysOnTop(true);

        switch (type)
        {
            case BOOLEAN: {
                //jeśli chcesz to zamiast tego wszystkiego możesz zrobić ladowanie fxmla z widokiem
                //<--
                Label popupMessage = new Label(message);
                Button yesButton = new Button("Tak");
                yesButton.setOnAction(e ->{
                    answer=1;
                    popupStage.close();
                });
                Button noButton = new Button("Nie");
                noButton.setOnAction(e ->{
                    answer=2;
                    popupStage.close();
                });

                HBox buttons = new HBox(yesButton,noButton);
                buttons.setAlignment(Pos.CENTER);
                VBox content = new VBox(popupMessage,buttons);
                content.setAlignment(Pos.CENTER);
                //-->

                popupStage.setScene(new Scene(content));

                //Ta funkcja zatrzymuje wątek, aż nie zamknie się okno, a okno zamykane jest przez przyciski i przez krzyżyk
                //naciśnięcie krzyżyka też zwraca "anuluj"
                popupStage.showAndWait();
            } break;
            case ALERT: {

                Label popupMessage = new Label(message);
                Button button = new Button("Ok");
                button.setOnAction(e -> popupStage.close());
                VBox content = new VBox(popupMessage,button);
                content.setAlignment(Pos.CENTER);

                popupStage.setScene(new Scene(content));

                popupStage.showAndWait();
            } break;
            case THREE_WAY: {
                Label popupMessage = new Label(message);
                Button yesButton = new Button("Tak");
                yesButton.setOnAction(e ->{
                    answer=1;
                    popupStage.close();
                });
                Button noButton = new Button("Nie");
                noButton.setOnAction(e ->{
                    answer=2;
                    popupStage.close();
                });
                Button cancelButton = new Button("Anuluj");
                cancelButton.setOnAction(e ->popupStage.close());

                HBox buttons = new HBox(yesButton,noButton,cancelButton);
                buttons.setAlignment(Pos.CENTER);
                VBox content = new VBox(popupMessage,buttons);
                content.setAlignment(Pos.CENTER);
                //-->

                popupStage.setScene(new Scene(content));

                popupStage.showAndWait();
            } break;
            default: answer=0;
        }
        return answer;
    }
}
