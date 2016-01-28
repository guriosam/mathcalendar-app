package br.com.gods.mathcalendar.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Caio on 26/01/2016.
 */
public class DateUtils {

    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private Date time;

    public DateUtils(){

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        Calendar currentCal = Calendar.getInstance();
        String currentDate = dateFormat.format(currentCal.getTime());

        this.currentDay = Integer.parseInt(currentDate.substring(0, currentDate.indexOf("/")));
        this.currentMonth = Integer.parseInt(currentDate.substring(currentDate.indexOf("/") + 1, currentDate.lastIndexOf("/")));
        this.currentYear = Integer.parseInt(currentDate.substring(currentDate.lastIndexOf("/") + 1, currentDate.length()));
        this.time = currentCal.getTime();

    }

    public String getCurrentDate(){

        String date = currentDay + "/" + currentMonth + "/" + currentYear;

        return date;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public Date getTime(){
        return time;
    }
}
