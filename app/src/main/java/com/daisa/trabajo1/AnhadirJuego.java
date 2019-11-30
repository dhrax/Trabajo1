package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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



                break;

            case R.id.imgFotovideojuego:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, FOTO_TAREA);

                break;

        }
    }
}
