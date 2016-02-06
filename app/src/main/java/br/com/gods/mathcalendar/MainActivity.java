package br.com.gods.mathcalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.marcohc.robotocalendar.RobotoCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.gods.mathcalendar.notifications.ScheduleNotification;
import br.com.gods.mathcalendar.utils.DateUtils;
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

        setCalendarView();

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

        String actualYear = "" + year;

        actualYear = 20 + actualYear.substring(1,3);

        month++;


        MyCalendar myCalendar = new MyCalendar(day, month);
        String date = "";



        if (month < 10) {
            if (day < 10) {
                date = "0" + day + "/0" + month + "/" + actualYear;
            } else {
                date = day + "/0" + month + "/" + actualYear;
            }
        } else {
            if (day < 10) {
                date = "0" + day + "/" + month + "/" + actualYear;
            } else {
                date = day + "/" + month + "/" + actualYear;
            }
        }

        String url = myCalendar.getDailyImage();

        Intent intent = new Intent(act, ProblemActivity.class);
        intent.putExtra("URL", url);
        intent.putExtra("DATE", date);

        startActivity(intent);

        // Mark that day with random colors
       /* final Random random = new Random(System.currentTimeMillis());
        final int style = random.nextInt(3);
        switch (style) {
            case 0:
                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR, date);
                break;
            case 1:
                robotoCalendarView.markSecondUnderlineWithStyle(RobotoCalendarView.GREEN_COLOR, date);
                break;
            case 2:
                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, date);
                break;
            default:
                break;
        }
        */
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
