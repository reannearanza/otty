package com.greenteam.otty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class select_profile extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    int profile_id;
    String profile_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_select_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
        }

        //POPULATE SPINNER
        final Spinner spn_profiles = (Spinner)findViewById(R.id.spn_profiles);
        ArrayList<String> list=db.getProfile();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list);
        spn_profiles.setAdapter(adapter);

        //OPEN MAIN MENU
        Button btn_Start = (Button)findViewById(R.id.btn_Start);
        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startIntent.putExtra("ChildName", profile_name);
                startIntent.putExtra("ChildID", profile_id);
                startActivity(startIntent);
            }
        });

        //CREATING A NEW PROFILE (DIALOG)
        Button btn_addprofile = (Button)findViewById(R.id.btn_AddProfile);
        btn_addprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(select_profile.this);
                View mView = getLayoutInflater().inflate(R.layout.profile_dialog, null);
                final EditText e_profilename = (EditText) mView.findViewById(R.id.e_profilename);
                Button btn_createprofile = (Button) mView.findViewById(R.id.btn_createprofile);
                Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);

                //SHOW DIALOG
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btn_createprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(e_profilename.getText().toString().isEmpty()){
                            Toast.makeText(select_profile.this, "Please enter a name.", Toast.LENGTH_SHORT).show(); }
                        else {
                            String name = e_profilename.getText().toString();
                            db.addprofile(name);
                            dialog.dismiss();
                            finish();
                            Intent intent = getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        // GET VALUES OF SELECTED ITEM IN SPINNER
        spn_profiles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                profile_id = position + 1;
                profile_name = spn_profiles.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void showStartDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(select_profile.this);
        View mView = getLayoutInflater().inflate(R.layout.profile_dialog, null);
        final EditText e_profilename = (EditText) mView.findViewById(R.id.e_profilename);
        Button btn_createprofile = (Button) mView.findViewById(R.id.btn_createprofile);
        Button btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        btn_cancel.setEnabled(false);
        //SHOW DIALOG
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btn_createprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e_profilename.getText().toString().isEmpty()){
                    Toast.makeText(select_profile.this, "Please enter a name.", Toast.LENGTH_SHORT).show(); }
                else {
                    String name = e_profilename.getText().toString();
                    db.addprofile(name);
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firstStart", false);
                    editor.apply();
                    dialog.dismiss();
                    finish();
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
