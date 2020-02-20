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


/** Esta clase refiere al Fragment Baño, que controla las luces
 * @param boton1 se utiliza para prender luz 1 GP0A01
 * @param boton2 se utiliza para prender luz 2 GP0A02
 * @param boton3 se utiliza para prender luz 3 GP0A03
 */


public class Bano<botones> extends Cajas implements View.OnClickListener {



    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;


    static int estado1;
    static int estado2;
    static int estado3;


    private ImageButton[] botones = {boton1, boton2, boton3};




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHabitacion("bano");
        setLuces(botones);
        Log.i("onCreate -->", " ON CREATE");
    }


    /** Este es el método constructor
     * @param View es la clase que se utiliza para trabajar con el fragmento que se va a generar
     * @param myView es el objeto de la clase View con el que accede a los métodos de la clase View
     * @param Inflater es el método utilizado para "inflar" o generar el fragmento
     */

    @Override
    public void cambiarL(int id, Boolean estado){

        Log.i("CAMBIAR BANO -->", String.valueOf(estado));
        Log.i("ID BANO -->", String.valueOf(id));

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
        View myView = inflater.inflate(R.layout.fragment_bano, container, false);




        boton1 = myView.findViewById(R.id.l01);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l02);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l03);
        boton3.setOnClickListener(this);

        /**Los estados nos dicen si la luz está prendida o no
         * cambiando el ícono que se ve en la aplicación
         *
         *
         *
         */
        //estado1 = getEstado1();
        Log.i("estadoLuzBano -->", String.valueOf(estado1));

        //cambia icono cuando cambia activity, no dentro de ella (menu)


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
        Log.i("----------------------", "BOTON LUZ BAÑO " + v.getTag());

        final String param = String.valueOf(v.getTag()) ;

        conex.send(String.valueOf(v.getTag()), "A", "0", new onPostExecute() {
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
            case "GP0A01":
                if(estado1 == 0){
                    boton1.setBackgroundResource(foco);
                    estado1 = 1;
                }else{
                    boton1.setBackgroundResource(foco_apagado);
                    estado1 = 0;
                } break;
            case "GP0A02":
                if(estado2 == 0){
                    boton2.setBackgroundResource(foco);
                    estado2 = 1;
                }else{
                    boton2.setBackgroundResource(foco_apagado);
                    estado2 = 0;
                }break;

            case "GP0A03":
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



