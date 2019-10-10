package com.desarrollo.cerouno.aparatos;

import android.util.Log;

import com.desarrollo.cerouno.administrador.conexion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static com.desarrollo.cerouno.manejadores.ambiente.conex;

public class Cajas  {


    String nodo;
    String elemento;
    String estado;


    public static void estadoAparatos (String valor) {

        switch (valor) {
            case "bano":

                //acc:"pregunta" - es para saber cual es el estado
                // val: "bano" - es para apuntar al ambiente
                conex.send("", "pregunta", "bano", new conexion.onPostExecute() {
                    Boolean estadoLuz;

                    @Override
                    public void recibirTexto(String txt, int estado) throws JSONException {

                        //se crea el array de JSON a partir del string txt
                        JSONArray jsonArray = new JSONArray(txt);

                        //se crea el objeto JSON dentro del array para llamar al indice 0
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String nodo = jsonObject.getString("NODO");
                            Log.i("QUE NODO? ---->", nodo);
                            //se recorre el array para ubicar el valor del objeto con nombre "ESTADO"
                            //y se lo guarda en la variable estadoAparato
                            String estadoAparato = jsonObject.getString("ESTADO");
                            Log.i("Y AHORA? ---->", estadoAparato);

                            //para obtener un objeto JSON dentro de un array anidado
                            JSONObject jsonObject1 = (JSONObject) new JSONTokener(estadoAparato).nextValue();

                            //se recorre el array anidado para ubicar el valor del objeto con nombre "luz0"
                            //y se lo guarda en la variable estado1
                            estadoLuz = jsonObject1.getBoolean("luz0");
                            Log.i("Y DESPUES? ---->", String.valueOf(estadoLuz));
                        }
                    }
                });
                break;
        }
    }




    String getNodo(){
        return nodo;
    }
    void setNodo(String nodo){
        this.nodo = nodo;
    }

    String getElemento(){
        return elemento;
    }

    void setElemento(String elemento){
        this.elemento = elemento;
    }

    String getEstado (){
        return estado;
    }

    void setEstado(String estado){
        this.estado=estado;
    }




}



