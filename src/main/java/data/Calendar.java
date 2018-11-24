package data;

import application.Logging;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Calendar {

    public File callendarMetaFile;
    public File callendarNotesFile;

    public Document callendarMeta;
    public Document callendarNotes;

    public String name;
    public String comment;
    ArrayList<Note> notes;

    public Calendar() { this.notes = new ArrayList<>(); }

    public Calendar(String name, String comment) {
        this();
        this.name = name;
        this.comment = comment;
    }

    public Calendar(File meta, File notes) {
        this.callendarMetaFile = meta;
        this.callendarNotesFile = notes;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            this.callendarMeta = dBuilder.parse(callendarMetaFile);
            this.callendarNotes = dBuilder.parse(callendarNotesFile);

            this.name = callendarMeta.getDocumentElement().getChildNodes().item(0).getTextContent();
            this.comment = callendarMeta.getDocumentElement().getChildNodes().item(1).getTextContent();
        } catch ( Exception e ) {
            e.printStackTrace();
            Logging.Logger.logError("callendar initialization failed");
        }
    }

    public void save(){
        callendarMeta.getDocumentElement().getChildNodes().item(0).setTextContent(this.name);
        callendarMeta.getDocumentElement().getChildNodes().item(1).setTextContent(this.comment);
    }
}
