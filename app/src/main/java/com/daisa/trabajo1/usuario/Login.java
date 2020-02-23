package com.daisa.trabajo1.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daisa.trabajo1.R;
import com.daisa.trabajo1.activitiesPrinc.MainActivity;
import com.daisa.trabajo1.objeto.Usuario;
import com.daisa.trabajo1.tarea.TareaCompruebaUsuarioContraseña;
import com.daisa.trabajo1.tarea.TareaCuentaUsuarios;
import com.daisa.trabajo1.util.Constantes;
import com.daisa.trabajo1.util.Util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rLayout1, rLayout2;
    Button btLogin, btIrARegistrarUsuario, btOlvidarContrasena;
    TextView txvLogInVerOcultarCOntraseña;
    EditText etContraseña;
    Usuario usuario = new Usuario();
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rLayout1.setVisibility(View.VISIBLE);
            rLayout2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        rLayout1 = findViewById(R.id.rLayout1);
        rLayout2 = findViewById(R.id.rLayout2);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        btIrARegistrarUsuario = findViewById(R.id.btIrARegistrarUsuario);
        btIrARegistrarUsuario.setOnClickListener(this);
        btOlvidarContrasena = findViewById(R.id.btOlvidarContrasena);
        btOlvidarContrasena.setOnClickListener(this);
        txvLogInVerOcultarCOntraseña = findViewById(R.id.txvLogInVerOcultarCOntraseña);
        txvLogInVerOcultarCOntraseña.setOnClickListener(this);

        etContraseña = findViewById(R.id.etContraseña);

        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btLogin:
                EditText etLogin = findViewById(R.id.etLogin);//te amo

                String nomUsuario = etLogin.getText().toString().toLowerCase();
                String contraseña = etContraseña.getText().toString().toLowerCase();
                /*if (!Util.contraseñaPermitida(contraseña)) {
                    Toast.makeText(getApplicationContext(), "La contraseña no cumple los requisitos", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                TareaCuentaUsuarios tareaCuentaUsuarios = new TareaCuentaUsuarios(this);
                Long numUsuariosPorNombre = 0L;
                try {
                    numUsuariosPorNombre = tareaCuentaUsuarios.execute(Constantes.URL+"contarPorNombre?nombre="+nomUsuario).get();
                } catch (ExecutionException e) {
                    Log.e("ERROR", e.getMessage());
                } catch (InterruptedException e) {
                    Log.e("ERROR", e.getMessage());
                }

                if(numUsuariosPorNombre<1L){
                    Util.intentosUsuarioFallido++;
                    Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();

                    if (Util.intentosUsuarioFallido >= 3) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Parece que no está registrado, ¿desea registrarse?").setTitle("Usuario no registrado")
                                .setPositiveButton("Si",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(getApplicationContext(), RegistrarUsuario.class);
                                                startActivity(intent);
                                                Util.intentosUsuarioFallido = 0;
                                                dialog.dismiss();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Util.intentosUsuarioFallido = 0;
                                                dialog.dismiss();
                                            }
                                        });
                        builder.create().show();
                    }
                    return;
                }

                TareaCompruebaUsuarioContraseña tarea = new TareaCompruebaUsuarioContraseña(this, usuario);

                try {
                    usuario = tarea.execute(Constantes.URL+"usuariosNombre?nombre="+nomUsuario).get();
                } catch (ExecutionException e) {
                    Log.e("ERROR", e.getMessage());
                } catch (InterruptedException e) {
                    Log.e("ERROR", e.getMessage());
                }

                if (Util.comprobarContraseña(usuario, contraseña)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    Util.intentosContraseñaFallido++;
                    if (Util.intentosContraseñaFallido >= 5) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Parece que ha olvidado su contraseña, ¿desea cambiarla?").setTitle("Contraseña errónea")
                                .setPositiveButton("Si",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent = new Intent(getApplicationContext(), ResetearContrasenha.class);
                                                startActivity(intent);
                                                Util.intentosContraseñaFallido = 0;
                                                dialog.dismiss();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Util.intentosContraseñaFallido = 0;
                                                dialog.dismiss();
                                            }
                                        });

                        builder.create().show();
                    }

                }
                return;

            case R.id.btIrARegistrarUsuario:
                Intent intentRegistrarUsuario = new Intent(this, RegistrarUsuario.class);
                startActivity(intentRegistrarUsuario);

                break;

            case R.id.btOlvidarContrasena:
                //Intent intent = new Intent(this, ResetearContrasenha.class);
                //startActivity(intent);
                break;
            case R.id.txvLogInVerOcultarCOntraseña:
                Util.toggleVerOcultarCntraseña(etContraseña, txvLogInVerOcultarCOntraseña);
                break;
            default:
                break;
        }
    }
}
