package com.daisa.trabajo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAnhadirMain = findViewById(R.id.btAnhadirMain);
        btAnhadirMain.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_anhadir_videojuego, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, AnhadirJuego.class);
        startActivity(intent);

        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btAnhadirMain:
                Intent intent = new Intent(this, AnhadirJuego.class);
                startActivity(intent);

                break;

        }
    }
}
