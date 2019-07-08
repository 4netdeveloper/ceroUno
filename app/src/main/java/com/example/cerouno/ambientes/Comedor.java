package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.aparatos.Televisor;

public class Comedor extends Fragment implements View.OnClickListener{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_comedor, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTvCom);
        boton.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        cargarFragmento(new Televisor());
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
