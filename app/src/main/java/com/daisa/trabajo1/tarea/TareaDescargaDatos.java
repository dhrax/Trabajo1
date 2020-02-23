package com.daisa.trabajo1.tarea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.daisa.trabajo1.activitiesPrinc.ListaOpiniones;
import com.daisa.trabajo1.activitiesPrinc.MainActivity;
import com.daisa.trabajo1.adapter.OpinionAdapter;
import com.daisa.trabajo1.objeto.Opinion;
import com.daisa.trabajo1.objeto.Tienda;
import com.daisa.trabajo1.objeto.Videojuego;
import com.daisa.trabajo1.tab.DesarrolladoraFragment;
import com.daisa.trabajo1.tab.GeneroFragment;
import com.daisa.trabajo1.tab.PlataformaFragment;
import com.daisa.trabajo1.tab.TiendaFragment;

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

public class TareaDescargaDatos extends AsyncTask<String, Void, Void> {

    private boolean error = false;
    private ProgressDialog dialog;
    private Activity act;
    ArrayList arrayList;
    private String procedencia;

    public TareaDescargaDatos(Activity act, ArrayList arrayList, String procedencia) {
        this.act = act;
        this.arrayList = arrayList;
        this.procedencia = procedencia.toLowerCase();
    }

    @Override
    protected Void doInBackground(String... params) {

        String url = params[0];
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            switch (procedencia) {

                case "lista opiniones":
                    Opinion[] opinionesArray = restTemplate.getForObject(url, Opinion[].class);
                    arrayList.addAll(Arrays.asList(opinionesArray));
                    break;
                case "lista tiendas mapa":
                    Tienda[] tiendasArray = restTemplate.getForObject(url, Tienda[].class);
                    arrayList.addAll(Arrays.asList(tiendasArray));
                    break;

                case "lista principal":

                case "lista tab desarrolladora":

                case "lista tab plataforma":

                case "lista tab tienda":

                case "lista tab genero":
                    Videojuego[] videojuegosArray = restTemplate.getForObject(url, Videojuego[].class);
                    arrayList.addAll(Arrays.asList(videojuegosArray));
                    break;

                default:
                    Log.e("LLAMADA NO ENCONTRADA", "Se ha llamado a la descarga de datos desde un lugar no controlado");
                    break;
            }
        } catch (Exception e) {
            Log.e("DAVID ERROR", e.getMessage());
            error = true;
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

        switch (procedencia) {

            case "lista opiniones":
                ListaOpiniones.adaptador.notifyDataSetChanged();
                break;

            case "lista principal":
                MainActivity.adaptador.notifyDataSetChanged();
                break;

            case "lista tab desarrolladora":
                DesarrolladoraFragment.adaptador.notifyDataSetChanged();
                break;

            case "lista tab plataforma":
                PlataformaFragment.adaptador.notifyDataSetChanged();
                break;

            case "lista tab tienda":
                TiendaFragment.adaptador.notifyDataSetChanged();
                break;

            case "lista tab genero":
                GeneroFragment.adaptador.notifyDataSetChanged();
                break;
            case "lista tiendas mapa":
                break;
            default:
                Log.e("LLAMADA NO ENCONTRADA", "Se ha llamado al refresco del adaptador desde un lugar desconocido");
                break;
        }
    }
}

