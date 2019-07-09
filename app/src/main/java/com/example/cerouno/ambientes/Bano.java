package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.manejadores.ambiente;


public class Bano extends Fragment implements View.OnClickListener {

    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_bano, container, false);

        boton1 = myView.findViewById(R.id.l01);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l02);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l03);
        boton3.setOnClickListener(this);

        return myView;
    }


    @Override
    public void onClick(View v) {
        Log.i("----------------------", "BOTON LUZ BAÑO");
        ambiente.recibeBotones(String.valueOf(v.getTag()), "0", "A");
    }
}
