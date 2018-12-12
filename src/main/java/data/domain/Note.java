package data.domain;

import application.GlobalOptions;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

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

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(GlobalOptions.dateFormat);

        return id + "-\"" + title + "," + (content == null || content.equals("") ? "<empty>" : "<content>") + "," + (isArchived ? "archived" : "not archived") + ",label:" + label + "," + fmt.print(startDate) + "," + fmt.print(endDate);
    }
}
