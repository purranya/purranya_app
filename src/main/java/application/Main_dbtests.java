package application;

import data.Calendar;
import data.CalendarManager;
import data.Label;
import data.Note;

public class Main_dbtests {

    public static HomeFolderManager homeFolderManager = new HomeFolderManager();

    public static void main(String[] args)
    {
        App.initialize(null);
        CalendarManager cs = new CalendarManager();

        Calendar c = cs.getCallendar("Test");

        System.out.println("nazwa: " + c.name);
        System.out.println("komentarz: " + c.comment);
        System.out.println("ilość etykiet: " + c.labels.size());
        System.out.println("następne id etykiety: " + c.nextLabelId);
        System.out.println("<----->");
        for(Label l : c.labels)
            System.out.println( "etykieta: " + l.id + "-" + l.text );
        System.out.println("<----->");
        System.out.println("następne id notatki: " + c.nextNoteId);
        System.out.println("<----->");
        for(Note n : c.notes)
            System.out.println("notatka: " + n.id + "-" + n.title + "\n" +
                    "kategoria: " + n.label.text + "\n" +
                    "kontent: " + n.content + "\n" +
                    "archived: " + n.isArchived + "\n" +
                    "data pocz: " + n.startDate + "\n" +
                    "data kon: " + n.endDate
            );

        System.out.println("<----->");



    }
}
