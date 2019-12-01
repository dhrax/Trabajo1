package com.daisa.trabajo1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class GeneroFragment extends Fragment {

    private ArrayList<Videojuego> videojuegos;
    private VideojuegoAdapter adaptador;
    private static String genero;

    public GeneroFragment(String genero) {
        this.genero = genero;
    }

    public static GeneroFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        GeneroFragment fragment = new GeneroFragment(genero);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recogida de parámetros
        int position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genero, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videojuegos = new ArrayList<>();
        ListView lvGenero = getView().findViewById(R.id.lvGenero);

        adaptador= new VideojuegoAdapter(getActivity().getApplicationContext(), videojuegos);
        lvGenero.setAdapter(adaptador);
        registerForContextMenu(lvGenero);
    }

    @Override
    public void onResume() {
        super.onResume();
        Database db = new Database(getActivity().getApplicationContext());
        videojuegos.clear();
        videojuegos.addAll(db.getVideojuegosGenero(genero));
        adaptador.notifyDataSetChanged();
    }
}
