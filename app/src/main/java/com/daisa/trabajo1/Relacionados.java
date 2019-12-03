package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class Relacionados extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        new TiendaFragment(tienda);

        new PlataformaFragment(pc, xbox, playStation, sw);

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
}
