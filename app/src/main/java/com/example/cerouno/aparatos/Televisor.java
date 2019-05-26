package com.example.cerouno.aparatos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.cerouno.R;


public class Televisor extends Fragment implements View.OnClickListener {

    public ImageView btn_input;
    public ImageView btn_power;
    public ImageView btn_mute;
    public ImageView btn_anterior;

    public Button btn_ch0;
    public Button btn_ch1;
    public Button btn_ch2;
    public Button btn_ch3;
    public Button btn_ch4;
    public Button btn_ch5;
    public Button btn_ch6;
    public Button btn_ch7;
    public Button btn_ch8;
    public Button btn_ch9;
    public Button btn_botonOk;

    public ImageButton btn_volumeUp;
    public ImageButton btn_volumeDw;
    public ImageButton btn_canalUp;
    public ImageButton btn_canalDw;

    public ImageButton btn_flechaUp;
    public ImageButton btn_flechaDw;
    public ImageButton btn_flechaRight;
    public ImageButton btn_flechaLeft;


    //private int[] botonesTv = {R.id.onOff_tv, R.i};


   // private admin admin = new admin("", "");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_televisor, container, false);

        //listeners(myView);

        btn_input = myView.findViewById(R.id.input_tv);
        btn_input.setOnClickListener(this);

        btn_power = myView.findViewById(R.id.onOff_tv);
        btn_power.setOnClickListener(this);

        btn_mute = myView.findViewById(R.id.mute);
        btn_mute.setOnClickListener(this);

        btn_anterior = myView.findViewById(R.id.can_ant);
        btn_anterior.setOnClickListener(this);

        btn_ch0 = myView.findViewById(R.id.canal0);
        btn_ch0.setOnClickListener(this);

        btn_ch1 = myView.findViewById(R.id.canal1);
        btn_ch1.setOnClickListener(this);

        btn_ch2 = myView.findViewById(R.id.canal2);
        btn_ch2.setOnClickListener(this);

        btn_ch3 = myView.findViewById(R.id.canal3);
        btn_ch3.setOnClickListener(this);

        btn_ch4 = myView.findViewById(R.id.canal4);
        btn_ch4.setOnClickListener(this);

        btn_ch5 = myView.findViewById(R.id.canal5);
        btn_ch5.setOnClickListener(this);

        btn_ch6 = myView.findViewById(R.id.canal6);
        btn_ch6.setOnClickListener(this);

        btn_ch7 = myView.findViewById(R.id.canal7);
        btn_ch7.setOnClickListener(this);

        btn_ch8 = myView.findViewById(R.id.canal8);
        btn_ch8.setOnClickListener(this);

        btn_ch9 = myView.findViewById(R.id.canal9);
        btn_ch9.setOnClickListener(this);

        btn_volumeUp = myView.findViewById(R.id.volumeUp);
        btn_volumeUp.setOnClickListener(this);

        btn_volumeDw = myView.findViewById(R.id.volumeDw);
        btn_volumeDw.setOnClickListener(this);

        btn_canalUp = myView.findViewById(R.id.canalUp);
        btn_canalUp.setOnClickListener(this);

        btn_canalDw = myView.findViewById(R.id.canalDw);
        btn_canalDw.setOnClickListener(this);

        btn_flechaUp = myView.findViewById(R.id.flechaUp);
        btn_flechaUp.setOnClickListener(this);

        btn_flechaDw = myView.findViewById(R.id.flechaDw);
        btn_flechaDw.setOnClickListener(this);

        btn_flechaLeft = myView.findViewById(R.id.flechaLeft);
        btn_flechaLeft.setOnClickListener(this);

        btn_flechaRight = myView.findViewById(R.id.flechaRight);
        btn_flechaRight.setOnClickListener(this);

        btn_botonOk = myView.findViewById(R.id.botonOk);
        btn_botonOk.setOnClickListener(this);

        return myView;
    }



    // metodo para mandar comandos al ADMIN

 /*   public void evento (int opcion){

        switch (opcion){
            //case R.id.input_tv: admin.setBoton(String.valueOf(opcion));
            break;
        }

    }*/

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }

    @Override
    public void onClick(View view) {

        //cargarFragmento(new Living());
    }


}





