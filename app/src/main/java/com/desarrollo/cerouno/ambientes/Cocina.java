package com.desarrollo.cerouno.ambientes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.administrador.msg;
import com.desarrollo.cerouno.aparatos.Cajas;
import com.desarrollo.cerouno.manejadores.ambiente;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Cocina extends Cajas implements View.OnClickListener {

    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;


    static int estado1;
    static int estado2;
    static int estado3;


    private ImageButton botones [] = {boton1, boton2, boton3};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("cocina");
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");
    }

    @Override
    public void cambiarL(int id, Boolean estado){

        Log.i("CAMBIAR COCINA -->", String.valueOf(estado));
        Log.i("ID COCINA-->", String.valueOf(id));

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
        View myView = inflater.inflate(R.layout.fragment_cocina, container, false);

        conex.send("", "pregunta", "cocina", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                Log.i("Que se recibe? ---->", txt);

            }
        });

        boton1 = myView.findViewById(R.id.l11);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l12);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l13);
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

        return myView;
    }

    @Override
    public void onClick(View v) {
        Log.i( "-----------------------", "BOTON LUZ COCINA");
        final String param = String.valueOf(v.getTag()) ;

        conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int est) {
                CambiarEstadoDeLuz(param);
                setEstadoLuz();
            }
        });

    }

    public void CambiarEstadoDeLuz(String tag){
        msg.echo("cambiando la luz"+tag);
        switch (tag){
            case "GP1A01":
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                } break;
            case "GP1A02":
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }break;

            case "GP1A03":
                if(estado3 == 0){
                    boton3.setBackgroundResource(foco);
                    estado3 = 1;
                }else{
                    boton3.setBackgroundResource(foco_apagado);
                    estado3 = 0;
                }break;
        }

    }
}
