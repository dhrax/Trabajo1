package com.daisa.trabajo1;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;

    private String tienda;
    private ArrayList<Tienda> arrayTiendas = new ArrayList<>();

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();





        // Inicializa el sistema de mapas de Google
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);*/
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                    MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

                    mapFragment.getMapAsync(Mapa.this);
                }
            }
        });


        Intent intent = getIntent();
        tienda = intent.getStringExtra("tienda");
        TareaDescargaDatosTienda tarea = new TareaDescargaDatosTienda(this, arrayTiendas);
        tarea.execute(Constantes.URL+"tiendasNombre?nombre="+tienda);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        mapa.getUiSettings().setMyLocationButtonEnabled(true);

        //CameraUpdate camara = CameraUpdateFactory.newLatLng(new LatLng(arrayTiendas.get(0).getLatitud(), arrayTiendas.get(0).getLongitud()));
        CameraUpdate camara = CameraUpdateFactory.newLatLng(latLng);

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

        //MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        //markerOptions.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.m));
        mapa.addMarker(markerOptions);

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.marker_icon);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    protected void onResume() {
        // Obtiene una vista de cámara
        super.onResume();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }
}

