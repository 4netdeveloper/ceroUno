package com.example.cerouno;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class aparatos extends AppCompatActivity implements View.OnClickListener, ComunicaBoton{

    Fragment [] misFragmentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparatos);

        misFragmentos = new Fragment[6];

        misFragmentos [0] = new Living();
        misFragmentos [1] = new Cocina();
        misFragmentos [2] = new Comedor();
        misFragmentos [3] = new Bano();
        misFragmentos [4] = new Dormitorio();
        misFragmentos [5] = new Patio();

        Bundle extras = getIntent().getExtras();    //en EXTRAS se tiene la info del boton pulsado

        boton(extras.getInt("BOTONPULSADO")); //se extrae la info y se la envia al metodo boton()

    }

    @Override
    public void boton(int queBoton) {

        FragmentManager miManejador = getFragmentManager();

        FragmentTransaction miTransaccion = miManejador.beginTransaction();

        miTransaccion.replace(R.id.contenedor, misFragmentos[queBoton]);

        miTransaccion.commit();

    }

    @Override
    public void onClick(View view) {

    }
}

