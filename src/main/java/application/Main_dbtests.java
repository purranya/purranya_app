package application;

import data.domain.Calendar;
import data.CalendarManager;
import data.domain.Label;
import data.domain.Note;
import org.joda.time.DateTime;

import java.lang.invoke.CallSite;

import static application.App.calendarManager;

public class Main_dbtests {

    public static HomeFolderManager homeFolderManager = new HomeFolderManager();

    public static void main(String[] args)
    {
        App.initialize(null);

        CalendarManager cm = calendarManager;
        cm.loadCallendar("test");
        //cm.renameCalendar("test2");

        System.out.println(cm.getCalendar_DEBUG());


    }
}
