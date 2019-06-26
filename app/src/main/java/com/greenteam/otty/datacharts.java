package com.greenteam.otty;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class datacharts extends AppCompatActivity {
    int sdate;
    int edate;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_datacharts);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        sdate = getIntent().getExtras().getInt("startDate");
        edate = getIntent().getExtras().getInt("endDate");

        String ssdate = getIntent().getExtras().getString("ssdate");
        String sedate = getIntent().getExtras().getString("sedate");
        int profile_id = getIntent().getExtras().getInt("ChildID");


        ProgressBar p_tantrums = (ProgressBar) findViewById(R.id.progressBar_tantrums);
        ProgressBar p_sib = (ProgressBar) findViewById(R.id.progressBar_sib);
        ProgressBar p_ssb = (ProgressBar) findViewById(R.id.progressBar_ssb);
        ProgressBar p_hl = (ProgressBar) findViewById(R.id.progressBar_hl);
        ProgressBar p_pw = (ProgressBar) findViewById(R.id.progressBar_pw);

        try {
            Cursor analysis = db.getAnalyis(edate, sdate, profile_id);
            if (analysis != null && analysis.getCount() > 0) {
                analysis.moveToNext();

                float tantrums = (analysis.getInt(0) + analysis.getInt(1) + analysis.getInt(2) + analysis.getInt(3)) / analysis.getInt(20);
                tantrums = tantrums / 4;
                tantrums = tantrums * 100;

                float sib = (analysis.getInt(4) + analysis.getInt(5) + analysis.getInt(6) + analysis.getInt(7)) / analysis.getInt(20);
                sib = sib / 4;
                sib = sib * 100;

                float ssb = (analysis.getInt(8) + analysis.getInt(9) + analysis.getInt(10) + analysis.getInt(11)) / analysis.getInt(20);
                ssb = ssb / 4;
                ssb = ssb * 100;

                float hl = (analysis.getInt(12) + analysis.getInt(13) + analysis.getInt(14) + analysis.getInt(15)) / analysis.getInt(20);
                hl = hl / 4;
                hl = hl * 100;

                float pw = (analysis.getInt(16) + analysis.getInt(17) + analysis.getInt(18) + analysis.getInt(19)) / analysis.getInt(20);
                pw = pw / 4;
                pw = pw * 100;


                if (Math.round(tantrums) >= 75) {
                    p_tantrums.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_severe));
                } else if (Math.round(tantrums) >= 51 && Math.round(tantrums) <= 74) {
                    p_tantrums.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar));
                } else if (Math.round(tantrums) >= 26 && Math.round(tantrums) <= 50) {
                    p_tantrums.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_mild));
                } else if (Math.round(tantrums) >= 0 && Math.round(tantrums) <= 25) {
                    p_tantrums.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
                }

                if (Math.round(sib) >= 75) {
                    p_sib.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_severe));
                } else if (Math.round(sib) >= 51 && Math.round(sib) <= 74) {
                    p_sib.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar));
                } else if (Math.round(sib) >= 26 && Math.round(sib) <= 50) {
                    p_sib.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_mild));
                } else if (Math.round(sib) >= 0 && Math.round(sib) <= 25) {
                    p_sib.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
                }

                if (Math.round(ssb) >= 75) {
                    p_ssb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_severe));
                } else if (Math.round(ssb) >= 51 && Math.round(ssb) <= 74) {
                    p_ssb.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar));
                } else if (Math.round(ssb) >= 26 && Math.round(ssb) <= 50) {
                    p_ssb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_mild));
                } else if (Math.round(ssb) >= 0 && Math.round(ssb) <= 25) {
                    p_ssb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
                }

                if (Math.round(hl) >= 75) {
                    p_hl.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_severe));
                } else if (Math.round(hl) >= 51 && Math.round(hl) <= 74) {
                    p_hl.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar));
                } else if (Math.round(hl) >= 26 && Math.round(hl) <= 50) {
                    p_hl.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_mild));
                } else if (Math.round(hl) >= 0 && Math.round(hl) <= 25) {
                    p_hl.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
                }

                if (Math.round(pw) >= 75) {
                    p_pw.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_severe));
                } else if (Math.round(pw) >= 51 && Math.round(pw) <= 74) {
                    p_pw.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progressbar));
                } else if (Math.round(pw) >= 26 && Math.round(pw) <= 50) {
                    p_pw.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_mild));
                } else if (Math.round(pw) >= 0 && Math.round(pw) <= 25) {
                    p_pw.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
                }


                p_tantrums.setProgress(Math.round(tantrums));
                p_sib.setProgress(Math.round(sib));
                p_ssb.setProgress(Math.round(ssb));
                p_hl.setProgress(Math.round(hl));
                p_pw.setProgress(Math.round(pw));

                TextView total_entries = (TextView) findViewById(R.id.textView36);
                total_entries.setText("The total number of entry/ies from " + ssdate + " to " + sedate + " is: " + Integer.toString(analysis.getInt(20)));

            }
        }
        catch(Exception e){

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(datacharts.this);
            View mView = getLayoutInflater().inflate(R.layout.noentry_dialog, null);
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

            TextView total_entries = (TextView) findViewById(R.id.textView36);
            total_entries.setText("You have no entries on the selected dates.");

            p_tantrums.setProgress(0);
            p_sib.setProgress(0);
            p_ssb.setProgress(0);
            p_hl.setProgress(0);
            p_pw.setProgress(0);

            p_tantrums.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
            p_sib.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
            p_ssb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
            p_hl.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));
            p_pw.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_normal));

        }


    }
}
