package models;

import java.time.LocalDateTime;

public class NoteModel extends Model {
    /*
    -Notatka ma tytuł i zawartość
    -Notatka może nie mieć daty, może mieć datę pojedyńczą, lub okres (2 daty)
    -Notatka może być zarchiwizowana (będzie pokazywana na liście w specjalnej sekcji i nie będzie brana pod uwagę
        w przypominajkach
    -format daty dla parsera 2007-12-03T10:15:30.
    */

    String title;

    boolean isArchived=false;;
    boolean hasTimeStamp=false;;
    boolean hasTimePeriod=false;;

    LocalDateTime timestamp;
    LocalDateTime startDate;
    LocalDateTime endDate;

    String content;

    public NoteModel(String path){

    }

    public NoteModel(String title, String content, boolean isArchived) {

        this.created = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();

        this.isArchived = isArchived;

        this.title=title;
        this.content=content;
    }

    public NoteModel(String title, String content, String timestamp, boolean isArchived){

        this(title,content,isArchived);

        this.hasTimeStamp=true;
        this.timestamp = LocalDateTime.parse(timestamp);
    }

    public NoteModel(String title, String content, String startDate, String endDate, boolean isArchived){

        this(title,content,isArchived);

        this.hasTimePeriod=true;
        this.startDate = LocalDateTime.parse(startDate);
        this.endDate = LocalDateTime.parse(endDate);
    }

}
