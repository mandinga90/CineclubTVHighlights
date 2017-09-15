package example.cineclubtvhighlightsjson.functional;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by mlu on 06.05.2017.
 */

public class DateTimeHelper {

    public static final Date EMPTY_DATE = parseJSONDateTime("1970-01-01T00:00:00.000Z");

    public static Date parseJSONDateTime(String dateString) {
        if (dateString == null) return null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
//        fmt.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        if (dateString.contains("T")) dateString = dateString.replace('T', ' ');
        if (dateString.contains("Z")) dateString = dateString.replace(".000", "");
        if (dateString.contains("Z")) dateString = dateString.replace("Z", "+0200");
        else
            dateString = dateString.substring(0, dateString.lastIndexOf(':')) + dateString.substring(dateString.lastIndexOf(':')+1);
        try {
            return fmt.parse(dateString);
        }
        catch (ParseException e) {
            Log.e(DateTimeHelper.class.getSimpleName(), "Could not parse JSON datetime: " + dateString);
            return null;
        }
    }
    public static boolean isToday(Calendar date){
        Calendar today = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+2"));
        return (    date.get( Calendar.YEAR ) == today.get( Calendar.YEAR )
                 && date.get( Calendar.MONTH ) == today.get( Calendar.MONTH )
                 && date.get( Calendar.DAY_OF_MONTH ) == today.get( Calendar.DAY_OF_MONTH ) );
    }

    public static boolean isEmptyDate(Date date) {
        return date.compareTo( DateTimeHelper.EMPTY_DATE ) <= 0;
    }
}
