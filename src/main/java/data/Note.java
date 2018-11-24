package data;

import org.joda.time.DateTime;
import org.w3c.dom.Node;

public class Note {
    /*
    -Notatka ma tytuł i zawartość
    -Notatka może nie mieć daty, może mieć datę pojedyńczą, lub okres (2 daty)
    -Notatka może być zarchiwizowana (będzie pokazywana na liście w specjalnej sekcji i nie będzie brana pod uwagę
    */

    public String title;

    public boolean isArchived = false;

    public DateTime startDate;
    public DateTime endDate;

    public String content;

    public Note() {
    }

    public Note(String title, String content, boolean isArchived) {
        this.isArchived = isArchived;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, DateTime timestamp, boolean isArchived) {

        this(title, content, isArchived);
        this.startDate = timestamp;
    }

    public Note(String title, String content, DateTime startDate, DateTime endDate, boolean isArchived) {

        this(title, content, isArchived);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Note(Node noteNode){}
}
