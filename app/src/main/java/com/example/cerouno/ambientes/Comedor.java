package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.aparatos.Televisor;
import com.example.cerouno.manejadores.ambiente;

import static com.example.cerouno.R.drawable.foco;
import static com.example.cerouno.R.drawable.foco_apagado;
import static com.example.cerouno.manejadores.ambiente.conex;

public class Comedor extends Fragment implements View.OnClickListener{

    public ImageButton boton1;
    static int estado1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_comedor, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTvCom);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l21);
        boton1.setOnClickListener(this);

        estado1 = ambiente.devuelveEstados(String.valueOf(boton1.getTag()));

        if(estado1 == 0){
            boton1.setBackgroundResource(foco_apagado);
        }else{
            boton1.setBackgroundResource(foco);
        }

        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonTvCom:
                cargarFragmento(new Televisor());
                Televisor.dev = "TV2A01";
                break;
            case R.id.l21:
                Log.i("-----------------------", "BOTON LUZ COMEDOR");
                conex.send(String.valueOf(v.getTag()), "A", "0");
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                }
                break;
        }
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
