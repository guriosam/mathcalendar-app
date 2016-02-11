package br.com.gods.mathcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio on 20/01/2016.
 */
public class MyCalendar {

    private int day;
    private int month;

    //

    private List<String> jan;
    private List<String> feb;
    private List<String> mar;
    private List<String> apr;
    private List<String> may;
    private List<String> jun;
    private List<String> jul;
    private List<String> aug;
    private List<String> sep;
    private List<String> oct;
    private List<String> nov;
    private List<String> dec;


    public MyCalendar(int day, int month) {
        this.day = day;
        this.month = month;

        initiateJanuary();
        initiateFebruary();
        initiateMarch();
        initiateApril();
        initiateMay();
        initiateJune();
        initiateJuly();
        initiateAugust();
        initiateSeptember();
        initiateOctober();
        initiateNovember();
        initiateDecember();

    }

    public String getDailyImage(){


        day--;

        month = 1;
        day = 1;

        switch(month){

            case 1:
                if(day < jan.size()) {
                    System.out.println(day);
                    System.out.println(jan.get(day));
                    return jan.get(day);
                } else {
                  return Integer.toString(R.string.no_access);
                }
            case 2:
                if(day < feb.size()) {
                    return feb.get(day);
                } else {
                    return Integer.toString(R.string.no_access);
                }
            case 3:
                return mar.get(day);
            case 4:
                return apr.get(day);
            case 5:
                return may.get(day);
            case 6:
                return jun.get(day);
            case 7:
                return jul.get(day);
            case 8:
                return aug.get(day);
            case 9:
                return sep.get(day);
            case 10:
                return oct.get(day);
            case 11:
                return nov.get(day);
            case 12:
                return dec.get(day);
        }

        return "";
    }

    public void initiateJanuary(){

        jan = new ArrayList<String>();

        jan.add("http://2.bp.blogspot.com/_OfYYlOGGnDk/TKHs_WCjl3I/AAAAAAAAByg/JvFTEHBkgkU/s1600/pastel.jpg");

        jan.add("http://www.nestle.com.br/site/images/cozinha/receitas/Pudim_de_leite_moca.jpg");

    }

    public void initiateFebruary(){

        feb = new ArrayList<String>();

        feb.add("");

    }

    public void initiateMarch(){

        mar = new ArrayList<String>();

        mar.add("");

    }

    public void initiateApril(){

        apr = new ArrayList<String>();

        apr.add("");
    }

    public void initiateMay(){

        may = new ArrayList<String>();

        may.add("");
    }

    public void initiateJune(){

        jun = new ArrayList<String>();

        jun.add("");
    }

    public void initiateJuly(){

        jul = new ArrayList<String>();

        jul.add("");
    }

    public void initiateAugust(){

        aug = new ArrayList<String>();

        aug.add("");
    }

    public void initiateSeptember(){

        sep = new ArrayList<String>();

        sep.add("");
    }

    public void initiateOctober(){

        oct = new ArrayList<String>();

        oct.add("");
    }

    public void initiateNovember(){

        nov = new ArrayList<String>();

        nov.add("");
    }

    public void initiateDecember(){

        dec = new ArrayList<String>();

        dec.add("");
    }


}

