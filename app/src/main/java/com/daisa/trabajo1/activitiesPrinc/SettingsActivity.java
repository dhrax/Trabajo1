package com.daisa.trabajo1.activitiesPrinc;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceManager;

import com.daisa.trabajo1.R;

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if(pref.getBoolean(getString(R.string.usarPreferencias), false))
            if(pref.getBoolean(getString(R.string.darkMode), false))
                setTheme(R.style.darktheme);
            else
                setTheme(R.style.AppTheme);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder.create(getApplicationContext())
                .addNextIntent(i)
                .startActivities();
    }
}
