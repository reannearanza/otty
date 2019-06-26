package com.greenteam.otty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DataSummary extends AppCompatActivity {

    private static final String TAG = "DataSummary";
    private DatePickerDialog.OnDateSetListener msDateSetListener;
    private DatePickerDialog.OnDateSetListener meDateSetListener;
    String enddate;
    String startdate;
    String ssdate;
    String sedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_data_summary);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button btn_start = (Button)findViewById(R.id.btn_startsumm);
        final TextView startingDate = (TextView)findViewById(R.id.startdate);
        final TextView endingDate = (TextView)findViewById(R.id.enddate);



        startingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DataSummary.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        msDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        msDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String monthupdated;
                String dayupdated;

                if (month < 10){
                    monthupdated = "0" + Integer.toString(month);
                }
                else{
                    monthupdated = Integer.toString(month);
                }

                if (day < 10){
                    dayupdated = "0" + Integer.toString(day);
                }
                else{
                    dayupdated = Integer.toString(day);
                }

                startdate = Integer.toString(year) +  monthupdated + dayupdated;
                ssdate = monthupdated + "/" + dayupdated + "/" + year;
                startingDate.setText(ssdate);

            }
        };

        endingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DataSummary.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        meDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        meDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String monthupdated;
                String dayupdated;

                if (month < 10){
                    monthupdated = "0" + Integer.toString(month);
                }
                else{
                    monthupdated = Integer.toString(month);
                }

                if (day < 10){
                    dayupdated = "0" + Integer.toString(day);
                }
                else{
                    dayupdated = Integer.toString(day);
                }

                enddate = Integer.toString(year) +  monthupdated + dayupdated;

                sedate = monthupdated + "/" + dayupdated + "/" + year;
                endingDate.setText(sedate);

            }
        };

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sDate = startingDate.getText().toString();
                String eDate = endingDate.getText().toString();


                if (sDate.equals("Starting Date") || eDate.equals("Ending Date")){
                    Toast.makeText(getApplicationContext(), "Please provide both dates.", Toast.LENGTH_LONG).show();
                }

                else {
                    if  (Integer.parseInt(enddate) < Integer.parseInt(startdate)) {
                        Toast.makeText(getApplicationContext(), "Wrong input.", Toast.LENGTH_LONG).show();
                    } else {

                        int profile_id = getIntent().getExtras().getInt("ChildID");
                        Intent startNewEnt = new Intent(getApplicationContext(), com.greenteam.otty.datacharts.class);
                        startNewEnt.putExtra("startDate", Integer.parseInt(startdate));
                        startNewEnt.putExtra("endDate", Integer.parseInt(enddate));
                        startNewEnt.putExtra("ssdate", ssdate);
                        startNewEnt.putExtra("sedate", sedate);
                        startNewEnt.putExtra("ChildID", profile_id);
                        startActivity(startNewEnt);
                    }
                }

            }
        });

    }
}
