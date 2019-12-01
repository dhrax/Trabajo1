package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

public class DetallesJuego extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_juego);

        Intent intent = getIntent();

        ImageView imgImagen = findViewById(R.id.imagenVideojuego);
        TextView txvNombre = findViewById(R.id.nombre);
        TextView txvdesarrolladora = findViewById(R.id.desarrolladora);
        TextView txvgenero = findViewById(R.id.genero);
        TextView txvanhoSalida = findViewById(R.id.anhoSalida);
        CheckBox chPC = findViewById(R.id.PC);
        CheckBox chXbox = findViewById(R.id.xbox);
        CheckBox chPlayStation = findViewById(R.id.playStation);
        CheckBox chSw = findViewById(R.id.sw);
        RatingBar rtValoracion = findViewById(R.id.valoracion);
        TextView txvTienda = findViewById(R.id.tiendaSeleccionada);
        Switch swFavorito = findViewById(R.id.favorito);

        Button btVolver = findViewById(R.id.volver);
        Button btRelacionados = findViewById(R.id.relacionados);

        //TODO poner el intent de la imagen
        //Bitmap imagen = intent.getB
        String nombre = intent.getStringExtra("nombre");
        String desarrolladora = intent.getStringExtra("desarrolladora");
        String genero = intent.getStringExtra("genero");
        String anhoSalida = intent.getStringExtra("anhoSalida");
        boolean pc = intent.getBooleanExtra("pc", false);
        boolean xbox = intent.getBooleanExtra("xbox", false);
        boolean playStation = intent.getBooleanExtra("playStation", false);
        boolean sw = intent.getBooleanExtra("sw", false);
        float valoracion = intent.getFloatExtra("valoracion", -1);
        String tienda = intent.getStringExtra("tienda");
        boolean favorito = intent.getBooleanExtra("favorito", false);

        //imagen.setImageBitMap();
        txvNombre.setText(nombre);
        txvdesarrolladora.setText(desarrolladora);
        txvgenero.setText(genero);
        txvanhoSalida.setText(String.valueOf(anhoSalida));
        chPC.setChecked(pc);
        chXbox.setChecked(xbox);
        chPlayStation.setChecked(playStation);
        chSw.setChecked(sw);
        rtValoracion.setRating(valoracion);
        txvTienda.setText(tienda);
        swFavorito.setChecked(favorito);

        btVolver.setOnClickListener(this);
        btRelacionados.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btVolver:
                onBackPressed();
                break;

            case R.id.relacionados:
                break;

        }
    }
}
