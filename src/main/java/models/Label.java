package models;

import application.App;

import java.io.Serializable;
import java.util.HashMap;

public class Label implements Serializable {
    public int id;
    public String text;

    public boolean isValid()
    {
        boolean isTextValid = text.matches("[a-zA-Z0-9]");
        boolean isTextLengthValid = text.length()<33;
        return isTextValid && isTextLengthValid;
    }

    public HashMap<String,String> getValidationErrors()
    {
        HashMap<String,String>  errors = new HashMap<>();

        if(!text.matches("[a-zA-Z0-9]"))
            errors.put("text","Nazwa musi zawierać co najmniej 1 znak alfanumeryczny");

        if(!(text.length()<33))
            errors.put("text","Nazwa musi zawierać maksymalnie 32 znaki");

        return errors;
    }

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
