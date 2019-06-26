package com.greenteam.otty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    DatabaseHelper db;
    private ListView listView;
    int profile_id;
    int date;
    boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_second);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView datetextView = (TextView) findViewById(R.id.datetextView);
        Button addbtn = (Button) findViewById(R.id.addbtn);
        db = new DatabaseHelper(this);

        date = getIntent().getExtras().getInt("selectedDate");
        profile_id = getIntent().getExtras().getInt("ChildID");

        seekbar();

        // SHOW RECORD
        Cursor record = db.getData(date, profile_id);
        if (record.getCount() > 0) {
            record.moveToNext();
            int tantrums_Progress = record.getInt(0);
            int sib_Progress = record.getInt(1);
            int ssb_Progress = record.getInt(2);
            int hl_Progress = record.getInt(3);
            int pw_Progress = record.getInt(4);
            String notesr = record.getString(5);

            SeekBar tantrums = (SeekBar) findViewById(R.id.seekbar_tantrums);
            SeekBar sib = (SeekBar) findViewById(R.id.seekbar_sib);
            SeekBar ssb = (SeekBar) findViewById(R.id.seekbar_ssb);
            SeekBar hl = (SeekBar) findViewById(R.id.seekbar_hl);
            SeekBar pw = (SeekBar) findViewById(R.id.seekbar_pw);

            sib.setProgress(sib_Progress - 1);
            tantrums.setProgress(tantrums_Progress - 1);
            ssb.setProgress(ssb_Progress - 1);
            hl.setProgress(hl_Progress - 1);
            pw.setProgress(pw_Progress - 1);

            EditText notes = (EditText) findViewById(R.id.notes);
            notes.setText(notesr);
        }

        // SAVE RECORD
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sampletxt = (TextView) findViewById(R.id.datetextView);
                SeekBar tantrums = (SeekBar) findViewById(R.id.seekbar_tantrums);
                SeekBar sib = (SeekBar) findViewById(R.id.seekbar_sib);
                SeekBar ssb = (SeekBar) findViewById(R.id.seekbar_ssb);
                SeekBar hl = (SeekBar) findViewById(R.id.seekbar_hl);
                SeekBar pw = (SeekBar) findViewById(R.id.seekbar_pw);
                EditText notes = (EditText) findViewById(R.id.notes);
                String notesr = notes.getText().toString();

                int tantrums_Progress = tantrums.getProgress() + 1;
                int sib_Progress = sib.getProgress() + 1;
                int ssb_Progress = ssb.getProgress() + 1;
                int hl_Progress = hl.getProgress() + 1;
                int pw_Progress = pw.getProgress() + 1;

                db.addRecord(profile_id, date, tantrums_Progress, sib_Progress, ssb_Progress, hl_Progress, pw_Progress, notesr);
                showStartDialog();
                saved = true;
            }
        });
    }

    public void seekbar() {
        SeekBar seekBar_tantrums = (SeekBar) findViewById(R.id.seekbar_tantrums);
        SeekBar seekBar_sib = (SeekBar) findViewById(R.id.seekbar_sib);
        SeekBar seekBar_ssb = (SeekBar) findViewById(R.id.seekbar_ssb);
        SeekBar seekBar_hl = (SeekBar) findViewById(R.id.seekbar_hl);
        SeekBar seekBar_pw = (SeekBar) findViewById(R.id.seekbar_pw);

        seekBar_tantrums.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String severity;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        severity = "None";
                        break;
                    case 1:
                        severity = "Mild";
                        break;
                    case 2:
                        severity = "Moderate";
                        break;
                    case 3:
                        severity = "Severe";
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_sib.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String severity;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        severity = "None";
                        break;
                    case 1:
                        severity = "Mild";
                        break;
                    case 2:
                        severity = "Moderate";
                        break;
                    case 3:
                        severity = "Severe";
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        seekBar_ssb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String severity;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        severity = "None";
                        break;
                    case 1:
                        severity = "Mild";
                        break;
                    case 2:
                        severity = "Moderate";
                        break;
                    case 3:
                        severity = "Severe";
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_hl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String severity;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        severity = "None";
                        break;
                    case 1:
                        severity = "Mild";
                        break;
                    case 2:
                        severity = "Moderate";
                        break;
                    case 3:
                        severity = "Severe";
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_pw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String severity;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        severity = "None";
                        break;
                    case 1:
                        severity = "Mild";
                        break;
                    case 2:
                        severity = "Moderate";
                        break;
                    case 3:
                        severity = "Severe";
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void showStartDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SecondActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.entry_dialog, null);
        Button btn_okay = (Button) mView.findViewById(R.id.btn_okay);

        //SHOW DIALOG
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog.dismiss();

                }

        });

    }
}
