package br.com.gods.mathcalendar.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.dd.CircularProgressButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.gods.mathcalendar.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    @InjectView(R.id.time)
    Button mTime;

    CircularProgressButton confirm;

    private DateFormat dateFormat;

    private SimpleDateFormat timeFormat;

    private Calendar calendar;
    private Calendar calendarEnd;

    private static final String TIME_PATTERN = "HH:mm:ss";

    private int hour;
    private int minute;

    private long diffMinutes;
    private long diffHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.inject(this);

        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
        update();

        mTime = (Button) findViewById(R.id.time);
        confirm = (CircularProgressButton) findViewById(R.id.confirm_button);
        confirm.setOnClickListener(this);
    }

    @OnClick(R.id.time)
    void onClickTime() {

        TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show(getFragmentManager(), "timePicker");
    }

    private void update() {
        mTime.setText(timeFormat.format(calendar.getTime()));
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        update();

        System.out.println(calendar.getTime());
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.confirm_button:

                Date time = calendar.getTime();
                hour = time.getHours();
                minute = time.getMinutes();

                System.out.println("Hour: " + hour + " Minute: " + minute);

                createScheduledNotification();

                finish();
                break;
        }

    }

    private void createScheduledNotification() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendarEnd = calendar;

        differenceBetweenDates();

        calendar.set(Calendar.HOUR, (int) diffHours);
        calendar.set(Calendar.MINUTE, (int) diffMinutes);

        System.out.println(System.currentTimeMillis());

        long hourInMilli = diffHours * 60 * 60 * 1000;
        long minuteInMilli = diffMinutes * 60 * 1000;

        long timeInMilli = System.currentTimeMillis() + hourInMilli + minuteInMilli - 60*1000;

        calendar.setTimeInMillis(System.currentTimeMillis() + hourInMilli + minuteInMilli);

        //System.out.println(calendar.getTime());
        System.out.println(timeInMilli);

        int interval = 1000 * 60 * 60;// * 24;

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMilli, interval, broadcast);

    }

    public void differenceBetweenDates(){

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = calendarEnd.getTime();
            d2 = calendar.getTime();

            long diff = d2.getTime() - d1.getTime();

            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;

            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
