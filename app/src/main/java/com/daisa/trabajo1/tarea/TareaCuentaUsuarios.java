package com.daisa.trabajo1.tarea;

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

public class TareaCuentaUsuarios extends AsyncTask<String, Void, Long>{

    private boolean error = false;
    private ProgressDialog dialog;
    private Activity act;
    Long numUsuarios;

    public TareaCuentaUsuarios(Activity act){
        this.act = act;
    }


    @Override
    protected Long doInBackground(String... params) {

        String url = params[0];
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        numUsuarios = restTemplate.getForObject(url, Long.class);

        return numUsuarios;

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        numUsuarios=-1L;
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
    protected void onPostExecute(Long resultado) {
        super.onPostExecute(resultado);

        if (error) {
            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dialog != null)
            dialog.dismiss();
    }
}

