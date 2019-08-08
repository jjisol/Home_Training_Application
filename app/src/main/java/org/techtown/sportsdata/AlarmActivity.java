package org.techtown.sportsdata;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by user on 2018-11-09.
 */

public class AlarmActivity extends AppCompatActivity {
    private static int ONE_MINUTE = 5626;
    Intent in;
    int year, month, day, hour, minute;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        in = getIntent();
        //year = Integer.parseInt(in.getStringExtra("Year"));
        year = in.getIntExtra("Year",0);
        month = in.getIntExtra("Month", 0);
        day = in.getIntExtra("Day", 0);
        hour = in.getIntExtra("Hour", 0);
        minute = in.getIntExtra("Minute", 0);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlarmActivity.this, BroadcastD.class);

        PendingIntent sender = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        intent2 = new Intent(AlarmActivity.this, TabActivity.class);
        startActivity(intent2);

    }

}