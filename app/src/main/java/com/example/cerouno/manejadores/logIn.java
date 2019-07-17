package com.example.cerouno.manejadores;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;

import static com.example.cerouno.manejadores.ambiente.conex;

public class logIn extends AppCompatActivity {

    private EditText etu, etp;
    private Button ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */

        //RaspbyConnect rp = new RaspbyConnect(this);


        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * *********************************************** */

        etu = (EditText)findViewById(R.id.usuario);
        etp = (EditText)findViewById(R.id.pass);

        ingreso = findViewById(R.id.btn_ingresar);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etu.getText().toString();
                ambiente.conex.setUser(nombre);

                String password = String.valueOf(etp.getText());
                conex.setHash(Integer.parseInt(password));

                int status = conex.getStatus();
                Log.i ("--------LOGIN", String.valueOf(status));

                switch (status){
                    case 0: Toast.makeText(getApplicationContext(), "Servicio no encontrado!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1: Toast.makeText(getApplicationContext(), "Usuario o Password Incorrecto", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: Toast.makeText(getApplicationContext(), "Bienvenido "+nombre+"!", Toast.LENGTH_SHORT).show();
                        SharedPreferences prefGuarda = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefGuarda.edit();
                        editor.putString("user",etu.getText().toString());
                        editor.putInt("hash", Integer.parseInt(password));
                        editor.apply();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), ambiente.class);
                    startActivity(intent);
                        break;

                    default: break;
                }
            }
        });

    }


}
