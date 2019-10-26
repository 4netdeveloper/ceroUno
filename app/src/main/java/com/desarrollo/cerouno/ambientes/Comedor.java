package com.desarrollo.cerouno.ambientes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.administrador.msg;
import com.desarrollo.cerouno.aparatos.Cajas;
import com.desarrollo.cerouno.aparatos.Televisor;
import com.desarrollo.cerouno.manejadores.ambiente;

import org.json.JSONException;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Comedor extends Cajas implements View.OnClickListener{

    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;

    private ImageButton botones [] = {boton1, boton2, boton3};

    static int estado1;
    static int estado2;
    static int estado3;

    public ImageButton portonA1;
    public ImageButton portonC1;
    public ImageButton portonP1;

    public ImageButton portonA2;
    public ImageButton portonC2;
    public ImageButton portonP2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("comedor");
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");

    }

    @Override
    public void cambiarL(int id, Boolean estado){

        Log.i("CAMBIAR COCINA -->", String.valueOf(estado));
        Log.i("ID COCINA -->", String.valueOf(id));

        switch (id){
            case 0 : if (estado) {
                boton1.setBackgroundResource(foco);
                estado1 = 0;
            }
            else
                boton1.setBackgroundResource(foco_apagado);
                estado1 = 1;
                break;
            case 1: if (estado) {
                boton2.setBackgroundResource(foco);
                estado2 = 0;
            }
            else
                boton2.setBackgroundResource(foco_apagado);
                estado2 = 1;
                break;
            case 2: if (estado) {
                boton3.setBackgroundResource(foco);
                estado3 = 0;
            }
            else
                boton3.setBackgroundResource(foco_apagado);
                estado3 = 1;
                break;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_comedor, container, false);

        conex.send("", "pregunta", "comedor", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                Log.i("Que se recibe? ---->", txt);

            }
        });


        ImageButton boton = myView.findViewById(R.id.botonTvCom);
        boton.setOnClickListener(this);
        boton1 = myView.findViewById(R.id.l21);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l22);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l23);
        boton3.setOnClickListener(this);




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
                Log.i("-----------------------", "BOTON LUZ 1 COMEDOR");
                if(estado2 == 0){
                    boton1.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
                    @Override
                    public void recibirTexto(String txt, int estado) throws JSONException {
                        setEstadoLuz();
                    }
                });
                break;

            case R.id.l22:
                Log.i("-----------------------", "BOTON LUZ 2 COMEDOR");
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
                    @Override
                    public void recibirTexto(String txt, int estado) throws JSONException {
                        setEstadoLuz();
                    }
                });
                break;

            case R.id.l23:
                Log.i("-----------------------", "BOTON LUZ 3 COMEDOR");
                if (estado3 == 0) {
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                } else {
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }
                conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
                    @Override
                    public void recibirTexto(String txt, int estado) throws JSONException {
                        setEstadoLuz();
                    }
                });
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

    public void CambiarEstadoDeLuz(String tag) {
        msg.echo("cambiando la luz" + tag);
        switch (tag) {
            case "GP2A01":
                if (estado1 == 0) {
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                } else {
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                }
                break;
            case "GP2A02":
                if (estado2 == 0) {
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                } else {
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }
                break;

            case "GP2A03":
                if (estado3 == 0) {
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                } else {
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }
                break;
        }



    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }
}
