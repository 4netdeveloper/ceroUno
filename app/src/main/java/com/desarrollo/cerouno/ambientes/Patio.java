package com.desarrollo.cerouno.ambientes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.administrador.conex.conexion;
import com.desarrollo.cerouno.administrador.conex.onPostExecute;
import com.desarrollo.cerouno.administrador.msg;
import com.desarrollo.cerouno.aparatos.Cajas;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Patio extends Cajas implements View.OnClickListener {

    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;

    static int estado1;
    static int estado2;
    static int estado3;

    private ImageButton[] botones = {boton1, boton2, boton3};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("patio");
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_patio, container, false);
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

        return myView;
    }


    @Override
    public void onClick(View v) {
        Log.i( "-----------------------", "BOTON LUZ PATIO");
        final String param = String.valueOf(v.getTag()) ;

        conex.send(String.valueOf(v.getTag()), "A", "0", new onPostExecute() {
            @Override
            public void recibirTexto(String txt, int est) {
                CambiarEstadoDeLuz(param);
            }
        });

    }

    public void CambiarEstadoDeLuz(String tag){
        msg.echo("cambiando la luz"+tag);
        switch (tag){
            case "GP5C01":
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                } break;
            case "GP5C02":
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }break;

            case "GP5C03":
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
