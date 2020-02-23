package com.daisa.trabajo1.activitiesPrinc;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.daisa.trabajo1.R;

public class PreferenciasFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }
}
