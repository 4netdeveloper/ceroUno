package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.aparatos.Televisor;
import com.example.cerouno.manejadores.ambiente;


public class Living extends Fragment implements View.OnClickListener{


    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_living, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTv);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l41);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l42);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l43);
        boton3.setOnClickListener(this);
        return myView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonTv:
                cargarFragmento(new Televisor());
                Televisor.dev = "40";
                break;
            case R.id.l41:
                Log.i("-----------------------", "BOTON LUZ 1 LIVING");
                Log.i("-----------------------", String.valueOf(v.getTag()));
                ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
                break;
            case R.id.l42:
                Log.i("-----------------------", "BOTON LUZ 2 LIVING");
                ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
                break;
            case R.id.l43:
                Log.i("-----------------------", "BOTON LUZ 3 LIVING");
                ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
                break;
        }
    }

    private void cargarFragmento (Fragment fragmento){
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }




}
