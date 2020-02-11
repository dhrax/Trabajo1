package com.daisa.trabajo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class AnhadirOpinion extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        switch (v.getId()){

            case R.id.btopAñadir:

                EditText etOpReview = findViewById(R.id.etOpReview);
                RatingBar rtValoracion = findViewById(R.id.opValoracion);

                String opinion = etOpReview.getText().toString();
                float valoracion = rtValoracion.getRating();

                //TODO añadir tabla opiniones y metodos para añadir opiniones

                break;

        }
    }
}
