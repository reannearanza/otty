package com.greenteam.otty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    int profile_id;
    String monthupdated;
    String dayupdated;
    int intDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_calendar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);
        profile_id = getIntent().getExtras().getInt("ChildID");

        calendarView.setMaxDate(System.currentTimeMillis());


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                i1 = i1 + 1;

                if (i1 < 10){
                    monthupdated = "0" + Integer.toString(i1);
                }
                else{
                    monthupdated = Integer.toString(i1);
                }

                if (i2 < 10){
                    dayupdated = "0" + Integer.toString(i2);
                }
                else{
                    dayupdated = Integer.toString(i2);
                }

                String selectedDate = i +  monthupdated + dayupdated;
                intDate = Integer.parseInt(selectedDate);
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startIntent.putExtra("selectedDate", intDate);
                startIntent.putExtra("ChildID", profile_id);
                startActivity(startIntent);
            }
        });

    }
}
