package br.com.gods.mathcalendar.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import br.com.gods.mathcalendar.R;

/**
 * Created by Caio on 27/01/2016.
 */
public class ScheduleNotification extends BroadcastReceiver {


    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        long when = System.currentTimeMillis();

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.circle));

        builder.setContentTitle("Title of the notification");
        builder.setContentText("See your problem.");


        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("NADA ACONTEFCE FEIJOADA"));

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }
}
