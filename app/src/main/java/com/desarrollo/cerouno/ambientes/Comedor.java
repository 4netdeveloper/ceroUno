package com.desarrollo.cerouno.ambientes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.aparatos.Cajas;
import com.desarrollo.cerouno.aparatos.Televisor;
import com.desarrollo.cerouno.manejadores.ambiente;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Comedor extends Fragment implements View.OnClickListener{

    public ImageButton boton1;
    static int estado1;

    public ImageButton portonA1;
    public ImageButton portonC1;
    public ImageButton portonP1;

    public ImageButton portonA2;
    public ImageButton portonC2;
    public ImageButton portonP2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_comedor, container, false);

        conex.send("", "pregunta", "comedor", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                Log.i("Que se recibe? ---->", txt);

            }
        });

        estado1 = Cajas.getEstadoLuz();

        ImageButton boton = myView.findViewById(R.id.botonTvCom);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l21);
        boton1.setOnClickListener(this);

        estado1 = ambiente.devuelveEstados(String.valueOf(boton1.getTag()));

        if(estado1 == 0){
            boton1.setBackgroundResource(foco_apagado);
        }else{
            boton1.setBackgroundResource(foco);
        }

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
        switch (v.getId()) {
            case R.id.botonTvCom:
                cargarFragmento(new Televisor());
                Televisor.dev = "TV2A01";
                break;
            case R.id.l21:
                Log.i("-----------------------", "BOTON LUZ COMEDOR");
                conex.send(String.valueOf(v.getTag()), "A", "0");
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                }
                break;

            case R.id.portonA1:
                Log.i("-----------------------", "PERSIANA 1 ARRIBA");
                conex.send(String.valueOf(v.getTag()), "A", "up");
                break;

            case R.id.portonC1:
                Log.i("-----------------------", "PERSIANA 1 ABAJO");
                conex.send(String.valueOf(v.getTag()), "A", "down");
                break;

            case R.id.portonP1:
                Log.i("-----------------------", "PERSIANA 1 PAUSA");
                conex.send(String.valueOf(v.getTag()), "A", "pause");
                break;

            case R.id.portonA2:
                Log.i("-----------------------", "PERSIANA 2 ARRIBA");
                conex.send(String.valueOf(v.getTag()), "A", "up");
                break;

            case R.id.portonC2:
                Log.i("-----------------------", "PERSIANA 2 ABAJO");
                conex.send(String.valueOf(v.getTag()), "A", "down");
                break;

            case R.id.portonP2:
                Log.i("-----------------------", "PERSIANA 2 PAUSA");
                conex.send(String.valueOf(v.getTag()), "A", "pause");
                break;
        }
    }


    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
