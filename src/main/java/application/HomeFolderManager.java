package application;

import application.OperationSystemData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeFolderManager {

    String osHome;
    OperationSystemData.OS os;
    String osUser;
    String appHome;
    static HashMap<String,String> paths = new HashMap<>();

    public HomeFolderManager() {
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
            dirs.add("\\databases"); paths.put("databases",this.appHome+"\\databases\\");
            dirs.add("\\options"); paths.put("options",this.appHome+"\\options\\");
            dirs.add("\\logs"); paths.put("logs",this.appHome+"\\logs\\");
        } else if (this.os.equals(OperationSystemData.OS.UNIX)){
            dirs.add("/databases"); paths.put("databases",this.appHome+"/databases/");
            dirs.add("/options"); paths.put("options",this.appHome+"/options/");
            dirs.add("/logs"); paths.put("logs",this.appHome+"/logs/");
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

    public boolean createDirectory(String dir){
        boolean res;
        String appHome = this.appHome + dir;
        try {
            File f = new File(appHome);
            if(!f.exists())
                res = f.mkdir();
            else
                res=true;
        } catch (Exception e) {
            res = false;
        }
        return res;
    }
    public boolean deleteDirectory(String dir){
        boolean res;
        String appHome = this.appHome + dir;
        try {
            File f = new File(appHome);
            if(f.exists())
                deleteFolder(f);
            res=true;
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    //ze staka, usuwa folder, usuwając najpierw całą zawartość rekursywnie
    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    public String getPath(String dir) {
        return paths.get(dir);
    }

    public String getErrorLogPath() {
        String res = getPath("logs");

        if (this.os.equals(OperationSystemData.OS.WINDOWS))
            res += "\\errorlog";
        else if (this.os.equals(OperationSystemData.OS.UNIX))
            res += "/errorlog";

        return res;
    }

    public String getEventLogPath() {
        String res = getPath("logs");

        if (this.os.equals(OperationSystemData.OS.WINDOWS))
            res += "\\eventlog";
        else if (this.os.equals(OperationSystemData.OS.UNIX))
            res += "/eventlog";

        return res;
    }

    public String[] getListOfFiles(String dir) {
        File databases = new File(getPath(dir));
        ArrayList<String> list = new ArrayList<>();
        for(File f : databases.listFiles())
            if (f.isFile())
                list.add(f.getName());

        String[] res = new String[list.size()];
        for(int i=0;i<res.length;i++)
            res[i]=list.get(i);

        return res;
    }
}