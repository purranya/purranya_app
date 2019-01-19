package models;

import application.App;

import java.io.Serializable;
import java.util.HashMap;

public class Label implements Serializable {
    public int id;
    public String text;

    public boolean isValid()
    {
        boolean labelNameUnique = true;
        for(String f : App.calendarManager.getLabels())
            if(f.equals(text))
                labelNameUnique = false;
        boolean isTextLengthValid = text.length()<33 && text.length()>0;
        return labelNameUnique && isTextLengthValid;
    }

    public HashMap<String,String> getValidationErrors()
    {
        HashMap<String,String>  errors = new HashMap<>();
        boolean labelNameUnique = true;
        for(String f : App.calendarManager.getLabels())
            if(f.equals(text))
                labelNameUnique = false;

        if(!labelNameUnique)
            errors.put("text","Etykieta o takiej nazwie już istnieje");

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
