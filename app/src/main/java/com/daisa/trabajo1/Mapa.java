package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;

    private String tienda;
    private ArrayList<Tienda> arrayTiendas = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Intent intent = getIntent();
        tienda = intent.getStringExtra("tienda");

        TareaDescargaDatosTienda tarea = new TareaDescargaDatosTienda(this, arrayTiendas);
        tarea.execute(Constantes.URL+"tiendasNombre?nombre="+tienda);

        // Inicializa el sistema de mapas de Google
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        CameraUpdate camara = CameraUpdateFactory.newLatLng(new LatLng(arrayTiendas.get(0).getLatitud(), arrayTiendas.get(0).getLongitud()));

        // Coloca la vista del mapa sobre la posición del restaurante
        // y activa el zoom para verlo de cerca
            mapa.moveCamera(camara);
            mapa.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        for(Tienda tiendas : arrayTiendas){
            // Añade una marca en la posición del restaurante con el nombre de éste
            mapa.addMarker(new MarkerOptions()
                    .position(new LatLng(tiendas.getLatitud(), tiendas.getLongitud()))
                    .title(tiendas.getNombre()));
        }
    }

    @Override
    protected void onResume() {
        // Obtiene una vista de cámara
        super.onResume();

    }
}

