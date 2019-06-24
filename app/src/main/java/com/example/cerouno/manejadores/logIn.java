package com.example.cerouno.manejadores;

import android.content.Intent;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
// import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cerouno.R;

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


        ingreso = findViewById(R.id.btn_ingresar);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ambiente.class);
                Ingresar(view );

                intent.putExtra("usuario", etu.getText().toString());
                intent.putExtra("hasusr", etp.getText().toString());

                startActivity(intent);
            }
        });

        etu = (EditText)findViewById(R.id.usuario);//.setText("leandro");
        etp = (EditText)findViewById(R.id.pass);//.setText("159357");


    }

    //metodo para el boton

    public void Ingresar (View view){

        String nombre = etu.getText().toString();
        String password = etp.getText().toString();
        /*
        if(nombre.length() == 0){
            msg.msg(this, "Debes ingresar un usuario", );
        }

        if(password.length() == 0){
            msg.msg(this, "Debes ingresar una contraseña", );
        }

        if(nombre.length() !=0 && password.length() !=0){
            msg.msg(this, "Registro en proceso",);
        }
        */

    }
}
