package com.daisa.trabajo1.tarea;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.daisa.trabajo1.objeto.Opinion;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URL;


public class TareaAñadeOpinion extends AsyncTask<String, Void, Void> {
    Opinion opinion;
    Activity act;

    public TareaAñadeOpinion(Activity act, Opinion opinion) {
        this.act = act;
        this.opinion = opinion;
    }

    @Override
    protected Void doInBackground(String... urlParam) {


        String autor = opinion.getAutor(), videojuegoNom = opinion.getVideojuego(), comentario = opinion.getComentario();
        float valoracion  = opinion.getValoracion();


        try {
            // Conecta con la URL y obtenemos el fichero con los datos
            Log.d("DAVID ANADE OPI", "URL = "+urlParam[0]);
            URL url = new URL(urlParam[0]);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getForObject(url + "?autor=" + autor + "&videojuego=" + videojuegoNom +
                    "&valoracion=" + valoracion + "&comentario=" + comentario, Void.class);
        } catch (Exception e) {
            Log.d("DAVID ANADE OPI ERROR", e.getMessage());
        }

        return null;
    }

}

