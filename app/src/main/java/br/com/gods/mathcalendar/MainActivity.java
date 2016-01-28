package br.com.gods.mathcalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements RobotoCalendarView.RobotoCalendarListener {

    private RobotoCalendarView robotoCalendarView;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
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

         createScheduledNotification();
        //createNotification();
        //setAlarm();
         act = this;

        setCalendarView();


    }

    private void createNotification() {



        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(getApplicationContext().getString(R.string.notification_problem_title));
        mBuilder.setContentText(this.getString(R.string.notification_problem_detail));

        Intent resultIntent = new Intent(this, ProblemActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ProblemActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        mNotificationManager.notify(2, mBuilder.build());

    }

    private void createScheduledNotification()
    {
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 24);
        int interval = 1000 * 60 * 60 * 24;

        Intent myIntent = new Intent(this, ScheduleNotification .class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);*/

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        System.out.println(calendar.getTime());
        System.out.println(System.currentTimeMillis());
        //calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 50);
        //calendar.set(Calendar.SECOND, 15);
        int interval = 1000 * 60 * 60 * 24;

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        System.out.println(calendar.getTimeInMillis());
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 1000000, broadcast);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, broadcast);
        //alarmManager.setAlarmClock(, broadcast);



    }

    private void setCalendarView() {
        robotoCalendarView = (RobotoCalendarView) findViewById(R.id.robotoCalendarPicker);

        robotoCalendarView.setRobotoCalendarListener(this);

        dateUtils = new DateUtils();

        robotoCalendarView.markDayAsSelectedDay(dateUtils.getTime());

        /*System.out.println(currentDay);
        System.out.println(currentMonth);
        System.out.println(currentYear);*/

    }

    private void setAlarm(){
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ProblemActivity.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 57);
        calendar.set(Calendar.SECOND, 0);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, alarmIntent);



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
