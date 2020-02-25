package com.daisa.trabajo1.activitiesPrinc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.daisa.trabajo1.R;
import com.daisa.trabajo1.adapter.OpinionAdapter;
import com.daisa.trabajo1.objeto.Opinion;
import com.daisa.trabajo1.tarea.TareaDescargaDatos;
import com.daisa.trabajo1.util.Constantes;

import java.util.ArrayList;

public class ListaOpiniones extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Opinion> opiniones = new ArrayList<>();
    public static OpinionAdapter adaptador;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if(pref.getBoolean(getString(R.string.usarPreferencias), false))
            if(pref.getBoolean(getString(R.string.darkMode), false))
                setTheme(R.style.darktheme);
            else
                setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_lista_opiniones);

        ListView lvOpinion = findViewById(R.id.lvOpiniones);
        adaptador = new OpinionAdapter(this, opiniones);
        lvOpinion.setAdapter(adaptador);


        Button back = findViewById(R.id.back);
        back.setOnClickListener(this);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
    }

    @Override
    protected void onResume() {
        super.onResume();

        opiniones.clear();

        adaptador.notifyDataSetChanged();

        cargarListaOpiniones();

    }

    private void cargarListaOpiniones() {

        TareaDescargaDatos tarea = new TareaDescargaDatos(this, opiniones, "Lista Opiniones");
        tarea.execute(Constantes.URL+"opinionesVideojuego?videojuego="+nombre);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back:
                onBackPressed();
        }
    }
}
