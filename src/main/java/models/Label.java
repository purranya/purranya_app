package models;

import application.App;

import java.io.Serializable;
import java.util.HashMap;

public class Label implements Serializable {
    public int id;
    public String text;

    public boolean isValid()
    {

        boolean isTextLengthValid = text.length()<33;
        return text.length()<33 && text.length()>0;
    }

    public HashMap<String,String> getValidationErrors()
    {
        HashMap<String,String>  errors = new HashMap<>();

        if(!(text.length()>0))
            errors.put("text","Nazwa musi zawierać minimum 1 znak");

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
