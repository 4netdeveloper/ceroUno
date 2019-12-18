package com.desarrollo.cerouno.aparatos;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.ambientes.Bano;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;

interface IntCambiarLuz{
    void cambiarL(int id, Boolean estado) ;
}

public class Cajas extends Fragment  {

    //static String [] habitaciones = {"bano", "cocina", "comedor", "dormitorio1", "entrada", "living", "patio" };



    public void setLuces(ImageButton[] luces) {
        this.luces = luces;
    }

    public ImageButton [] luces;

    static String habitacion;
    static String luz;
    static String nodo, arrayAparato;

    protected ImageButton boton1;
    protected ImageButton boton2;
    protected ImageButton boton3;

    protected ImageButton[] botones = {boton1, boton2, boton3};

    protected int estado1;
    protected int estado2;
    protected int estado3;

    public static void setEstadoLuz(int estadoLuz) {
        Cajas.estadoLuz = estadoLuz;
    }

    Timer timer = new Timer();

    static int estadoLuz;


    public String getHabitacion() {
        return habitacion;
    }

    public  void setHabitacion(String habitacion) {
        Cajas.habitacion = habitacion;
    }


    public void setEstadoLuz () {

        conex.send("", "pregunta", habitacion, new conexion.onPostExecute() {
            @Override
   //se solicita el estado de las habitaciones de a traves de setHabitacion()
            public void recibirTexto(String txt, int estado) throws JSONException {
                procesarTexto(txt);
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setEstadoLuz();
                Log.i("REFRESCO  -->", String.valueOf(habitacion));
            }
        },1000);

    }

    public void procesarTexto(String txt)throws JSONException{
        int cont = 0;
        //final IntCambiarLuz st;
        //se obtiene una cadena de string que contiene un JSON
        Log.i("QUE SE RECIBE -->", txt);

        //se crea un array del tipo JSONy se guarda el valor de txt
        JSONArray jsonArray = new JSONArray(txt);

        //se recorre el array
        for (int i = 0; i < jsonArray.length(); i++) {
            //se crea un JSONObject para guardar los valores se guardan
            //en cada posicion del array
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            //a traves de setNodo se indica sobre que #esclavo se esta trabajando
            //y se verifica si coincide con alguna variable dentro el array
            arrayAparato = jsonObject.getString("ESTADO");
            Log.i("ARRAY --->", arrayAparato);
            //dentro del campo"ESTADO" se encuentra un array anidado
            //que se lo guarda dentro del un objeto JSONObject

            JSONObject jsonObject1 = (JSONObject) new JSONTokener(arrayAparato).nextValue();
            //@jsonObject1 puede devolver un valor boleano o String


            for (Iterator<String> it = jsonObject1.keys(); it.hasNext(); ) {
                String vtr = it.next();
                if (vtr.contains("luz")) {
                    Log.i("ENTRO IF -->", jsonObject1.getString(vtr));
                    Log.i("CONT IF -->", String.valueOf(cont));
                    cambiarL(cont, jsonObject1.getBoolean(vtr));
                    cont++;
                }
                Log.i("PROCESAR TXT -->", vtr);
            }

        }
    }

    public void cambiarL(int id, Boolean estado){
        /*
        Log.i("CAMBIAR -->", String.valueOf(estado));
        Log.i("ID -->", String.valueOf(id));

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
      */
    }
    protected int btnSts(@NotNull ImageButton boton, int IdImagDraw){
        boton.setBackgroundResource(IdImagDraw);
        return (foco==IdImagDraw)? 1 : 0 ;
    }


}










