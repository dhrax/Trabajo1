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

public class TareaCompruebaUsuarioContraseña extends AsyncTask<String, Void, Usuario>{

    private boolean error = false;
    private ProgressDialog dialog;
    private Activity act;
    Usuario usuario;

    public TareaCompruebaUsuarioContraseña(Activity act, Usuario usuario){
        this.act = act;
        this.usuario = usuario;
    }


    @Override
    protected Usuario doInBackground(String... params) {

        String url = params[0];
        Log.d("DAVID", url);
        Usuario[] usuariosArray = null;
        //try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            usuariosArray = restTemplate.getForObject(url, Usuario[].class);
        /*}catch (Exception e){
            Log.d("DAVIDDDDD", e.getMessage());
        }*/

        Log.d("DAVID", usuariosArray.toString());
        usuario = usuariosArray[0];


        return usuario;

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        usuario = new Usuario();
    }

    @Override
    protected void onProgressUpdate(Void... progreso) {
        super.onProgressUpdate(progreso);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(act);
        dialog.setTitle("Cargando");
        dialog.show();
    }


    @Override
    protected void onPostExecute(Usuario resultado) {
        super.onPostExecute(resultado);

        if (error) {
            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dialog != null)
            dialog.dismiss();
    }
}

