package com.example.cerouno.manejadores;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;

import static com.example.cerouno.manejadores.ambiente.conex;
import static com.example.cerouno.manejadores.ambiente.banderaStatus;

public class logIn extends AppCompatActivity {

    private EditText etu, etp;
    private Button ingreso;
    static int status;
    static String password;
    static boolean respuestaStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        if (conex == null) ambiente.creaConexion(this);

        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */

        //RaspbyConnect rp = new RaspbyConnect(this);


        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * *********************************************** */

        etu = (EditText) findViewById(R.id.usuario);
        etp = (EditText) findViewById(R.id.pass);

        ingreso = findViewById(R.id.btn_ingresar);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etu.getText().toString();
                ambiente.conex.setUser(nombre);

                password = String.valueOf(etp.getText());
                conex.setHash(Integer.parseInt(password) + "");
                banderaStatus = 2;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        status = conex.getStatus();
                    }
                }, 1000);

                Log.i("--------LOGIN", String.valueOf(status));

                if (status == 0) {
                    Toast.makeText(getApplicationContext(), "Servicio no encontrado!", Toast.LENGTH_SHORT).show();
                } else {
                    checkeo(respuestaStatus);
                }
            }
        });

    }
    public void checkeo(boolean check){
        if (check){
            Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
            SharedPreferences prefGuarda = getSharedPreferences("usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefGuarda.edit();
            editor.putString("user",etu.getText().toString());
            editor.putInt("hash", Integer.parseInt(password));
            editor.apply();
            finish();
            Intent intent = new Intent(getApplicationContext(), ambiente.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Usuario o Password Incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
