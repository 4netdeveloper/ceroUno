package com.example.cerouno;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class ambiente extends Fragment {

    private final int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio,  //array de botones
            R.id.amb_dormitorio2, R.id.amb_patio, R.id.amb_extra};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View miMenu = inflater.inflate(R.layout.fragment_ambiente, container, false);

        ImageButton botonMenu;  //se almacena en esta variable, el valor de cada boton


        //se recorre el array
        for (int i=0; i<BOTONESMENU.length; i++){

            botonMenu = miMenu.findViewById(BOTONESMENU[i]);

            final int queBoton = i;     //para saber que boton se pulsó

            botonMenu.setOnClickListener(new View.OnClickListener() {       //se ponen los botones a la escucha
                @Override
                public void onClick(View view) {

                    Activity estaActividad = getActivity(); //asi sabemos en que ACTIVITY nos encontramos

                    ((ComunicaBoton)estaActividad).boton(queBoton); //se utiliza el metodo de la interfaz ComunicaBoton

                }
            });

        }









        return miMenu;
    }


}
