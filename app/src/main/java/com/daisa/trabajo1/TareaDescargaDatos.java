package com.daisa.trabajo1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class TareaDescargaDatos extends AsyncTask<String, Void, Void>{

    private boolean error = false;
    private ProgressDialog dialog;
    private Activity act;
    ArrayList arrayList;
    VideojuegoAdapter adapter;
    //private boolean multipleVideojuego;
    //Videojuego videojuego;
    //DetallesJuego detalles;

    public TareaDescargaDatos(Activity act, ArrayList arrayList, VideojuegoAdapter adapter){
        this.act = act;
        this.arrayList = arrayList;
        this.adapter = adapter;
        //multipleVideojuego = true;
    }
    /*
    public TareaDescargaDatos(Activity act, Videojuego videojuego){
        this.act = act;
        this.videojuego = videojuego;
        multipleVideojuego = false;
        this.detalles = new DetallesJuego();
    }
    */
    @Override
    protected Void doInBackground(String... params) {

        String url = params[0];

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Videojuego[] opinionesArray = restTemplate.getForObject(url, Videojuego[].class);
            //if(multipleVideojuego)
                arrayList.addAll(Arrays.asList(opinionesArray));
            /*else{
                this.videojuego = opinionesArray[0];
            }
             */


        return null;

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        //if(multipleVideojuego)
            arrayList = new ArrayList<>();
    }

    @Override
    protected void onProgressUpdate(Void... progreso) {
        super.onProgressUpdate(progreso);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(act);
        dialog.setTitle("Cargando");
        dialog.show();
    }


    @Override
    protected void onPostExecute(Void resultado) {
        super.onPostExecute(resultado);

        if (error) {
            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dialog != null)
            dialog.dismiss();

        //if(multipleVideojuego)
            adapter.notifyDataSetChanged();
        /*else{
            detalles.videojuego = this.videojuego;
            Log.d("DAVID1", detalles.videojuego.toString());
            detalles.mostrarDatos(detalles.videojuego);
        }*/
    }
}

