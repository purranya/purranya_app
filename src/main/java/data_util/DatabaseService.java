package data_util;

import application.HomeFolderManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseService {

    public DatabaseService(){}

    public ArrayList<String> getDatabasesIndex() {
        HomeFolderManager hfc = new HomeFolderManager();
        File[] directories = new File(hfc.getPath("databases")).listFiles(File::isDirectory);
        ArrayList<String> names = new ArrayList<>();

        for(File f : directories) {
            String path = f.toString();
            int l = 0;
            if(OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS))
                l= path.lastIndexOf('\\');
            else if(OperationSystemData.os.equals(OperationSystemData.OS.UNIX))
                l= path.lastIndexOf('/');
            names.add(f.toString().substring(l+1,path.length()));
        }
        return names;
    }

    public Connection getDatabaseConnection(String database){
        HomeFolderManager hfc = new HomeFolderManager();
        ArrayList<String> index = getDatabasesIndex();
        if(!index.contains(database)){
            System.err.println("Brak takiej bazy w katalogu");
            return null;
        }

        String path = hfc.getPath("databases");

        if(OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS))
            path += "\\" + database + "\\";
        else if(OperationSystemData.os.equals(OperationSystemData.OS.UNIX))
            path += "/" + database + "/";

        try {
            DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
            return DriverManager.getConnection("jdbc:hsqldb:file:" + path, "SA", "");
        } catch ( Exception e) {
            e.printStackTrace();
            System.err.println("Bład przy tworzeniu połaczenia");
            return null;
        }
    }

    public boolean createDatabase(String database) {

        HomeFolderManager hfc = new HomeFolderManager();
        if (OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS)) {
            if (!hfc.createDirectory("\\databases\\" + database))
                System.err.println("Nie udało się utworzyć katalogu dla bazy danych");
        } else if (OperationSystemData.os.equals(OperationSystemData.OS.UNIX)){
            if (!hfc.createDirectory("/databases/" + database))
                System.err.println("Nie udało się utworzyć katalogu dla bazy danych");
        }
        try {
            getDatabaseConnection(database);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteDatabase(String database) {
        HomeFolderManager hfc = new HomeFolderManager();
        boolean success=false;
        if (OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS)) {
            success = hfc.deleteDirectory("\\databases\\" + database);
            if (!success)
                System.err.println("Nie udało się usunąć katalogu dla bazy danych");
        } else if (OperationSystemData.os.equals(OperationSystemData.OS.UNIX)){
            success = hfc.deleteDirectory("/databases/" + database);
            if (!success)
                System.err.println("Nie udało się usunąć katalogu dla bazy danych");
        }
        return success;
    }
}
