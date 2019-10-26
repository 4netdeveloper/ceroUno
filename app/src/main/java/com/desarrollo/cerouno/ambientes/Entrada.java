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

public class Entrada extends Cajas implements View.OnClickListener {


    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;

    static int estado1;
    static int estado2;
    static int estado3;

    private ImageButton botones [] = {boton1, boton2, boton3};

    public ImageButton portonA;
    public ImageButton portonC;
    public ImageButton portonP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("entrada");
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_entrada, container, false);
        boton1 = myView.findViewById(R.id.l51);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l52);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l53);
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

        portonA = myView.findViewById(R.id.portonA1);
        portonA.setOnClickListener(this);
        portonC = myView.findViewById(R.id.portonC);
        portonC.setOnClickListener(this);
        portonP = myView.findViewById(R.id.portonP);
        portonP.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.portonA1) {
            Log.i("-----------------------", "ABRIR PORTON");
            conex.send(String.valueOf(v.getTag()), "A", "up");
        } else if (v.getId() == R.id.portonC) {
            Log.i("-----------------------", "CERRAR PORTON");
            conex.send(String.valueOf(v.getTag()), "A", "down");
        } else if (v.getId() == R.id.portonP) {
            Log.i("-----------------------", "PAUSA - PORTON");
            conex.send(String.valueOf(v.getTag()), "A", "pause");
        } else {
            Log.i("-----------------------", "BOTON LUZ ENTRADA");
            final String param = String.valueOf(v.getTag());

            conex.send(String.valueOf(v.getTag()), "A", "0", new conexion.onPostExecute() {
                @Override
                public void recibirTexto(String txt, int est) {
                    CambiarEstadoDeLuz(param);
                    setEstadoLuz();
                }
            });
        }

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


}
