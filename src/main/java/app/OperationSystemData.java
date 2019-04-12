package app;

import java.io.IOException;
import java.util.Locale;

public class OperationSystemData {
    public static String osUser = getOsUser();
    public static String osHome = getOsHome();
    public static OS os = getOs();

    public enum OS {
        WINDOWS,
        UNIX,
        OTHER;

        public String toString()
        {
            if(this.equals(OS.WINDOWS)) return "Windows";
            else if (this.equals(OS.UNIX)) return "Unix";
            else return "Other OS";
        }
    }

    private static OS getOs() {
        OS os;
        try {
            String osName = System.getProperty("os.name");
            if (osName == null)
                throw new IOException("os.name not found");
            osName = osName.toLowerCase(Locale.ENGLISH);
            if (osName.contains("windows"))
                os = OS.WINDOWS;
            else if (osName.contains("linux")
                    || osName.contains("mpe/ix")
                    || osName.contains("freebsd")
                    || osName.contains("irix")
                    || osName.contains("digital unix")
                    || osName.contains("unix"))
                os = OS.UNIX;
            else
                os = OS.OTHER;
        } catch(Exception e) {
            os = OS.OTHER;
        }
        return os;
    }

    private static String getOsUser()
    {
        String osUser;
        try {
            osUser = System.getProperty("user.name");
            if (osUser == null)
                throw new IOException("user.name not found");
        } catch ( Exception e) {
            osUser = "UNKNOWN_USER";
        }
        return osUser;
    }

    private static String getOsHome()
    {
        String osHome;
        try {
            osHome = System.getProperty("user.home");
            if (osHome == null)
                throw new IOException("user.home not found");
        } catch ( Exception e) {
            osHome = "UNKNOWN_HOME";
        }
        return osHome;
    }
}
