package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Relacionados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacionados);

        ViewPager view = findViewById(R.id.view);
        view.setAdapter(new ListaVideojuegosAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(view);

        Intent intent = getIntent();
        String tienda = intent.getStringExtra("tienda");
// set Fragmentclass Arguments
        TiendaFragment inteTienda = new TiendaFragment(tienda);

    }




}
