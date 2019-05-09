package app;

import java.util.HashMap;

public class GlobalOptions {
    public static String dateFormat = "yyyy/MM/dd HH:mm:ss";
    public static String logDateFormat = "yyyy-MM-dd-HH-mm-ss";
    public static HashMap<String, String> userOptions = new HashMap<>();

    public String getUserOptions(String arg) {
        return userOptions.get(arg);
    }

    public void setUserOptions(HashMap<String, String> userOptions) {
        this.userOptions = userOptions;
    }

    //ciemny motyw jest domyślny!
    public GlobalOptions() {
        userOptions.put("stylesheet", "dark");
    }
}
