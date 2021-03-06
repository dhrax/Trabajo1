package com.daisa.trabajo1.tarea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.daisa.trabajo1.objeto.Videojuego;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URL;


public class TareaAñadeDatos extends AsyncTask<String, Void, Void> {
    Videojuego videojuego;
    Activity act;

    public TareaAñadeDatos(Activity act, Videojuego videojuego) {
        this.act = act;
        this.videojuego = videojuego;
    }

    @Override
    protected Void doInBackground(String... urlParam) {


        String nombre = videojuego.getNombre(), desarrolladora = videojuego.getDesarrolladora(),
                genero = videojuego.getGenero(),anhoSalida = videojuego.getAnhoSalida(), tienda = videojuego.getTienda();
        boolean pc  = videojuego.isPC(), xbox = videojuego.isXbox(), playStation = videojuego.isPlayStation(),
                sw = videojuego.isSW(), favorito = videojuego.isFavorito();
        float valoracion  = videojuego.getValoracion();


        try {
            // Conecta con la URL y obtenemos el fichero con los datos
            Log.d("DAVID ANADE TAREA", "URL = "+urlParam[0]);
            URL url = new URL(urlParam[0]);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getForObject(url + "?nombre=" + nombre + "&desarrolladora=" + desarrolladora +
                    "&genero=" + genero + "&anhoSalida=" + anhoSalida + "&pc=" + pc +
                    "&xbox=" + xbox + "&playStation=" + playStation + "&sw=" + sw +
                    "&valoracion=" + valoracion + "&tienda=" + tienda + "&favorito=" + favorito, Void.class);
        } catch (Exception e) {
            Log.d("DAVID ANADE TAREA ERROR", e.getMessage());
        }

        return null;
    }

}
