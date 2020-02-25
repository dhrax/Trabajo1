package com.daisa.trabajo1.activitiesPrinc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daisa.trabajo1.R;
import com.daisa.trabajo1.objeto.Opinion;
import com.daisa.trabajo1.tarea.TareaAñadeOpinion;
import com.daisa.trabajo1.util.Constantes;


public class AnhadirOpinion extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        if (pref.getBoolean(getString(R.string.usarPreferencias), false))
            if (pref.getBoolean(getString(R.string.darkMode), false))
                setTheme(R.style.darktheme);
            else
                setTheme(R.style.AppTheme);


        setContentView(R.layout.activity_anhadir_opinion);

        Intent intent = getIntent();

        TextView txvNombre = findViewById(R.id.opNombre);
        TextView txvDesarrolladora = findViewById(R.id.opDesarrolladora);
        RatingBar rtValoracion = findViewById(R.id.opValoracion);
        Button btAnhadirOpinion = findViewById(R.id.btopAñadir);

        String nombre = intent.getStringExtra("nombre");
        String desarrolladora = intent.getStringExtra("desarrolladora");
        float valoracion = intent.getFloatExtra("valoracion", -1);

        txvNombre.setText(nombre);
        txvDesarrolladora.setText(desarrolladora);
        rtValoracion.setRating(valoracion);


        btAnhadirOpinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btopAñadir:
                TextView txvNombre = findViewById(R.id.opNombre);
                EditText etOpReview = findViewById(R.id.etOpReview);
                RatingBar rtValoracion = findViewById(R.id.opValoracion);

                String comentario = etOpReview.getText().toString();
                float valoracion = rtValoracion.getRating();

                String usuario = "anónimo";
                if (pref.getBoolean(getString(R.string.usarPreferencias), false))
                    if ( !pref.getString(getString(R.string.nomUsuPublico), "anónimo").equals(""))
                        usuario = pref.getString(getString(R.string.nomUsuPublico), "anónimo");

                Opinion opinion = new Opinion(usuario, txvNombre.getText().toString(), valoracion, comentario);

                TareaAñadeOpinion tarea = new TareaAñadeOpinion(this, opinion);
                tarea.execute(Constantes.URL + "add_opinion");
                finish();
                onBackPressed();

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
