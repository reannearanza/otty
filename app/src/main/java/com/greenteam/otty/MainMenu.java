package com.greenteam.otty;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {

    String name;
    int profile_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button btn_NewEnt = (Button)findViewById(R.id.btn_NewEnt);
        Button btn_Summ = (Button)findViewById(R.id.btn_Summ);
        Button btn_AbtAut = (Button)findViewById(R.id.btn_AbtAut);
        TextView txt_name = (TextView)findViewById(R.id.txt_name);

        name = getIntent().getExtras().getString("ChildName");
        txt_name.setText(name);
        profile_id = getIntent().getExtras().getInt("ChildID");


        //NEW ENTRY
        btn_NewEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewEnt = new Intent(getApplicationContext(), Calendar.class);
                startNewEnt.putExtra("ChildID", profile_id);
                startActivity(startNewEnt);
            }
        });

        //SUMMARY
        btn_Summ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewEnt = new Intent(getApplicationContext(), DataSummary.class);
                startNewEnt.putExtra("ChildID", profile_id);
                startActivity(startNewEnt);
            }
        });

        //ABOUT AUTISM
        btn_AbtAut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewEnt = new Intent(getApplicationContext(), helpActivity.class);
                startActivity(startNewEnt);
            }
        });
    }
}
