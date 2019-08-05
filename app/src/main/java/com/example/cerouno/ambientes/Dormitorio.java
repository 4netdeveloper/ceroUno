package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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

public class Dormitorio extends Fragment implements View.OnClickListener{


    private TextView dormitorio;

    public static int id = 0;
    public ImageButton boton1;
    public ImageButton boton2;
    static int estado1;
    static int estado2;
    static int estado3;
    static int estado4;


    static SeekBar persiana1;
    static SeekBar persiana2;
    static int estadoPersiana = 0;

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
        persiana1 = myView.findViewById(R.id.persiana1);
        persiana1.setOnClickListener(this);
        persiana2 = myView.findViewById(R.id.persiana2);
        persiana2.setOnClickListener(this);
        persiana3 = myView.findViewById(R.id.persiana3);
        persiana3.setOnClickListener(this);
        persiana4 = myView.findViewById(R.id.persiana4);
        //persiana2B.setOnClickListener(this);

       // persiana4.setOnLongClickListener((OnLongClickListener) this);

*/
        if(id == 1){
            estado1 = ambiente.devuelveEstados("GP3A01");
            estado2 = ambiente.devuelveEstados("GP3A02");
            estado3 = ambiente.devuelveEstados("IR3A01");
            estado4 = ambiente.devuelveEstados("IR3A02");


        }else{
            estado1 = ambiente.devuelveEstados("GP3B01");
            estado2 = ambiente.devuelveEstados("GP3B02");
            estado3 = ambiente.devuelveEstados("IR3A01");
            estado4 = ambiente.devuelveEstados("IR3A02");
        }


        dormitorio = myView.findViewById(R.id.tv_dormitorio);
        dormitorio.setText("Dormitorio " + id);

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
        switch(v.getId()){
            case R.id.botonTvDorm1:
                cargarFragmento(new Televisor());
                if (id == 1){
                    Log.i( "-----------------------", "BOTON TELE DORMITORIO 1");
                    Televisor.dev = "TV3A01";}
                else if (id == 2){
                    Log.i( "-----------------------", "BOTON TELE DORMITORIO 2");
                    Televisor.dev = "TV3B01";}
                break;
            case R.id.l31:
                if (id == 1) {
                    Log.i("-----------------------", "BOTON LUZ 1 DORMITORIO 1");
                    if(estado1 == 0){
                        boton1.setBackgroundResource(foco);
                        estado1 = 1;
                    }else{
                        boton1.setBackgroundResource(foco_apagado);
                        estado1 = 0;
                    }
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

            /*case R.id.persiana1:
                if (id == 1){
                    Log.i("---------------------", "PERSIANA 1, DORMITORIO 1");
                    if(estado3 == 0){
                        persiana1.setBackgroundResource(ic_persiana_48dp);
                        estado3 = 1;
                    }else{
                        persiana1.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado3 = 0;
                    }
                    conex.send("IR3A01", "A", "0");
                }

                if(id == 2){
                    Log.i("---------------------", "PERSIANA 1, DORMITORIO 2");
                    if (estado3 == 0){
                        persiana1.setBackgroundResource(ic_persiana_48dp);
                        estado3 = 1;
                    }else{
                        persiana1.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado3 =0;
                    }
                    conex.send("IR3B01", "A", "0");
                }
                break;

            case R.id.persiana2:
                if(id == 1){
                    Log.i("--------------------", "PERSIANA 2, DORMITORIO 1");
                    if(estado4 == 0){
                        persiana2.setBackgroundResource(ic_persiana_48dp);
                        estado4 = 1;
                    }else{
                        persiana2.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado4 = 0;
                    }
                    conex.send("IR3A02", "A", "0");
                }

                if(id == 2) {
                    Log.i("--------------------", "PERSIANA 2, DORMITORIO 2");
                    if (estado4 == 0) {
                        persiana2.setBackgroundResource(ic_persiana_48dp);
                        estado4 = 1;
                    } else {
                        persiana2.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado4 = 0;
                    }
                    conex.send("IR3B02", "A", "0");
                }
                break;

            case R.id.persiana3:
                if(id == 1){
                    Log.i("--------------------", "PERSIANA 3, DORMITORIO 1");
                    if(estado5 == 0){
                        persiana3.setBackgroundResource(ic_persiana_48dp);
                        estado5 = 1;
                    }else{
                        persiana3.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado5 = 0;
                    }
                    conex.send("IR3A03", "A", "0");
                }
                break;

            case R.id.persiana4:
                if(id == 1){
                    Log.i("--------------------", "PERSIANA 3, DORMITORIO 2");
                    if(estado6 == 0){
                        persiana4.setBackgroundResource(ic_persiana_48dp);
                        estado6 = 1;
                    }else{
                        persiana4.setBackgroundResource(ic_persiana_apagado_48dp);
                        estado6 = 0;
                    }
                    conex.send("IR3A04", "A", "0");
                }
                break;
                */


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
                                if(id == 1){
                                    Log.i("---------------------", "PERSIANA 1, DORMITORIO 1");

                                    estadoPersiana = persiana1.getProgress();
                                    conex.send("IR3A01", "A", "0");
                                }

                                if(id == 2){
                                    Log.i("---------------------", "PERSIANA 1, DORMITORIO 2");

                                    estadoPersiana = persiana1.getProgress();
                                    conex.send("IR3B01", "A", "0");
                                }
                                break;

                            case  R.id.seekBar_2:
                                if(id == 1){
                                    Log.i("---------------------", "PERSIANA 2, DORMITORIO 1");

                                    estadoPersiana = persiana2.getProgress();
                                    conex.send("IR3A02", "A", "0");
                                }

                                if(id == 2){
                                    Log.i("---------------------", "PERSIANA 2, DORMITORIO 2");

                                    estadoPersiana = persiana2.getProgress();
                                    conex.send("IR3B02", "A", "0");
                                }
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
