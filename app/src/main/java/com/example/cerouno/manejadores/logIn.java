package com.example.cerouno.manejadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;

import static com.example.cerouno.manejadores.ambiente.banderaStatus;
import static com.example.cerouno.manejadores.ambiente.conex;

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

        etu = findViewById(R.id.usuario);
        etp = findViewById(R.id.pass);

        ingreso = findViewById(R.id.btn_ingresar);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etu.getText().toString();
                ambiente.conex.setUser(nombre);

                password = String.valueOf(etp.getText());
                conex.setHash(password);
                conex.setHost("");
                banderaStatus = 2;
                status = conex.getStatus(new conexion.onPostExecute() {
                    @Override
                    public void recibirTexto(String txt, int estado) {
                        validarNuevoUsuario();
                        Log.i("-------estadoamb", String.valueOf(estado));
                    }
                });


                Log.i("--------LOGIN", String.valueOf(status));

                if (status == 0) {
                    Toast.makeText(getApplicationContext(), "Servicio no encontrado!", Toast.LENGTH_SHORT).show();
                }}
        });

    }
    public void validarNuevoUsuario(){
        conex.getStatus(new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                Log.d("recibido", "recibido:" + estado);
                estadoUsuario(estado);

            }
        });
    }
    public void estadoUsuario(int check){
        if (check == 2){
            Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
            SharedPreferences prefGuarda = getSharedPreferences("usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefGuarda.edit();
            editor.putString("user",etu.getText().toString());
            editor.putString("hash", password);
            editor.apply();
            finish();
            Intent intent = new Intent(getApplicationContext(), ambiente.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Usuario o Password Incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
