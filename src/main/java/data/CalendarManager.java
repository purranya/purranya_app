package data;

import application.Logging;
import application.OperationSystemData;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

import application.App;

public class CalendarManager {

    public CalendarManager(){ }

    public ArrayList<String> getCallendarIndex() {
        File[] directories = new File(App.homeFolderManager.getPath("databases")).listFiles(File::isDirectory);
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

    public String getCallendarPath(String name){
        ArrayList<String> index = getCallendarIndex();

        if(!index.contains(name)){
            System.err.println("Brak kalendarza");
            Logging.Logger.logError("Invalid callendar name");
            return null;
        }

        String path = App.homeFolderManager.getPath("databases");

        if(OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS))
            path += '\\' + name + '\\';
        else if(OperationSystemData.os.equals(OperationSystemData.OS.UNIX))
            path += '/' + name + '/';

        return path;
    }

    public Calendar getCallendar(String name)
    {
        String path = getCallendarPath(name);
        Calendar c;

        if(OperationSystemData.os.equals(OperationSystemData.OS.WINDOWS))
            c = new Calendar(new File(path+"\\meta.xml"),new File(path+"\\notes.xml"));
        else if(OperationSystemData.os.equals(OperationSystemData.OS.UNIX))
            c = new Calendar(new File(path+"/meta.xml"),new File(path+"/notes.xml"));
        else
            c=null;

        return c;
    }

    public void saveCallendar(Calendar c){
        try {
            c.save();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Result output = new StreamResult(c.callendarMetaFile);
            Source input = new DOMSource(c.callendarMeta);
            transformer.transform(input, output);

            output = new StreamResult(c.callendarNotesFile);
            input = new DOMSource(c.callendarNotes);
            transformer.transform(input, output);

        } catch (Exception e){
            e.printStackTrace();
            Logging.Logger.logError("Saving callendar failed");
        }
    }
}
