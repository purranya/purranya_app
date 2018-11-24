package application;

import data.Calendar;
import data.CalendarManager;

public class Main_dbtests {

    public static HomeFolderManager homeFolderManager = new HomeFolderManager();

    public static void main(String[] args)
    {
        App.initialize(null);
        CalendarManager cs = new CalendarManager();

        Calendar c = cs.getCallendar("Test");

        System.out.println(c.name);
        System.out.println(c.comment);


        cs.saveCallendar(c);


    }
}
