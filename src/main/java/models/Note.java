package models;

import application.App;
import application.GlobalOptions;
import data_util.ValidationUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.HashMap;

public class Note implements Serializable {

    public int id;
    public String title;
    public String content;
    public boolean isArchived;
    public Label label;

    public DateTime startDate;
    public DateTime endDate;

    public Note() {
    }

    public Note(String title, String content, boolean isArchived, Label label, DateTime startDate, DateTime endDate) {
        this.title = title;
        this.content = content;
        this.isArchived = isArchived;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValid()
    {
        boolean isTitleLengthValid = ValidationUtil.StringLengthBetween(title,1,32);
        boolean isEndDateValid = (endDate==null || ( endDate!=null && endDate.isAfter(startDate)));
        return isTitleLengthValid && isEndDateValid;
    }

    public HashMap<String,String> getValidationErrors()
    {
        boolean isTitleLengthValid = ValidationUtil.StringLengthBetween(title,1,32);
        boolean isEndDateValid = (endDate==null || ( endDate!=null && endDate.isAfter(startDate)));

        HashMap<String,String>  errors = new HashMap<>();
        if(!isTitleLengthValid)
            errors.put("text","Tytuł może mieć od 1 do 33 znaków");
        if(!isEndDateValid)
            errors.put("enddate","Data końcowa musi być większa niż data początkowa");
        return errors;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(GlobalOptions.dateFormat);

        return id + "-\"" + title + "," + (content == null || content.equals("") ? "<empty>" : "<content>") + "," + (isArchived ? "archived" : "not archived") + ",label:" + label + "," + fmt.print(startDate) + "," + (endDate!=null?fmt.print(endDate):"null");
    }
}
