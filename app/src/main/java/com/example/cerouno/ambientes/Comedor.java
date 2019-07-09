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
import com.example.cerouno.aparatos.Televisor;
import com.example.cerouno.manejadores.ambiente;

public class Comedor extends Fragment implements View.OnClickListener{

    public ImageButton boton1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_comedor, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTvCom);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l21);
        boton1.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonTvCom:
                cargarFragmento(new Televisor());
                Televisor.dev = "20";
                break;
            case R.id.l21:
                Log.i("-----------------------", "BOTON LUZ COMEDOR");
                ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
                break;
        }
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
