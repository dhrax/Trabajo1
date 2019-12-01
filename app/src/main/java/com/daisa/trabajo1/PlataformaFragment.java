package com.daisa.trabajo1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PlataformaFragment extends Fragment {

    private ArrayList<Videojuego> videojuegos;
    private VideojuegoAdapter adaptador;
    private static boolean pc;
    private static boolean xbox;
    private static boolean playStation;
    private static boolean sw;

    public PlataformaFragment(boolean pc, boolean xbox, boolean playStation, boolean sw) {
        this.pc = pc;
        this.xbox = xbox;
        this.playStation = playStation;
        this.sw = sw;
    }

    public static PlataformaFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        PlataformaFragment fragment = new PlataformaFragment(pc, xbox, playStation, sw);
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

        View view = inflater.inflate(R.layout.fragment_plataforma, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videojuegos = new ArrayList<>();
        ListView lvPlataforma = getView().findViewById(R.id.lvPlataforma);

        adaptador= new VideojuegoAdapter(getActivity().getApplicationContext(), videojuegos);
        lvPlataforma.setAdapter(adaptador);
        registerForContextMenu(lvPlataforma);
    }

    @Override
    public void onResume() {
        super.onResume();
        Database db = new Database(getActivity().getApplicationContext());
        videojuegos.clear();
        videojuegos.addAll(db.getVideojuegosPlataforma(pc, xbox, playStation, sw));
        adaptador.notifyDataSetChanged();
    }

}
