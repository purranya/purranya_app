package application;

import data.CalendarManager;

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
