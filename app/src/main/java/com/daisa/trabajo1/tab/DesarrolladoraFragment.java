package com.daisa.trabajo1.tab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.daisa.trabajo1.R;
import com.daisa.trabajo1.adapter.VideojuegoAdapter;
import com.daisa.trabajo1.objeto.Videojuego;
import com.daisa.trabajo1.tarea.TareaDescargaDatos;
import com.daisa.trabajo1.util.Constantes;

import java.util.ArrayList;

public class DesarrolladoraFragment extends Fragment {

    private ArrayList<Videojuego> videojuegos;
    public static VideojuegoAdapter adaptador;
    private static String desarrolladora;

    public DesarrolladoraFragment(String desarrolladora) {
        this.desarrolladora= desarrolladora;
    }

    public static DesarrolladoraFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        DesarrolladoraFragment fragment = new DesarrolladoraFragment(desarrolladora);
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
        View view = inflater.inflate(R.layout.fragment_desarrolladora, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videojuegos = new ArrayList<>();
        ListView lvGenero = getView().findViewById(R.id.lvDesarrolladora);

        adaptador= new VideojuegoAdapter(getActivity().getApplicationContext(), videojuegos);
        lvGenero.setAdapter(adaptador);
        registerForContextMenu(lvGenero);
    }

    @Override
    public void onResume() {
        super.onResume();
        videojuegos.clear();
        adaptador.notifyDataSetChanged();

        TareaDescargaDatos tarea = new TareaDescargaDatos(getActivity(), videojuegos, "Lista Tab Desarrolladora");
        tarea.execute(Constantes.URL+"videojuegosDesarrolladora?desarrolladora="+desarrolladora);
    }
}
