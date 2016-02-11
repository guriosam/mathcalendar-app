package br.com.gods.mathcalendar.utils;

/**
 * Created by Caio on 11/02/2016.
 */
public class LocalCache {

    private static LocalCache instance = null;

    private boolean isVip = false;

    private int day = 1;

    private int month = 1;

    public static LocalCache getInstance() {
        if (instance == null) {
            instance = new LocalCache();
        }

        return instance;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
