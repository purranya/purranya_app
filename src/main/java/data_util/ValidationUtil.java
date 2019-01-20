package data_util;

import java.util.List;

public class ValidationUtil {
    public static <T> boolean tableContains(T[] array, T object) {
        boolean res = false;

        for (T obj : array)
            if (obj.equals(object))
                res = true;

        return res;
    }

    public static <T> boolean listContains(List<T> list, T object) {
        boolean res = false;

        for (T obj : list)
            if (obj.equals(object))
                res = true;

        return res;
    }

    public static boolean StringLengthBetween(String text, int minimal,int maximal)
    {
        return text.length()>=minimal && text.length()<maximal;
    }
}
