package com.desarrollo.cerouno.aparatos;

import android.util.Log;
import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.ambientes.Bano;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import static com.desarrollo.cerouno.manejadores.ambiente.conex;


public class Cajas  {

    //static String [] habitaciones = {"bano", "cocina", "comedor", "dormitorio1", "entrada", "living", "patio" };

    //static String [] luces = {"luz0", "luz1", "luz2", "luz3"};

    static String habitacion;
    static String luz;
    static String nodo, arrayAparato;
    static int estadoLuz;

    public static String getHabitacion() {
        return habitacion;
    }

    public static void setHabitacion(String habitacion) {
        Cajas.habitacion = habitacion;
    }

    public static String getLuz() {
        return luz;
    }

    public static void setLuz(String luz) {
        Cajas.luz = luz;
    }

    public static String getNodo() {
        return nodo;
    }

    public static void setNodo(String nodo) {
        Cajas.nodo = nodo;
    }


    public static int getEstadoLuz () {

            conex.send("", "pregunta", habitacion, new conexion.onPostExecute() {
                @Override

                //se solicita el estado de las habitaciones de a traves de setHabitacion()
                public void recibirTexto(String txt, int estado) throws JSONException {

                    //se obtiene una cadena de string que contiene un JSON
                    Log.i("QUE SE RECIBE -->", txt);

                    //se crea un array del tipo JSONy se guarda el valor de txt
                    JSONArray jsonArray = new JSONArray(txt);

                    //se recorre el array
                    for(int i=0; i<jsonArray.length();i++){

                        //se crea un JSONObject para guardar los valores se guardan
                        //en cada posicion del array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //a traves de setNodo se indica sobre que #esclavo se esta trabajando
                        //y se verifica si coincide con alguna variable dentro el array
                        if(jsonObject.getString("NODO").equals(nodo)) {
                            Log.i("NODO --->", nodo);

                            //si ese #esclavo coincide con el campo "NODO" incluido en el array
                            //se guarda el contenido del campo "ESTADO" dentro de la
                            //variable arrayAparato
                            arrayAparato = jsonObject.getString("ESTADO");
                            Log.i("ARRAY --->", arrayAparato);

                            //dentro del campo"ESTADO" se encuentra un array anidado
                            //que se lo guarda dentro del un objeto JSONObject
                            JSONObject jsonObject1 = (JSONObject) new JSONTokener(arrayAparato).nextValue();
                            //@jsonObject1 puede devolver un valor boleano o String
                            //se verifica si coincide el valor del campo obtenido del @jsonObject1 con
                            //el valor introducido por el metodo setLuz()
                            if (jsonObject1.getBoolean(luz)) {

                                //si el valor booleano es true
                                //el valor de la variable @estadoLuz es igual a 1
                                estadoLuz = 1;
                            } else if (!jsonObject1.getBoolean(luz)) {
                                estadoLuz = 0;
                            } else estadoLuz = Integer.parseInt(String.valueOf(jsonObject1));
                            //en caso de que el valor obtenido sea un String, se lo parcea a entero
                            Log.i("estadoLuzCajas --->", String.valueOf(estadoLuz));
                        }
                    }
                }
            }); Log.i("estadoLuz -->", String.valueOf(estadoLuz));
            return estadoLuz;
        }



    public static void setEstadoLuz(int estadoLuz) {
        Cajas.estadoLuz = estadoLuz;
    }


}










