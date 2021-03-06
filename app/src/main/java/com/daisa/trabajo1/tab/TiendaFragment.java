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


public class TiendaFragment extends Fragment {

    private ArrayList<Videojuego> videojuegos;
    public static VideojuegoAdapter adaptador;
    private static String tienda;

    public TiendaFragment(String tienda) {
        this.tienda=tienda;
    }

    public static TiendaFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        TiendaFragment fragment = new TiendaFragment(tienda);
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
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videojuegos = new ArrayList<>();
        ListView lvTiendas = getView().findViewById(R.id.lvTiendas);

        adaptador= new VideojuegoAdapter(getActivity().getApplicationContext(), videojuegos);
        lvTiendas.setAdapter(adaptador);
        registerForContextMenu(lvTiendas);
    }

    @Override
    public void onResume() {
        super.onResume();
        videojuegos.clear();
        adaptador.notifyDataSetChanged();

        TareaDescargaDatos tarea = new TareaDescargaDatos(getActivity(), videojuegos, "Lista Tab Tienda");
        tarea.execute(Constantes.URL+"videojuegosTienda?tienda="+tienda);
    }
}
