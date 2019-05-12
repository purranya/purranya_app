package app;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Logging {

    public static void logError(String error)
    {
        GlobalOptions globalOptions = new GlobalOptions();
        HomeFolderManager homeFolderManager = new HomeFolderManager();

        boolean logToFile = globalOptions.get("logging.file").equals("true");
        boolean logToConsole = globalOptions.get("logging.console").equals("true");

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(globalOptions.get("date.format.log"));
        String date = fmt.print(dt);

        String logEntry = "error-" + date + " : " + error;

        if(logToFile)
        {
            FileOutputStream errorLog;
            String filename = "errorlog-" + date + ".log";
            Path path = homeFolderManager.getPath("logs").resolve(filename);

            try
            {
                if (Files.notExists(path))
                    Files.createFile(path);

                errorLog = new FileOutputStream(path.toFile());

                errorLog.write((logEntry + "\n").getBytes());
            } catch (IOException io)
            {
                System.err.println("Creating log file failed");
            }
        }
        if(logToConsole)
        {
            System.err.println(logEntry);
        }

    }

    public void logEvent(String event)
    {
        GlobalOptions globalOptions = new GlobalOptions();
        HomeFolderManager homeFolderManager = new HomeFolderManager();

        boolean logToFile = globalOptions.get("logging.file").equals("true");
        boolean logToConsole = globalOptions.get("logging.console").equals("true");

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(globalOptions.get("date.format.log"));
        String date = fmt.print(dt);

        String logEntry = "event-" + date + " : " + event;

        if(logToFile)
        {
            FileOutputStream eventLog;
            String filename = "eventlog-" + date + ".log";
            Path path = homeFolderManager.getPath("logs").resolve(filename);

            try
            {
                if (Files.notExists(path))
                    Files.createFile(path);

                eventLog = new FileOutputStream(path.toFile());

                eventLog.write((logEntry + "\n").getBytes());
            } catch (IOException io)
            {
                System.err.println("Creating log file failed");
            }
        }
        if(logToConsole)
        {
            System.out.println(logEntry);
        }
    }
}
