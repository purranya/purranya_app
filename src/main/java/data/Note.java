package data;

import org.joda.time.DateTime;

public class Note {

    public int id;
    public String title;
    public String content;
    public boolean isArchived;
    public Label label;

    public DateTime startDate;
    public DateTime endDate;

    public Note(int id, String title, String content, boolean isArchived, Label label, DateTime startDate, DateTime endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isArchived = isArchived;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
