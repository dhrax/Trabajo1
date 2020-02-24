package com.daisa.trabajo1.preferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.daisa.trabajo1.R;
import com.daisa.trabajo1.activitiesPrinc.MainActivity;

import java.util.List;


public class Preferencias extends AppCompatActivity
{
    public static Switch myswitch;
    public static boolean active;
    SharedPref sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        active = true;
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()) {
            setTheme(R.style.darktheme);
        }
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        myswitch=(Switch)findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState()) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(), Preferencias.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder.create(getApplicationContext())
                .addNextIntent(i)
                .startActivities();
    }
}
