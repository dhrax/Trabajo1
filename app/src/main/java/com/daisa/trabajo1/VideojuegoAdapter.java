package com.daisa.trabajo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VideojuegoAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<Videojuego> videojuegos;
    private LayoutInflater layoutinflater;

    VideojuegoAdapter(Context contexto, ArrayList<Videojuego> videojuegos){
        this.contexto = contexto;
        this.videojuegos = videojuegos;
        layoutinflater = LayoutInflater.from(contexto);
    }

    static class ViewHolder{
        ImageView imagen;
        TextView nombre;
        TextView genero;
        TextView anhoSalida;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;

        if(convertView == null){
            convertView = layoutinflater.inflate(R.layout.item_videojuego, null);
            viewholder = new ViewHolder();
            //viewholder.imagen = convertView.findViewById(R.id.itemimgVideojuego);
            viewholder.nombre = convertView.findViewById(R.id.itemtxvNombre);
            viewholder.genero = convertView.findViewById(R.id.itemtxvGenero);
            viewholder.anhoSalida = convertView.findViewById(R.id.itemtxvAnhoSalia);
            convertView.setTag(viewholder);

        }else
            viewholder = (ViewHolder) convertView.getTag();

        Videojuego videojuego = videojuegos.get(position);
        //viewholder.imagen.setImageBitmap(videojuego.getImagen());
        viewholder.nombre.setText(videojuego.getNombre());
        viewholder.genero.setText(videojuego.getGenero());
        viewholder.anhoSalida.setText(videojuego.getAnhoSalida());

        return convertView;
    }

    @Override
    public int getCount() {
        return videojuegos.size();
    }

    @Override
    public Object getItem(int position) {
        return videojuegos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
