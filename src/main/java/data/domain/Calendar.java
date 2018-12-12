package data.domain;

import java.io.Serializable;
import java.util.ArrayList;

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
        l.id=0;l.text="null";
        this.labels.add(l);
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
