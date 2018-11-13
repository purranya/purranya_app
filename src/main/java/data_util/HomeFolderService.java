package data_util;

import java.io.File;
import java.util.ArrayList;

public class HomeFolderService {

    String osHome;
    OperationSystemData.OS os;
    String osUser;
    String appHome;

    public HomeFolderService() {
        this.os = OperationSystemData.os;
        this.osHome = OperationSystemData.osHome;
        this.osUser = OperationSystemData.osUser;

        if(!osHomeFolderExists())
            System.err.println("Brak systemowego folderu domomowego użytkownika");

        if (this.os.equals(OperationSystemData.OS.WINDOWS))
            this.appHome = this.osHome + "\\purranya";
        else if (this.os.equals(OperationSystemData.OS.UNIX))
            this.appHome = this.osHome + "/.purranya";

        if(!appHomeDirectoryExists())
            if(!createDirectory(""))
                System.err.println("Bład przy tworzeniu katalogu");

        //katalogi w home
        ArrayList<String> dirs = new ArrayList<>();
        if (this.os.equals(OperationSystemData.OS.WINDOWS)){
            dirs.add("\\databases");
            dirs.add("\\options");
        } else if (this.os.equals(OperationSystemData.OS.UNIX)){
            dirs.add("/databases");
            dirs.add("/options");
        }
        for(String dir : dirs)
            if(!directoryExists(dir))
                if(!createDirectory(dir))
                    System.err.println("Bład przy tworzeniu katalogu");

    }

    private boolean osHomeFolderExists() {
        boolean res;
        String osHome = this.osHome;
        try {
            File osHomeDir = new File(osHome);
            res = osHomeDir.exists();
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    private boolean appHomeDirectoryExists(){
        boolean res;
        String appHome = this.appHome;
        try {
            File osHomeDir = new File(appHome);
            res = osHomeDir.exists();
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    private boolean directoryExists(String dir){
        boolean res;
        String appHome = this.appHome + dir;
        try {
            File osHomeDir = new File(appHome);
            res = osHomeDir.exists();
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    private boolean createDirectory(String dir){
        boolean res;
        String appHome = this.appHome + dir;
        try {
            res = (new File(appHome)).mkdir();
        } catch (Exception e) {
            res = false;
        }
        return res;
    }
}