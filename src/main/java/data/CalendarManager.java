package data;

import application.App;
import application.Logging;
import models.Calendar;
import models.Label;
import models.Note;
import org.joda.time.DateTime;

import java.io.*;
import java.util.List;

public class CalendarManager {
    private Calendar c;

    public CalendarManager(){ }

    public Calendar getCalendar_DEBUG() { return c; }

    public String getCalendarName() { return c.name; }
    public String getCalendarComment() { return c.comment; }

    public List<Note> getAllNotes() { return c.notes; }
    public List<Label> getAllLabels() { return c.labels; }

    public boolean loadCallendar(String name)
            //wczytuje kalendarz z pliku o podanej nazwie
    {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(App.homeFolderManager.getPath("databases") + name));
            c = (Calendar) o.readObject();
            return true;
        } catch ( Exception e)
        {
            Logging.Logger.logError("Loading calendar failed");
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveCalendar()
            //zapisuje aktualnie wczytany kalendarz
    {
        ObjectOutputStream o = null;
        try {
            if(!isCalendarLoaded()) throw new Exception("calendar is null");
            o = new ObjectOutputStream(new FileOutputStream(App.homeFolderManager.getPath("databases") + c.name));
            o.writeObject(c);
            o.flush();
            return true;
        } catch ( Exception e) {
            Logging.Logger.logError("Saving calendar failed");
            e.printStackTrace();
            return false;
        }
    }

    public void createCalendar(String name) { createCalendar(name,""); }

    public void renameCalendar(String newName) {
        File f = new File(App.homeFolderManager.getPath("databases") + c.name);
        System.out.println(f.exists());
        System.out.println(f.delete());

        c.name=newName;
        saveCalendar();
    }

    public void createCalendar(String name, String comment)
            //tworzy nowy, pusty kalendarz
    {
        c = new Calendar(name,comment);

    }

    public boolean isCalendarLoaded()
            //zwraca informacje o tym, czy wczytano jakiś kalendarz
    {
        return c!=null;
    }

    public void addNote(Note n) {
        n.id=this.c.nextNoteId;
        this.c.nextNoteId++;
        this.c.notes.add(n);
    }

    public void addNote(String title, String content, boolean isArchived, int label, DateTime startDate, DateTime endDate)
    {
        addNote(new Note(title,content,isArchived,getLabelById(label),startDate,endDate));
    }

    public void addLabel(Label l)
    {
        l.id=this.c.nextLabelId;
        this.c.nextLabelId++;
        this.c.labels.add(l);
    }

    public void addLabel(String text)
    {
        addLabel(new Label(text));
    }

    public Label getLabelById(int id)
    {
        for(Label l : c.labels)
            if(l.id==id) return l;

        return null;
    }

    public Note getNoteById(int id)
    {
        for(Note n : c.notes)
            if(n.id==id) return n;

        return null;
    }

    public boolean deleteLabelById(int id)
    {
        Label l = getLabelById(id);
        if(l!=null)
            this.c.labels.remove(l);
        else
            return false;
        return true;
    }

    public boolean deleteNoteById(int id)
    {
        Note n = getNoteById(id);
        if(n!=null)
            this.c.notes.remove(n);
        else
            return false;
        return true;
    }
}
