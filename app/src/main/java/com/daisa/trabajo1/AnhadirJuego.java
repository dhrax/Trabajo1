package com.daisa.trabajo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AnhadirJuego extends AppCompatActivity implements View.OnClickListener{

    private final int FOTO_TAREA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhadir_juego);

        Button btVolver = findViewById(R.id.btVolver);
        Button btAnhadir = findViewById(R.id.btAnhadirMain);
        ImageView imgFotovideojuego = findViewById(R.id.imgFotovideojuego);
        btVolver.setOnClickListener(this);
        btAnhadir.setOnClickListener(this);
        imgFotovideojuego.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btVolver:
                onBackPressed();
                break;

            case R.id.btAnhadirMain:

                ImageView imgFotovideojuego = findViewById(R.id.imgFotovideojuego);
                EditText txNombreVideojuego = findViewById(R.id.txNombreVideojuego);
                Spinner drpbtDesarrollador = findViewById(R.id.drpbtDesarrollador);
                Spinner drpbtGenero = findViewById(R.id.drpbtGenero);
                EditText txAnhoSalida = findViewById(R.id.txAnhoSalida);
                Switch swPC =findViewById(R.id.swPC);
                Switch swXbox =findViewById(R.id.swXbox);
                Switch swPlayStation =findViewById(R.id.swPlayStation);
                Switch swSwitch =findViewById(R.id.swSwitch);
                RatingBar rtValoracion = findViewById(R.id.rtValoracion);
                Spinner drpbtTienda = findViewById(R.id.drpbtTienda);
                Switch swFavorito =findViewById(R.id.swFavorito);


                String nombre = txNombreVideojuego.getText().toString();
                if (nombre.equals("")) {
                    Toast.makeText(this, "Tiene que añadir un nombre para añadir el videojuego", Toast.LENGTH_LONG).show();
                    return;
                }

                Bitmap imagen = ((BitmapDrawable) imgFotovideojuego.getDrawable()).getBitmap();

                String desarrolladora = drpbtDesarrollador.getSelectedItem().toString();
                String genero = drpbtGenero.getSelectedItem().toString();
                String anhoSalida = txAnhoSalida.getText().toString();
                if (anhoSalida.equals("")) {
                    anhoSalida="????";
                }
                boolean pc = swPC.isChecked();
                boolean xbox = swXbox.isChecked();
                boolean playStation = swPlayStation.isChecked();
                boolean sw = swSwitch.isChecked();
                float valoracion = rtValoracion.getRating();
                String tienda = drpbtTienda.getSelectedItem().toString();
                boolean favorito =swFavorito.isChecked();

                Videojuego videojuego = new Videojuego(nombre, desarrolladora, genero, anhoSalida, pc, xbox, playStation, sw, valoracion, tienda, favorito);
                videojuego.setImagen(imagen);
                Database db = new Database(this);
                db.nuevoVideojuego(videojuego);

                txNombreVideojuego.setText("");

                onBackPressed();
                break;

            case R.id.imgFotovideojuego:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, FOTO_TAREA);

                break;

        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == FOTO_TAREA) && (resultCode == RESULT_OK)
                && (data != null)) {
            // Obtiene el Uri de la imagen seleccionada por el usuario
            Uri imagenSeleccionada = data.getData();
            String[] ruta = {MediaStore.Images.Media.DATA };

            // Realiza una consulta a la galería de imágenes solicitando la imagen seleccionada
            Cursor cursor = getContentResolver().query(imagenSeleccionada, ruta, null, null, null);
            cursor.moveToFirst();

            // Obtiene la ruta a la imagen
            int indice = cursor.getColumnIndex(ruta[0]);
            String picturePath = cursor.getString(indice);
            cursor.close();

            // Carga la imagen en una vista ImageView que se encuentra en
            // en layout de la Activity actual
            ImageView imageView = (ImageView) findViewById(R.id.imgFotovideojuego);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
