package com.example.cerouno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;

public class ambiente extends AppCompatActivity {

    private final int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio,  //array de botones
            R.id.amb_dormitorio2, R.id.amb_patio, R.id.amb_extra};

     Fragment [] misFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);

        misFragmentos [0] = new Living();
        misFragmentos [1] = new Cocina();
        misFragmentos [2] = new Comedor();
        misFragmentos [3] = new Bano();
        misFragmentos [4] = new Dormitorio();
        misFragmentos [5] = new Patio();

        //en esta variable se almacena el valor de cada boton
        ImageButton botonMenu;

        for (int i=0; i<BOTONESMENU.length; i++){
            for (int j = 0; j<misFragmentos.length; j++){

                botonMenu = findViewById(BOTONESMENU[i]);

                botonMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent estaActividad = getApplicationContext(this, misFragmentos[j]);
                        startActivity(estaActividad);
                    }
                });

            }
        }

    }

}
