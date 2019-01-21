package data_util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.time.LocalDate;

public class DateUtils {

    /**
     * Convert {@link java.time.LocalDate} to {@link org.joda.time.DateTime}
     */
    public static DateTime toDateTime(LocalDate localDate) {
        return new DateTime(DateTimeZone.UTC).withDate(
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()
        ).withTime(0, 0, 0, 0);
    }

    /**
     * Convert {@link org.joda.time.DateTime} to {@link java.time.LocalDate}
     */
    public static LocalDate toLocalDate(DateTime dateTime) {
        DateTime dateTimeUtc = dateTime.withZone(DateTimeZone.UTC);
        return LocalDate.of(dateTimeUtc.getYear(), dateTimeUtc.getMonthOfYear(), dateTimeUtc.getDayOfMonth());
    }

    public static String getMonthName(int dayOfMonth)
    {
        switch(dayOfMonth) {
            case 1:
                return "Styczeń";
            case 2:
                return "Luty";
            case 3:
                return "Marzec";
            case 4:
                return "Kwiecień";
            case 5:
                return "Maj";
            case 6:
                return "Czerwiec";
            case 7:
                return "Lipiec";
            case 8:
                return "Sierpień";
            case 9:
                return "Wrzesień";
            case 10:
                return "Październik";
            case 11:
                return "Listopad";
            case 12:
                return "Grudzień";
            default:
                return null;
        }
    }

    public static boolean dateEquals(DateTime d1, DateTime d2)
    {
        return d1.year().get() == d2.year().get() && d1.monthOfYear().get() == d2.monthOfYear().get() && d1.dayOfMonth().get() == d2.dayOfMonth().get();
    }
}