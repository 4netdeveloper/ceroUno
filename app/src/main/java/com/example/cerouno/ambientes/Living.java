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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cerouno.R;
import com.example.cerouno.aparatos.Televisor;
import com.example.cerouno.manejadores.ambiente;

import static com.example.cerouno.R.drawable.foco;
import static com.example.cerouno.R.drawable.foco_apagado;
import static com.example.cerouno.R.drawable.ic_persiana_48dp;
import static com.example.cerouno.R.drawable.ic_persiana_apagado_48dp;
import static com.example.cerouno.manejadores.ambiente.conex;


public class Living extends Fragment implements View.OnClickListener{


    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;

    static int estado1;
    static int estado2;
    static int estado3;

    static SeekBar persiana1;
    static SeekBar persiana2;
    static int estadoPersiana = 0;

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

        estado1 = ambiente.devuelveEstados(String.valueOf(boton1.getTag()));
        estado2 = ambiente.devuelveEstados(String.valueOf(boton2.getTag()));
        estado3 = ambiente.devuelveEstados(String.valueOf(boton3.getTag()));



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

        if(estado3 == 0){
            boton3.setBackgroundResource(foco_apagado);
        }else{
            boton3.setBackgroundResource(foco);
        }


        //seekbar
        persiana1 = myView.findViewById(R.id.seekBar_1);
        persiana1.setProgress(estadoPersiana);
        persiana1.setMax(100);
        manejoPersiana(persiana1);

        persiana2 = myView.findViewById(R.id.seekBar_2);
        persiana2.setProgress(estadoPersiana);
        persiana2.setMax(100);
        manejoPersiana(persiana2);



        return myView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonTv:
                cargarFragmento(new Televisor());
                Televisor.dev = "TV4A01";
                break;
            case R.id.l41:
                Log.i("-----------------------", "BOTON LUZ 1 LIVING");
                Log.i("-----------------------", String.valueOf(v.getTag()));
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0");
                break;

            case R.id.l42:
                Log.i("-----------------------", "BOTON LUZ 2 LIVING");
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0");
                break;

            case R.id.l43:
                Log.i("-----------------------", "BOTON LUZ 3 LIVING");
                if(estado3 == 0){
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                }else{
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0");
                break;


        }
    }

    private void manejoPersiana (SeekBar seekBar) {

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {

                    }
                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        switch (seekBar.getId()){
                            case R.id.seekBar_1:

                                    Log.i("---------------------", "PERSIANA 1, LIVING");

                                    estadoPersiana = persiana1.getProgress();
                                    conex.send("PR4A01", "A", String.valueOf(estadoPersiana));
                                break;

                            case  R.id.seekBar_2:
                                    Log.i("---------------------", "PERSIANA 2, LIVING");

                                    estadoPersiana = persiana2.getProgress();
                                    conex.send("PR4A02", "A", String.valueOf(estadoPersiana));

                                break;
                        }

                        if(seekBar.getProgress() == 0){
                            Toast.makeText(getContext(), "Persiana cerrada", Toast.LENGTH_SHORT).show();
                        }else if(seekBar.getProgress() == 100){
                            Toast.makeText(getContext(), "Persiana abierta", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Persiana abierta al " + seekBar.getProgress()+"%", Toast.LENGTH_SHORT).show();
                        }
                        estadoPersiana = seekBar.getProgress();

                    }
                });
    }

    private void cargarFragmento (Fragment fragmento){
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}





