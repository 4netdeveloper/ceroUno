package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
//import android.widget.Button;


import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.administrador.msg;
import com.example.cerouno.manejadores.ambiente;

import static com.example.cerouno.R.drawable.foco;
import static com.example.cerouno.R.drawable.foco_apagado;
import static com.example.cerouno.manejadores.ambiente.conex;

public class Cocina extends Fragment implements View.OnClickListener {

    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;
    static int estado1;
    static int estado2;
    static int estado3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_cocina, container, false);

        boton1 = myView.findViewById(R.id.l11);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l12);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l13);
        boton3.setOnClickListener(this);

        estado1 = ambiente.devuelveEstados(String.valueOf(boton1.getTag()));
        estado2 = ambiente.devuelveEstados(String.valueOf(boton2.getTag()));
        estado3 = ambiente.devuelveEstados(String.valueOf(boton3.getTag()));

        if(estado1 == 0){
            boton1.setBackgroundResource(foco_apagado);
        }else{
            boton1.setBackgroundResource(foco);
        }

        if(estado2 == 0){
            boton2.setBackgroundResource(foco_apagado);
        }else{
            boton2.setBackgroundResource(foco);
        }

        if(estado3 == 0){
            boton3.setBackgroundResource(foco_apagado);
        }else{
            boton3.setBackgroundResource(foco);
        }

        return myView;
    }

    @Override
    public void onClick(View v) {
        Log.i( "-----------------------", "BOTON LUZ COCINA");
        final String param = String.valueOf(v.getTag()) ;

        conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt) {
                CambiarEstadoDeLuz(param);
            }
        });

    }

    public void CambiarEstadoDeLuz(String tag){
        msg.echo("cambiando la luz"+tag);
        switch (tag){
            case "GP1A01":
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                } break;
            case "GP1A02":
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }break;

            case "GP1A03":
                if(estado3 == 0){
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                }else{
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }break;
        }

    }
}
