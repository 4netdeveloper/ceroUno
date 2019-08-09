package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.administrador.msg;
import com.example.cerouno.manejadores.ambiente;

import static com.example.cerouno.R.drawable.foco;
import static com.example.cerouno.R.drawable.foco_apagado;
import static com.example.cerouno.manejadores.ambiente.conex;

public class Entrada extends Fragment implements View.OnClickListener {



    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;

    static int estado1;
    static int estado2;
    static int estado3;

    static SeekBar persiana1;
    //static SeekBar persiana2;
    static int estadoPersiana = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_entrada, container, false);
        boton1 = myView.findViewById(R.id.l51);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l52);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l53);
        boton3.setOnClickListener(this);

        estado1 = ambiente.devuelveEstados(String.valueOf(boton1.getTag()));
        estado2 = ambiente.devuelveEstados(String.valueOf(boton2.getTag()));
        estado3 = ambiente.devuelveEstados(String.valueOf(boton3.getTag()));

        /*if(estado1 == 0){
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
        */

        //seekbar
        persiana1 = myView.findViewById(R.id.seekBar_1);
        persiana1.setProgress(estadoPersiana);
        persiana1.setMax(100);
        manejoPersiana(persiana1);

        return myView;
    }

    @Override
    public void onClick(View v) {
        Log.i( "-----------------------", "BOTON LUZ ENTRADA");
        final String param = String.valueOf(v.getTag()) ;

        conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int est) {
                CambiarEstadoDeLuz(param);
            }
        });

    }

    public void CambiarEstadoDeLuz(String tag){
        msg.echo("cambiando la luz"+tag);
        switch (tag){
            case "GP5A01":
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                } break;
            case "GP5A02":
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }break;

            case "GP5A03":
                if(estado3 == 0){
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                }else{
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }break;
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

                                    Log.i("---------------------", "PORTON DE ENTRADA");

                                    estadoPersiana = persiana1.getProgress();
                                    conex.send("GP5B01", "A", String.valueOf(estadoPersiana));
                                break;
                        }

                        if(seekBar.getProgress() == 0){
                            Toast.makeText(getContext(), "Portón cerrado", Toast.LENGTH_SHORT).show();
                        }else if(seekBar.getProgress() == 100){
                            Toast.makeText(getContext(), "Portón abierto", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Portón abierto al " + seekBar.getProgress()+"%", Toast.LENGTH_SHORT).show();
                        }
                        estadoPersiana = seekBar.getProgress();

                    }
                });
    }
}
