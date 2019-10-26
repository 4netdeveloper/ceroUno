package com.desarrollo.cerouno.ambientes;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.aparatos.Cajas;
import com.desarrollo.cerouno.aparatos.Televisor;
import com.desarrollo.cerouno.manejadores.ambiente;

import org.jetbrains.annotations.NotNull;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Dormitorio extends Cajas implements View.OnClickListener{


    private TextView dormitorio;

    public static int id = 0;
    private String tag=  "Dormitorio:";
    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;

    private ImageButton botones [] = {boton1, boton2, boton3};

    /*
    static int estado1;
    static int estado2;
    static int estado3;
    static int estado4;
    */

    public ImageButton portonA1;
    public ImageButton portonC1;
    public ImageButton portonP1;

    public ImageButton portonA2;
    public ImageButton portonC2;
    public ImageButton portonP2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("dormitorio"+id);
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");

    }

    @Override
    public void cambiarL(int id, Boolean estado){


        //Log.i("CAMBIAR DORMITORIO --> "+id, String.valueOf(estado));
        Log.i(tag, "CAMBIARL:"+String.valueOf(id));
        int d = (estado) ? foco : foco_apagado ;

        switch (id) {
            case 0:
                btnSts(boton1, d);
                // estado1 = 0;
                break;
            case 1:
                btnSts(boton2, d);
                //if (estado) {
                //boton2.setBackgroundResource(foco);
                // estado2 = 0;
            /*}
            else
                boton2.setBackgroundResource(foco_apagado);
                estado2 = 1;
                break;

             */
            case 2:
                btnSts(boton3,d);
                break;
                //if (estado) {
                //boton3.setBackgroundResource(foco);
                // estado3 = 0;
            /*}
            else
                boton3.setBackgroundResource();
                estado3 = 1;
                break;
        }

             */
        }
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_dormitorio, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTvDorm1);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l31);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l32);
        boton2.setOnClickListener(this);

        /*
        if(id == 1){
            estado1 = ambiente.devuelveEstados("GP3A01");
            estado2 = ambiente.devuelveEstados("GP3A02");
            //estado3 = ambiente.devuelveEstados("PR3A01");
            //estado4 = ambiente.devuelveEstados("PR3A02");


        }else{
            estado1 = ambiente.devuelveEstados("GP3B01");
            estado2 = ambiente.devuelveEstados("GP3B02");
            //estado3 = ambiente.devuelveEstados("PR3A01");
            //estado4 = ambiente.devuelveEstados("PR3A02");
        }
        */

        dormitorio = myView.findViewById(R.id.tv_dormitorio);
        dormitorio.setText("Dormitorio " + id);

        // btnSts(boton1,( (estado1)?foco:foco_apagado ) );

        /*
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
    */
        portonA1 = myView.findViewById(R.id.portonA1);
        portonA1.setOnClickListener(this);
        portonC1 = myView.findViewById(R.id.portonC1);
        portonC1.setOnClickListener(this);
        portonP1 = myView.findViewById(R.id.portonP1);
        portonP1.setOnClickListener(this);

        portonA2 = myView.findViewById(R.id.portonA2);
        portonA2.setOnClickListener(this);
        portonC2 = myView.findViewById(R.id.portonC2);
        portonC2.setOnClickListener(this);
        portonP2 = myView.findViewById(R.id.portonP2);
        portonP2.setOnClickListener(this);


        return myView;
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.botonTvDorm1:
                cargarFragmento(new Televisor());
                if (id == 1){
                    Log.i( "-----------------------", "BOTON TELE DORMITORIO 1");
                    Televisor.dev = "TV3A01";}
                else if (id == 2){
                    Log.i( "-----------------------", "BOTON TELE DORMITORIO 2");
                    Televisor.dev = "TV3B01";
                }
                break;
            case R.id.l31:
                if (id == 1) {
                    Log.i("-----------------------", "BOTON LUZ 1 DORMITORIO 1");
                    estado1=btnSts(boton1, ( (false)? foco : foco_apagado ) );
                    /*
                    if(estado1 == 0){
                        boton1.setBackgroundResource(foco);
                        estado1 = 1;
                    }else{
                        boton1.setBackgroundResource(foco_apagado);
                        estado1 = 0;
                    }
                    */
                    conex.send("GP3A01", "A", "0");

                } else if (id == 2) {
                    Log.i("-----------------------", "BOTON LUZ 1 DORMITORIO 2");
                    if(estado1 == 0){
                        boton1.setBackgroundResource(foco);
                        estado1 = 1;
                    }else{
                        boton1.setBackgroundResource(foco_apagado);
                        estado1 = 0;
                    }
                    conex.send("GP3B01", "A", "0");

                }
                break;
            case R.id.l32:
                if (id == 1) {
                    Log.i("-----------------------", "BOTON LUZ 2 DORMITORIO 1");
                    if(estado2 == 0){
                        boton2.setBackgroundResource(foco);
                        estado2 = 1;
                    }else{
                        boton2.setBackgroundResource(foco_apagado);
                        estado2 = 0;
                    }
                    conex.send("GP3A02", "A", "0");
                } else if (id == 2) {
                    Log.i("-----------------------", "BOTON LUZ 2 DORMITORIO 2");
                    if(estado2 == 0){
                        boton2.setBackgroundResource(foco);
                        estado2 = 1;
                    }else{
                        boton2.setBackgroundResource(foco_apagado);
                        estado2 = 0;
                    }
                    conex.send("GP3B02", "A", "0");
                }
                break;

            case R.id.portonA1:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 1 ARRIBA - DORMITORIO 1");
                    conex.send("GP3A11", "A", "up");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 1 ARRIBA - DORMITORIO 2");
                    conex.send("GP3B21", "A", "up");
                }
                break;

            case R.id.portonA2:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 2 ARRIBA - DORMITORIO 1");
                    conex.send("GP3A12", "A", "up");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 2 ARRIBA - DORMITORIO 2");
                    conex.send("GP3B22", "A", "up");
                }
                break;

            case R.id.portonC1:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 1 CERRADO - DORMITORIO 1");
                    conex.send("GP3A11", "A", "down");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 1 CERRADO - DORMITORIO 2");
                    conex.send("GP3B11", "A", "down");
                }
                break;

            case R.id.portonC2:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 2 CERRADA - DORMITORIO 1");
                    conex.send("GP3A12", "A", "down");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 2 CERRADA - DORMITORIO 2");
                    conex.send("GP3B22", "A", "down");
                }
                break;

            case R.id.portonP1:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 1 PAUSA - DORMITORIO 1");
                    conex.send("GP3A11", "A", "pause");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 1 PAUSA - DORMITORIO 2");
                    conex.send("GP3B11", "A", "pause");
                }
                break;

            case R.id.portonP2:
                if (id == 1) {
                    Log.i("-----------------------", "PERSIANA 2 PAUSA - DORMITORIO 1");
                    conex.send("GP3A12", "A", "pause");
                } else if (id == 2) {
                    Log.i("-----------------------", "PERSIANA 2 PAUSA - DORMITORIO 2");
                    conex.send("GP3B22", "A", "pause");
                }
                break;
        }
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
