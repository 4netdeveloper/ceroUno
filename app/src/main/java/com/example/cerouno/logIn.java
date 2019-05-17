package com.example.cerouno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class logIn extends AppCompatActivity {

    private EditText etu, etp;
    private Button ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ingreso = findViewById(R.id.btn_ingresar);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), aparatos.class);
                startActivity(intent);
            }
        });

        etu = (EditText)findViewById(R.id.usuario);
        etp = (EditText)findViewById(R.id.pass);
    }

    //metodo para el boton

    public void Ingresar (View view){

        String nombre = etu.getText().toString();
        String password = etp.getText().toString();

        if(nombre.length() == 0){
            Toast.makeText(this, "Debes ingresar un usuario", Toast.LENGTH_LONG).show();
        }

        if(password.length() == 0){
            Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
        }

        if(nombre.length() !=0 && password.length() !=0){
            Toast.makeText(this, "Registro en proceso", Toast.LENGTH_LONG).show();
        }

    }
}
