package models;

import application.App;
import data_util.ValidationUtil;

import java.io.Serializable;
import java.util.HashMap;

public class Label implements Serializable {
    public int id;
    public String text;

    public boolean isValid()
    {
        boolean labelNameUnique = !ValidationUtil.listContains(App.calendarManager.getAllLabels(),this);
        boolean isTextLengthValid = ValidationUtil.StringLengthBetween(text,1,32);
        return labelNameUnique && isTextLengthValid;
    }

    public HashMap<String,String> getValidationErrors()
    {
        HashMap<String,String>  errors = new HashMap<>();
        boolean labelNameNotUnique = ValidationUtil.listContains(App.calendarManager.getAllLabels(),this);

        if(labelNameNotUnique)
            errors.put("text","Etykieta o takiej nazwie już istnieje!");


        if(!ValidationUtil.StringLengthBetween(text,1,32))
            errors.put("text","Etykieta może zawierać od 1 do 32 znaków.");

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

    @Override
    public boolean equals(Object o)
    {
        return this.text.equals(((Label)o).text);
    }
}
