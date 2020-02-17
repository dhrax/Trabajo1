package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

public class DetallesJuego extends AppCompatActivity implements View.OnClickListener {

    public Videojuego videojuego = new Videojuego();

    ImageView imgImagen;
    TextView txvNombre;
    TextView txvdesarrolladora;
    TextView txvgenero;
    TextView txvanhoSalida;
    CheckBox chPC;
    CheckBox chXbox;
    CheckBox chPlayStation;
    CheckBox chSw;
    RatingBar rtValoracion;
    TextView txvTienda;
    Switch swFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_juego);
        this.setTitle(R.string.detallesJuego);
        Intent intent = getIntent();

        imgImagen = findViewById(R.id.imagenVideojuego);
        txvNombre = findViewById(R.id.nombre);
        txvdesarrolladora = findViewById(R.id.desarrolladora);
        txvgenero = findViewById(R.id.genero);
        txvanhoSalida = findViewById(R.id.anhoSalida);
        chPC = findViewById(R.id.PC);
        chXbox = findViewById(R.id.xbox);
        chPlayStation = findViewById(R.id.playStation);
        chSw = findViewById(R.id.sw);
        rtValoracion = findViewById(R.id.valoracion);
        txvTienda = findViewById(R.id.tiendaSeleccionada);
        swFavorito = findViewById(R.id.favorito);

        Button btVolver = findViewById(R.id.btAnhadirOpinion);
        Button btRelacionados = findViewById(R.id.relacionados);

        byte[] byteArray = intent.getByteArrayExtra("imagen");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

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

        //TareaDescargaDatos tarea = new TareaDescargaDatos(this, videojuego);
        //tarea.execute(Constantes.URL+"videojuegosNombre?nombre="+nombre);
        imgImagen.setImageBitmap(bmp);

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
            case R.id.btAnhadirOpinion:
                /*Intent intentOpinion = new Intent(this, AnhadirOpinion.class);

                TextView txvNombre = findViewById(R.id.nombre);
                TextView txvdesarrolladora = findViewById(R.id.desarrolladora);
                RatingBar rtValoracion = findViewById(R.id.valoracion);

                intentOpinion.putExtra("nombre", txvNombre.getText().toString());
                intentOpinion.putExtra("desarrolladora", txvdesarrolladora.getText().toString());
                intentOpinion.putExtra("valoracion", rtValoracion.getRating());
                startActivity(intentOpinion);
                 */
                Intent intentOpinion = new Intent(this, Mapa.class);
                startActivity(intentOpinion);
                break;

            case R.id.relacionados:
                TextView txvTienda = findViewById(R.id.tiendaSeleccionada);
                TextView txvgenero = findViewById(R.id.genero);

                CheckBox chPC = findViewById(R.id.PC);
                CheckBox chXbox = findViewById(R.id.xbox);
                CheckBox chPlayStation = findViewById(R.id.playStation);
                CheckBox chSw = findViewById(R.id.sw);

                txvdesarrolladora = findViewById(R.id.desarrolladora);

                Intent intent = new Intent(this, Relacionados.class);

                intent.putExtra("tienda", txvTienda.getText().toString());
                intent.putExtra("pc", chPC.isChecked());
                intent.putExtra("xbox", chXbox.isChecked());
                intent.putExtra("playStation", chPlayStation.isChecked());
                intent.putExtra("sw", chSw.isChecked());
                intent.putExtra("genero", txvgenero.getText().toString());
                intent.putExtra("desarrolladora", txvdesarrolladora.getText().toString());

                startActivity(intent);
                break;

        }
    }

}
