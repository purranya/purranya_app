package application;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileOutputStream;

import static application.GlobalOptions.logDateFormat;

public class Logging {
    public static Logging Logger = new Logging();
    private FileOutputStream errorLog = null;
    private FileOutputStream eventLog = null;
    HomeFolderManager hfs;

    private Logging(){}

    public void logError(String error)
    {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(logDateFormat);
        String date = fmt.print(dt);
        if(hfs==null) hfs = new HomeFolderManager();
        if(errorLog==null) {
            try {
                String path = hfs.getErrorLogPath() + "-" + date + ".txt";
                errorLog = new FileOutputStream(new File(path));
            } catch ( Exception e) {
                System.err.println("Zapisywanie loga nie powiodło się");
                e.printStackTrace();
            }
        }

        String log = date + " " + error + "\n";
        try {
            errorLog.write(log.getBytes());
        } catch ( Exception e) {
            System.err.println("Zapisywanie loga nie powiodło się");
            e.printStackTrace();
        }

    }

    public void logEvent(String event)
    {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(logDateFormat);
        String date = fmt.print(dt);
        if(hfs==null) hfs = new HomeFolderManager();
        if(eventLog==null) {
            try {
                String path = hfs.getEventLogPath() + "-" + date + ".txt";
                eventLog = new FileOutputStream(new File(path));
            } catch ( Exception e) {
                System.err.println("Zapisywanie loga nie powiodło się");
                e.printStackTrace();
            }
        }

        String log = date + " " + event + "\n";
        try {
            eventLog.write(log.getBytes());
        } catch ( Exception e) {
            System.err.println("Zapisywanie loga nie powiodło się");
            e.printStackTrace();
        }
    }
}
