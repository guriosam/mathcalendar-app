package br.com.gods.mathcalendar.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Caio on 24/01/2016.
 */
public class DateUtil {

    public static String getUtcTime(Calendar calendar) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        return df.format(calendar.getTime());
    }
}
