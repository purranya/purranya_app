package data;

import application.App;
import application.Logging;
import models.Calendar;
import models.Label;
import models.Note;
import org.joda.time.DateTime;
import org.omg.CORBA.Object;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private Calendar c;

    public CalendarManager(){ }

    public Calendar getCalendar_DEBUG() { return c; }

    public String getCalendarName() { return c.name; }
    public String getCalendarComment() { return c.comment; }

    public List<Note> getAllNotes() { return c.notes; }
    public List<Label> getAllLabels() { return c.labels; }

    /** wczytuje kalendarz z pliku o podanej nazwie */
    public boolean loadCalendar(String name)
    {
        try {
            FileInputStream fip = new FileInputStream(App.homeFolderManager.getPath("databases") + name);
            ObjectInputStream o = new ObjectInputStream(fip);
            c = (Calendar) o.readObject();
            fip.close();
            return true;
        } catch ( Exception e)
        {
            Logging.Logger.logError("Loading calendar failed");
            e.printStackTrace();
            return false;
        }
    }

    public String[] getCalendarIndex()
    {
        String[] files = App.homeFolderManager.getListOfFiles("databases");

        try {
            for (String s : files)
                if (!s.matches("^[a-zA-Z0-9][a-zA-Z0-9 -_]{0,31}"))
                    throw new Exception("Database folder contaminated, please delete any non-database files");
        } catch (Exception e) {
            Logging.Logger.logError(e.getMessage());
            e.printStackTrace();
        }
        return files;
    }

    /** zapisuje aktualnie wczytany kalendarz */
    public boolean saveCalendar()
    {
        if(!isCalendarLoaded())
        {
            System.err.println("No calendar");
            Logging.Logger.logError("No calendar");
        }
        ObjectOutputStream o = null;
        try {
            if(!isCalendarLoaded()) throw new Exception("calendar is null");
            o = new ObjectOutputStream(new FileOutputStream(App.homeFolderManager.getPath("databases") + c.name));
            o.writeObject(c);
            o.flush();
            o.close();
            return true;
        } catch ( Exception e) {
            Logging.Logger.logError("Saving calendar failed");
            e.printStackTrace();
            return false;
        }
    }

    /** zapisuje dowolny wczytany kalendarz */
    public boolean saveCalendar(models.Calendar calendar)
    {

        try {
            FileOutputStream fop = new FileOutputStream(App.homeFolderManager.getPath("databases") + calendar.name);
            ObjectOutputStream o = new ObjectOutputStream(fop);
            o.writeObject(calendar);
            o.flush();
            fop.close();
            o.close();
            return true;
        } catch ( Exception e) {
            Logging.Logger.logError("Saving calendar failed");
            e.printStackTrace();
            return false;
        }
    }

    /** zmienia nazwę dowolnego kalendarza */
    public boolean renameCalendar(String oldName,String newName)
    {
        File f = new File(App.homeFolderManager.getPath("databases") + oldName);
        File f2 = new File(App.homeFolderManager.getPath("databases") + newName);
        if(f.exists() && !f2.exists() && newName.matches("^[a-zA-Z0-9][a-zA-Z0-9 -_]{0,31}")) {
            if (f.renameTo(f2)) {
                return true;
            } else {
                System.err.println("Rename failed");
                Logging.Logger.logError("Rename failed");
                return false;
            }
        }
        else
        {
            System.err.println("Invalid name for calendar");
            Logging.Logger.logError("Invalid name for calendar");
            return false;
        }
    }

    /** zmienia nazwę aktualnego kalendarza */
    public boolean renameCalendar(String newName)
    {
        if(!isCalendarLoaded())
        {
            System.err.println("No calendar");
            Logging.Logger.logError("No calendar");
        }
        Path f = Paths.get(App.homeFolderManager.getPath("databases") + c.name);
        Path f2 = Paths.get(App.homeFolderManager.getPath("databases") + newName);
        if(newName.matches("^[a-zA-Z0-9][a-zA-Z0-9 -_]{0,31}")) {
            try {
                Files.move(f, f2);
                loadCalendar(newName);
                c.name = newName;
                saveCalendar();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Rename failed");
                Logging.Logger.logError("Rename failed");
                return false;
            }
        }
        else
        {
            System.err.println("Invalid name for calendar");
            Logging.Logger.logError("Invalid name for calendar");
            return false;
        }
    }

    public boolean deleteCalendar()
    {
        if(!isCalendarLoaded())
        {
            System.err.println("No calendar");
            Logging.Logger.logError("No calendar");
        }
        File f = new File(App.homeFolderManager.getPath("databases") + c.name);
        if(f.delete())
        {
            c = null;
            return true;
        }
        else
        {
            System.err.println("Deletion failed");
            Logging.Logger.logError("Deletion failed");
            return false;
        }
    }

    public boolean deleteCalendar(String name)
    {
        File f = new File(App.homeFolderManager.getPath("databases") + name);
        if(f.delete())
        {
            return true;
        }
        else
        {
            System.err.println("Deletion failed");
            Logging.Logger.logError("Deletion failed");
            return false;
        }
    }

    /** zwraca informację o tym, czy wczytano jakiś kalendarz */
    public boolean isCalendarLoaded()
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

        return getLabelById(0);
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

    public Label getLabelByText(String text)
    {
        for(Label l : c.labels)
            if(l.text.equals(text)) return l;

        return getLabelByText("");
    }

    public List<String> getLabelList() {
        ArrayList<String> result = new ArrayList<>();

        for (Label l : c.labels)
            result.add(l.text);

        return result;
    }
}
