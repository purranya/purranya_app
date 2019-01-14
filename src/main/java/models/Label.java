package models;

import java.io.Serializable;

public class Label implements Serializable {
    public int id;
    public String text;

    public Label() {
    }

    public Label(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return id + "-\"" + text + "\"";
    }
}
