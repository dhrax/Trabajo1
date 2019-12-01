package com.daisa.trabajo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Videojuego> videojuegos;
    private VideojuegoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnhadirMain = findViewById(R.id.btAnhadirMain);
        btAnhadirMain.setOnClickListener(this);

        videojuegos = new ArrayList<>();
        ListView lvVideojuego = findViewById(R.id.lvVideojuego);
        adaptador = new VideojuegoAdapter(this, videojuegos);
        lvVideojuego.setAdapter(adaptador);

        lvVideojuego.setOnItemClickListener(this);
        registerForContextMenu(lvVideojuego);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        Database db = new Database(this);
        videojuegos.clear();
        videojuegos.addAll(db.getVideojuegos());
        adaptador.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_anhadir_videojuego, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, AnhadirJuego.class);
        startActivity(intent);

        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btAnhadirMain:
                Intent intent = new Intent(this, AnhadirJuego.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Intent intentMapa = new Intent(this, DetallesJuego.class);

        //TODO poner el intent de la imagen
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        videojuegos.get(i).getImagen().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        intentMapa.putExtra("imagen", byteArray);
        intentMapa.putExtra("nombre", videojuegos.get(i).getNombre());
        intentMapa.putExtra("desarrolladora", videojuegos.get(i).getDesarrolladora());
        intentMapa.putExtra("genero", videojuegos.get(i).getGenero());
        intentMapa.putExtra("anhoSalida", videojuegos.get(i).getAnhoSalida());
        intentMapa.putExtra("pc", videojuegos.get(i).isPC());
        intentMapa.putExtra("xbox", videojuegos.get(i).isXbox());
        intentMapa.putExtra("playStation", videojuegos.get(i).isPlayStation());
        intentMapa.putExtra("sw", videojuegos.get(i).isSW());
        intentMapa.putExtra("valoracion", videojuegos.get(i).getValoracion());
        intentMapa.putExtra("tienda", videojuegos.get(i).getTienda());
        intentMapa.putExtra("favorito", videojuegos.get(i).isFavorito());

        startActivity(intentMapa);
    }
}
