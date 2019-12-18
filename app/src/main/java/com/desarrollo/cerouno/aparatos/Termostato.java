package com.desarrollo.cerouno.aparatos;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.desarrollo.cerouno.R;

import static com.desarrollo.cerouno.manejadores.ambiente.conex;

/**
 *
 */
public class Termostato extends Cajas implements View.OnClickListener{

    public static String dev;
    private static int temperatura;

    public static int getTemperatura() {
        Log.i("getTEMPERATURA -->", String.valueOf(temperatura));
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    private TextView mostrarPorcentaje;
    private SeekBar seekBar;



    public Termostato() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =  inflater.inflate(R.layout.fragment_termostato, container, false);


        mostrarPorcentaje = (TextView)myView.findViewById(R.id.tv_porcentaje);

        // SeekBar
        seekBar = (SeekBar)myView.findViewById(R.id.seekBar);
        // Valor Inicial
        seekBar.setProgress(temperatura);
        /*
        falta hacer la consulta de la temperatura en la raspi
         */
        mostrarPorcentaje.setText(seekBar.getProgress()+"ºC");

        seekBar.setMin(15);

        // Valot Final
        seekBar.setMax(45);

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        mostrarPorcentaje.setText(String.valueOf(progress)+"ºC");
                        setTemperatura(seekBar.getProgress());
                    }
                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        mostrarPorcentaje.setText(seekBar.getProgress() + "ºC");
                        setTemperatura(seekBar.getProgress());
                    }
                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mostrarPorcentaje.setText(seekBar.getProgress() + "ºC");
                        setTemperatura(seekBar.getProgress());


                        conex.send(String.valueOf(mostrarPorcentaje.getTag()), "A", String.valueOf(mostrarPorcentaje));
                        Log.i("TEMPERATURA ---------->", String.valueOf(seekBar.getProgress()));
                    }
                });

        return myView;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
