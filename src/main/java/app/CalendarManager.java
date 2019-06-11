package app;

import models.db_models.Calendar;
import models.db_models.Event;
import models.db_models.Label;
import models.transfer_models.UserCalendar;
import utils.DateUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class CalendarManager
{
    private static UserCalendar calendar;

    public Calendar getCalendar()
    {
        return calendar.getCalendar();
    }

    public List<Event> getAllNotes()
    {
        return calendar.getEvents();
    }

    public List<Label> getAllLabels()
    {
        return calendar.getLabels();
    }

    private Label nullLabel = new Label(null, "", 0, 0, 0, calendar.getCalendar().getId());

    public CalendarManager()
    {
        if (calendar == null)
            throw new RuntimeException("Calendar not initialized");
    }

    public static void initialize(UserCalendar calendar)
    {
        CalendarManager.calendar = calendar;
    }

    public Label getLabelById(long id)
    {
        for (Label l : calendar.getLabels())
            if (l.getId() == id) return l;

        return getLabelById(0);
    }

    public Event getEventById(long id)
    {
        for (Event e : calendar.getEvents())
            if (e.getId() == id) return e;

        return null;
    }

    public List<Event> getNotesByDate(DateTime date)
    {
        ArrayList<Event> res = new ArrayList<>();
        for (Event event : calendar.getEvents())
        {
            if (event.getEndDate() == null)
            {
                if (DateUtils.dateEquals(date, event.getStartDate()))
                    res.add(event);
            } else
            {
                boolean starts = (DateUtils.dateEquals(date, event.getStartDate()));
                boolean ends = (DateUtils.dateEquals(date, event.getEndDate()));
                boolean between = event.getStartDate().isBefore(date) && event.getEndDate().isAfter(date);

                if (starts || ends || between)
                    res.add(event);
            }
        }
        res.sort((o1, o2) ->
        {
            if (o1.getStartDate().isAfter(o2.getStartDate()))
                return -1;
            else if (o1.getStartDate().isBefore(o2.getStartDate()))
                return 1;
            else
                return 0;
        });
        return res;
    }

    public Label getLabelByText(String text)
    {
        for (Label l : calendar.getLabels())
            if (l.getName().equals(text)) return l;

        return getLabelByText("");
    }

    public List<String> getLabelList()
    {
        ArrayList<String> result = new ArrayList<>();

        for (Label l : calendar.getLabels())
            result.add(l.getName());

        return result;
    }
}