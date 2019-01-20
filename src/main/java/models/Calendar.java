package models;

import application.App;
import data_util.ValidationUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Calendar implements Serializable {
    public String name;
    public String comment;

    public ArrayList<Note> notes;
    public ArrayList<Label> labels;

    public int nextLabelId;
    public int nextNoteId;

    public Calendar() {}

    public Calendar(String name, String comment) {
        this.name = name;
        this.comment = comment;

        this.notes = new ArrayList<>();
        this.labels = new ArrayList<>();

        this.nextNoteId = 0;
        this.nextLabelId = 1;

        Label l = new Label();
        l.id=0;l.text="";
        this.labels.add(l);
    }

    public boolean isValid()
    {
        boolean calendarNameValid = name!=null && name.matches("^[a-zA-Z0-9][a-zA-Z0-9 -_]{0,31}");
        boolean calendarComValid = ValidationUtil.StringLengthBetween(comment,1,500);
        boolean calendarNameUnique = !ValidationUtil.tableContains(App.calendarManager.getCalendarIndex(),name);

        return calendarComValid && calendarNameValid && calendarNameUnique;
    }

    public HashMap<String,String> getValidationErrors()
    {
        HashMap<String,String> errors = new HashMap<>();
        if(name==null || !name.matches("^[a-zA-Z0-9][a-zA-Z0-9 -_]{0,31}"))
            errors.put("name","Nazwa może zawierać 32 znaki.");
        if(!ValidationUtil.StringLengthBetween(comment,1,500))
            errors.put("comment","Komentarz może zawierać od 1 do 500 znaków.");

        boolean calendarNameNotUnique = ValidationUtil.tableContains(App.calendarManager.getCalendarIndex(),name);

        if(calendarNameNotUnique)
            errors.put("name","Kalendarz o podanej nazwie już istnieje!");

        return errors;
    }

    @Override
    public String toString() {
        StringBuilder var = new StringBuilder();
        var.append("\nCalendar: " + name + "\nComment: " + comment + "\nLabels:\n");
        for (Label l : labels)
            var.append(l + "\n");
        var.append("Notes:\n");
        for (Note n : notes)
            var.append(n + "\n");

        return var.toString();
    }
}
