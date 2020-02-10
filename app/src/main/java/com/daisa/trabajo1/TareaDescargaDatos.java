package com.daisa.trabajo1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TareaDescargaDatos extends AsyncTask<String, Void, Void>{

    private boolean error = false;
    private ProgressDialog dialog;
    private MainActivity act;

    TareaDescargaDatos(MainActivity act){
        this.act = act;
    }

    /**
     * Método que ejecuta la tarea en segundo plano
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(String... params) {

        //String url = params[0];
        String resultado;
        JSONObject json;
        JSONArray jsonArray;

        try {
            // Conecta con la URL y obtenemos el fichero con los datos
            URL url = new URL(Constantes.URL);
            Log.d("DAVID", "Antes de URL Sin conexion");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            Log.d("DAVID", "Despues de URL Recupera conexion");

            // Lee el fichero de datos y genera una cadena de texto como resultado
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            }catch(Exception e){
                Log.d("DAVID", e.getMessage());
                br = new BufferedReader(new InputStreamReader(System.in));
            }

            Log.d("DAVID", "Se crea buffer");
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null){
                Log.d("DAVID", linea+"\n");
                sb.append(linea + "\n");
            }


            conexion.disconnect();
            br.close();
            resultado = sb.toString();
            Log.d("DAVID", "Antes de crear objeto json");
            try{
                jsonArray = new JSONArray(resultado);
            }catch (Exception e) {
                Log.d("DAVID", e.toString());
                jsonArray = new JSONArray();
            }
            Log.d("DAVID", "Se crea objeto json");
            Log.d("DAVID", "VALOR JSON: "+jsonArray.toString());

            String nombre, desarrolladora, genero, anhoSalida, tienda;
            int id;
            float valoracion;
            boolean pc, xbox, playStation, sw, favorito;

            Videojuego videojuego;
            Log.d("DAVID", "Antes FOR punto antes de recuperar los datos");
            for (int i = 0; i < jsonArray.length(); i++) {
                id = jsonArray.getJSONObject(i).getInt("id");
                Log.d("Dentro FOR", "Recupera datos id");

                nombre = jsonArray.getJSONObject(i).getString("nombre");
                Log.d("Dentro FOR", "Recupera datos nombre");

                desarrolladora = jsonArray.getJSONObject(i).getString("desarrolladora");
                Log.d("Dentro FOR", "Recupera datos desarrolladora");

                genero = jsonArray.getJSONObject(i).getString("genero");
                Log.d("Dentro FOR", "Recupera datos genero");

                anhoSalida = jsonArray.getJSONObject(i).getString("anhoSalida");
                Log.d("Dentro FOR", "Recupera datos anhoSalida");

                pc = jsonArray.getJSONObject(i).getBoolean("pc");
                Log.d("Dentro FOR", "Recupera datos pc");

                xbox = jsonArray.getJSONObject(i).getBoolean("xbox");
                Log.d("Dentro FOR", "Recupera datos xbox");

                playStation = jsonArray.getJSONObject(i).getBoolean("playStation");
                Log.d("Dentro FOR", "Recupera datos playStation");

                sw = jsonArray.getJSONObject(i).getBoolean("sw");
                Log.d("Dentro FOR", "Recupera datos sw");

                valoracion = (float) jsonArray.getJSONObject(i).getDouble("valoracion");
                Log.d("Dentro FOR", "Recupera datos puntuacion");

                tienda = jsonArray.getJSONObject(i).getString("tienda");
                Log.d("Dentro FOR", "Recupera datos tienda");

                favorito = jsonArray.getJSONObject(i).getBoolean("favorito");
                Log.d("Dentro FOR", "Recupera datos favorito");

                videojuego = new Videojuego(id, nombre, desarrolladora, genero, anhoSalida, pc, xbox, playStation, sw, valoracion, tienda, favorito);
                Log.d("titulo", videojuego.toString());

                MainActivity.videojuegos.add(videojuego);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            error = true;
        } catch (JSONException jse) {
            jse.printStackTrace();
            error = true;
        }

        return null;
    }

    /**
     * Método que se ejecuta si la tarea es cancelada antes de terminar
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        MainActivity.videojuegos = new ArrayList<>();
    }

    /**
     * Método que se ejecuta durante el progreso de la tarea
     * @param progreso
     */
    @Override
    protected void onProgressUpdate(Void... progreso) {
        super.onProgressUpdate(progreso);
        MainActivity.adaptador.notifyDataSetChanged();
    }

    /**
     * Método ejecutado automáticamente justo antes de lanzar la tarea en segundo plano
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(act);
        dialog.setTitle("Cargando");
        dialog.show();
    }

    /**
     * Método ejecutado automáticamente justo después de terminar la parte en segundo plano
     * Es la parte donde podemos interactuar con el UI para notificar lo sucedido al usuario
     * @param resultado
     */
    @Override
    protected void onPostExecute(Void resultado) {
        super.onPostExecute(resultado);

        if (error) {
            Toast.makeText(act, "Error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dialog != null)
            dialog.dismiss();

        MainActivity.adaptador.notifyDataSetChanged();
    }
}
