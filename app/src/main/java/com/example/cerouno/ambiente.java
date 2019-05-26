package com.example.cerouno.manejadores;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.manejadores.MenuSlideActivity;

public class ambiente extends AppCompatActivity implements View.OnClickListener{

    static int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio, R.id.amb_dormitorio2,  //array de botones
            R.id.amb_patio};

    int i, j;
    int opcion = i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);

        ImageButton livingB = findViewById(BOTONESMENU[0]);
        livingB.setOnClickListener(this);

        ImageButton cocinaB = findViewById(BOTONESMENU[1]);
        cocinaB.setOnClickListener(this);

        ImageButton comedorB = findViewById(BOTONESMENU[2]);
        comedorB.setOnClickListener(this);

        ImageButton banoB = findViewById(BOTONESMENU[3]);
        banoB.setOnClickListener(this);

        ImageButton dormitorioB = findViewById(BOTONESMENU[4]);
        dormitorioB.setOnClickListener(this);

        ImageButton dormitorio2B = findViewById(BOTONESMENU[5]);
        dormitorio2B.setOnClickListener(this);

        ImageButton patioB = findViewById(BOTONESMENU[6]);
        patioB.setOnClickListener(this);
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.amb_tv: Intent intent = new Intent(getApplicationContext(), MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 1 ;break;

            case R.id.amb_cocina: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 2 ;break;

            case R.id.amb_comedor: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 3 ;break;

            case R.id.amb_bano: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 4 ;break;

            case R.id.amb_dormitorio: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 5 ;break;

            case R.id.amb_dormitorio2: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 6 ;break;

            case R.id.amb_patio: intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
                startActivity(intent); MenuSlideActivity.opcion= 7 ;break;
        }
    }
}