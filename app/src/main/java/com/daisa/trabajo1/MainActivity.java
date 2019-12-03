package com.daisa.trabajo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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
        this.setTitle(R.string.gameList);

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
        switch (item.getItemId()){
            case R.id.itemDetalles:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.descDetalles)).setTitle(getString(R.string.acercaDe))
                        .setNegativeButton(getString(R.string.cerrar),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }});

                builder.create().show();
                break;
            case R.id.actionNuevoVideojuego:
                Intent intent = new Intent(this, AnhadirJuego.class);
                startActivity(intent);
                break;
        }



        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual_favorito, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //Nos dice dónde está un listView

        final int posicion = menuInfo.position; //y esta

        switch (item.getItemId()) {//  Qué item he pulsado
            case R.id.itemFavorito:
                Videojuego videojuego = videojuegos.get(posicion);
                videojuego.favorito();
                Database db = new Database(this);
                db.videojuegoFavorito(videojuego);
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.itemBorrar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.borrarVideojuegoQ)).setTitle(getString(R.string.borrar))
                        .setPositiveButton(getString(R.string.si),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Videojuego videojuegoEliminado = videojuegos.remove(posicion);

                                        Database db = new Database(getApplicationContext());
                                        db.borrarVideojuego(videojuegoEliminado);
                                        adaptador.notifyDataSetChanged();
                                        dialog.dismiss();
                                    }})
                        .setNegativeButton(getString(R.string.no),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }});

                builder.create().show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
