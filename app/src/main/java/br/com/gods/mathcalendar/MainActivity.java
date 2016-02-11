package br.com.gods.mathcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.marcohc.robotocalendar.RobotoCalendarView;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.gods.mathcalendar.utils.DateUtils;
import br.com.gods.mathcalendar.utils.LocalCache;
import br.com.gods.mathcalendar.utils.SettingsActivity;

public class MainActivity extends AppCompatActivity implements RobotoCalendarView.RobotoCalendarListener {

    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    private DateUtils dateUtils;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        act = this;

         try {
             setCalendarView();

         } catch(Exception e){
             Toast.makeText(act, act.getString(R.string.general_error), Toast.LENGTH_SHORT).show();
             act.finish();
         }

    }

    private void setCalendarView() {
        robotoCalendarView = (RobotoCalendarView) findViewById(R.id.robotoCalendarPicker);

        robotoCalendarView.setRobotoCalendarListener(this);

        dateUtils = new DateUtils();

        robotoCalendarView.markDayAsSelectedDay(dateUtils.getTime());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSelected(Date d) {

        int day = d.getDate();
        int month = d.getMonth();
        int year = d.getYear();

        if(year > dateUtils.getCurrentYear()){
            Toast.makeText(this, getString(R.string.no_access), Toast.LENGTH_LONG).show();
            return;
        } else {
            if(month > dateUtils.getCurrentMonth()) {
                Toast.makeText(this, getString(R.string.no_access), Toast.LENGTH_LONG).show();
                return;
            } else {
                if (day > dateUtils.getCurrentDay()) {
                    Toast.makeText(this, getString(R.string.no_access), Toast.LENGTH_LONG).show();
                    return;
                }
            }

        }

        LocalCache.getInstance().setDay(day);
        LocalCache.getInstance().setMonth(month);

        String actualYear = "" + year;

        actualYear = 20 + actualYear.substring(1,3);

        month++;

        String date = "";
        String url = "";

        if (month < 10) {
            if (day < 10) {
                date = "0" + day + "/0" + month + "/" + actualYear;
                url = "http://ap.imagensbrasil.org/images/problema-" + "0" + month + "-" + "0" + day;
            } else {
                date = day + "/0" + month + "/" + actualYear;
                url = "http://ap.imagensbrasil.org/images/problema-" + "0" + month + "-" + day;
            }
        } else {
            if (day < 10) {
                date = "0" + day + "/" + month + "/" + actualYear;
                url = "http://ap.imagensbrasil.org/images/problema-" + month + "-" + "0" + day;
            } else {
                date = day + "/" + month + "/" + actualYear;
                url = "http://ap.imagensbrasil.org/images/problema-" + month + "-" + day;
            }
        }

        url += ".png";

        System.out.println(url);

        Picasso.with(this).load(url).fetch(new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                //Success image already loaded into the view
            }

            @Override
            public void onError() {
                //Error placeholder image already loaded into the view, do further handling of this situation here

                Toast.makeText(act, act.getString(R.string.error_load_image), Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(act, ProblemActivity.class);
        intent.putExtra("URL", url);
        intent.putExtra("DATE", date);

        startActivity(intent);

    }

    @Override
    public void onRightButtonClick() {
        currentMonthIndex++;
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        currentMonthIndex--;
        updateCalendar();
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
    }

}
