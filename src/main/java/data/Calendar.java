package data;

import application.GlobalOptions;
import application.Logging;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Calendar {

    public File calendarMetaFile;
    public File calendarNotesFile;

    public Document calendarMeta;
    public Document calendarNotes;

    public String name;
    public String comment;

    public ArrayList<Note> notes;
    public ArrayList<Label> labels;

    public int nextLabelId;
    public int nextNoteId;

    public Calendar() { this.notes = new ArrayList<>(); this.labels = new ArrayList<>(); }

    public Calendar(File meta, File notes) {
        this();

        this.calendarMetaFile = meta;
        this.calendarNotesFile = notes;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            this.calendarMeta = dBuilder.parse(calendarMetaFile);
            this.calendarNotes = dBuilder.parse(calendarNotesFile);

        } catch ( Exception e ) {
            e.printStackTrace();
            Logging.Logger.logError("callendar initialization failed");
        }

        //kalendarz
        this.name = this.calendarMeta.getDocumentElement().getChildNodes().item(0).getTextContent();
        this.comment = this.calendarMeta.getDocumentElement().getChildNodes().item(1).getTextContent();

        //etykiety
        Node labelsTree = this.calendarMeta.getDocumentElement().getChildNodes().item(2);
        this.nextLabelId = Integer.parseInt(labelsTree.getChildNodes().item(0).getTextContent());

        Node idNode;
        Node textNode;
        for(int i=1;i<labelsTree.getChildNodes().getLength();i++) {
            idNode = labelsTree.getChildNodes().item(i).getChildNodes().item(0);
            textNode = labelsTree.getChildNodes().item(i).getChildNodes().item(1);
            this.labels.add(new Label(Integer.parseInt(idNode.getTextContent()),textNode.getTextContent()));
        }

        //notatki
        DateTimeFormatter fmt = DateTimeFormat.forPattern(GlobalOptions.dateFormat);

        this.nextNoteId = Integer.parseInt(calendarNotes.getDocumentElement().getChildNodes().item(0).getTextContent());
        for(int i=1;i<calendarNotes.getDocumentElement().getChildNodes().getLength();i++) {

            DateTime st,end;
            String st1,st2;
            st1 = calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(3).getTextContent();
            st2 = calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(4).getTextContent();
            st = st1.equals("null")?null:fmt.parseDateTime(st1);
            end = st2.equals("null")?null:fmt.parseDateTime(st2);

            this.notes.add(new Note(
                    Integer.parseInt(calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(0).getTextContent()),
                    calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(1).getTextContent(),
                    calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(2).getTextContent(),
                    calendarNotes.getDocumentElement().getChildNodes().item(i).getAttributes().item(0).getTextContent().equals("1"),
                    getLabelById(Integer.parseInt(calendarNotes.getDocumentElement().getChildNodes().item(i).getChildNodes().item(5).getTextContent())),
                    st,
                    end
            ));
        }
    }

    public void save(){
        calendarMeta.getDocumentElement().getChildNodes().item(0).setTextContent(this.name);
        calendarMeta.getDocumentElement().getChildNodes().item(1).setTextContent(this.comment);
    }

    Label getLabelById(int id)
    {
        for(Label l : this.labels)
            if(l.id==id) return l;

        return getLabelById(0);
    }
}
