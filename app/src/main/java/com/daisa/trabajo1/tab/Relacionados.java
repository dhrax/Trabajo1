package com.daisa.trabajo1.tab;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daisa.trabajo1.R;
import com.google.android.material.tabs.TabLayout;

public class Relacionados extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if(pref.getBoolean(getString(R.string.usarPreferencias), false))
            if(pref.getBoolean(getString(R.string.darkMode), false))
                setTheme(R.style.darktheme);
            else
                setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_relacionados);
        this.setTitle(R.string.relacionados);
        ViewPager view = findViewById(R.id.view);
        view.setAdapter(new ListaVideojuegosAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(view);

        Button btVolver = findViewById(R.id.volverRel);
        btVolver.setOnClickListener(this);

        Intent intent = getIntent();
        String tienda = intent.getStringExtra("tienda");

        boolean pc = intent.getBooleanExtra("pc", false);
        boolean xbox = intent.getBooleanExtra("xbox", false);
        boolean playStation = intent.getBooleanExtra("playStation", false);
        boolean sw = intent.getBooleanExtra("sw", false);

        String genero = intent.getStringExtra("genero");

        String desarrolladora = intent.getStringExtra("desarrolladora");


        new PlataformaFragment(pc, xbox, playStation, sw);


        new TiendaFragment(tienda);



        new GeneroFragment(genero);

        new DesarrolladoraFragment(desarrolladora);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.volverRel:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
