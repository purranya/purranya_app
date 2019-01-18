package application;

import data.CalendarManager;

import static application.App.calendarManager;

public class Main_dbtests {

    public static HomeFolderManager homeFolderManager = new HomeFolderManager();

    public static void main(String[] args)
    {
        App.initialize(null);

        String[] l = App.homeFolderManager.getListOfFiles("databases");

        for(String s : l)
            System.out.println(s);

    }
}
