package com.example.cerouno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class inicio extends AppCompatActivity implements View.OnClickListener{

    //botones para iniciar las actividades
    private ImageButton boton_tv, boton_luces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ImageButton boton_tv = (ImageButton) findViewById(R.id.menu_tv);
        boton_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuSlideActivity.opcion = 1;   //metodo para q aparezca este FRAGMENT cuando se elija este boton
                Intent intent = new Intent(getApplicationContext(), MenuSlideActivity.class);
                startActivity(intent);
            }
        });

        ImageButton boton_luces = (ImageButton) findViewById(R.id.menu_luces);
        boton_luces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MenuSlideActivity.opcion = 2;   //metodo para q aparezca este FRAGMENT cuando se elija este boton
                Intent intent = new Intent(getApplicationContext(), MenuSlideActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}

