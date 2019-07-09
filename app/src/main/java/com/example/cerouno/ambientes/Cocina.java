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
import com.example.cerouno.manejadores.ambiente;

public class Cocina extends Fragment implements View.OnClickListener {

    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_cocina, container, false);

        boton1 = myView.findViewById(R.id.l11);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l12);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l13);
        boton3.setOnClickListener(this);


        return myView;
    }

    @Override
    public void onClick(View v) {
        Log.i( "-----------------------", "BOTON LUZ COCINA");
        ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
    }
}
