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

public class TareaDescargaDatosOpinion extends AsyncTask<String, Void, Void>{

    private boolean error = false;
    private ProgressDialog dialog;
    private Activity act;
    ArrayList arrayList;
    OpinionAdapter adapter;

    public TareaDescargaDatosOpinion(Activity act, ArrayList arrayList, OpinionAdapter adapter){
        this.act = act;
        this.arrayList = arrayList;
        this.adapter = adapter;
    }
    @Override
    protected Void doInBackground(String... params) {

        String url = params[0];
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Opinion[] opinionesArray = restTemplate.getForObject(url, Opinion[].class);

            arrayList.addAll(Arrays.asList(opinionesArray));
        }catch (Exception e){
            Log.d("DAVID", e.getMessage());
        }



        return null;

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
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

        adapter.notifyDataSetChanged();
    }
}

