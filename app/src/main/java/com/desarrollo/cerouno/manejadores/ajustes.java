package com.desarrollo.cerouno.manejadores;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.aparatos.Cajas;


public class ajustes extends Cajas implements View.OnClickListener{



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_ajustes, container, false);

        return myView;
    }


    @Override
    public void onClick(View view) {

    }
}
