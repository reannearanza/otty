package com.greenteam.otty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import java.util.Calendar;

public class splashscreen extends AppCompatActivity {

    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_splashscreen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mySuperIntent = new Intent(getApplicationContext(), select_profile.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 18);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Intent intent1 = new Intent(splashscreen.this, AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(splashscreen.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager am = (AlarmManager) splashscreen.this.getSystemService(splashscreen.this.ALARM_SERVICE);
    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
