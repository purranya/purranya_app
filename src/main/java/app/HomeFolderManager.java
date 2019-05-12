package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class HomeFolderManager {

    OperationSystemData.OS os;

    Path osHome;
    String osUser;
    Path appHome;
    static HashMap<String,Path> paths = new HashMap<>();

    public HomeFolderManager() {
        this.os = OperationSystemData.os;
        this.osHome = Paths.get(OperationSystemData.osHome);
        this.osUser = OperationSystemData.osUser;

        if(Files.notExists(osHome))
        {
            System.err.println("Critical error: cannot locate home folder. Stopping app...");
            System.exit(1);
        }

        appHome = osHome.resolve(".purranya");

        if(Files.notExists(appHome))
        {
            try{
                Files.createDirectory(appHome);
            } catch ( IOException e ) {
                System.err.println("Critical error: cannot create main app folder. Stopping app...");
                System.exit(1);
            }
        }

        paths.put("options",appHome.resolve("options"));
        paths.put("logs",appHome.resolve("logs"));

        for(Map.Entry<String,Path> entry : paths.entrySet())
            if(Files.notExists(entry.getValue()))
            {
                try{
                    Files.createDirectory(entry.getValue());
                } catch ( IOException e ) {
                    System.err.println("Critical error: cannot create \""+ entry.getKey() +"\" folder. Stopping app...");
                    System.exit(1);
                }
            }
    }

    public Path getPath(String dir) {
        return paths.get(dir);
    }
}