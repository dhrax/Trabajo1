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

    public PlataformaFragment() {
        // Required empty public constructor

    }

    public static PlataformaFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        PlataformaFragment fragment = new PlataformaFragment();
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
}
