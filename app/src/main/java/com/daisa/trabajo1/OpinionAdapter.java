package com.daisa.trabajo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class OpinionAdapter extends BaseAdapter {


    private Context contexto;
    private ArrayList<Opinion> opiniones;
    private LayoutInflater layoutinflater;

    OpinionAdapter(Context contexto, ArrayList<Opinion> opiniones){
        this.contexto = contexto;
        this.opiniones = opiniones;
        layoutinflater = LayoutInflater.from(contexto);
    }

    static class ViewHolder{
        TextView autor;
        TextView valoracion;
        EditText comentario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OpinionAdapter.ViewHolder viewholder = null;

        if(convertView == null){
            convertView = layoutinflater.inflate(R.layout.item_opinion, null);
            viewholder = new OpinionAdapter.ViewHolder();
            viewholder.autor = convertView.findViewById(R.id.txvAutor);
            viewholder.valoracion = convertView.findViewById(R.id.txvValoracion);
            viewholder.comentario = convertView.findViewById(R.id.edComentario);
            convertView.setTag(viewholder);

        }else
            viewholder = (OpinionAdapter.ViewHolder) convertView.getTag();

        Opinion opinion = opiniones.get(position);
        //viewholder.imagen.setImageBitmap(videojuego.getImagen());
        viewholder.autor.setText(opinion.getAutor());
        viewholder.valoracion.setText(opinion.getValoracion()+"/"+10);
        viewholder.comentario.setText(opinion.getComentario());

        return convertView;
    }

    @Override
    public int getCount() {
        return opiniones.size();
    }

    @Override
    public Object getItem(int position) {
        return opiniones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
