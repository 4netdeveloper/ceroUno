package com.example.cerouno.administrador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;

import android.app.Fragment;

//import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cerouno.R;
import com.example.cerouno.ambientes.habitacion;
import com.example.cerouno.aparatos.Televisor;
import com.example.cerouno.manejadores.usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class raspberry extends Fragment {
    public String Status="ok";
    private conexion conex;
    private Context Cnt;
    private String hashUsaroi,urlRPI;
    private ArrayList<habitacion> habitacionAray;

    protected String dev,acc,val;
    private JSONObject raspberryJsonObjectOriginator = null;

    public static raspberry onConstruct(raspberry previa ){
        // metodo de auto construccion del objeto.

        conexion ctn = new conexion(null , previa.getHashUsaroi() , previa.getUrlRPI());

        raspberry resultado = ctn.raspyServer();

        resultado.setContexto( previa.getContext());

        resultado.setUrlRPI(previa.getUrlRPI());
        resultado.setHashUsaroi(previa.getHashUsaroi());

        // resultado.setHabitacionAray((ArrayList<habitacion>) rt.getHabitacionAray() )

        return resultado;

    }

/*
    public raspberry(Bundle bundle,Context contexto, usuario usuario, String url){

        super();

        this.Cnt=contexto;
        this.hashUsaroi=usuario.getHash();
        this.urlRPI=url;
        this.conex = new conexion(contexto,hashUsaroi,urlRPI);

    }
*/
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        // if (Status == "ConstruirCasa" ) ConstruirCasa();


        final ArrayList<LinearLayout> linearLayout = new ArrayList<LinearLayout>();
        //linearLayout..setOrientation(LinearLayout.HORIZONTAL);

        View respuesta = inflater.inflate(R.layout.activity_ambiente,parent,false);

        ArrayList<habitacion> hab = getHabitacionAray();
        LinearLayout tmp = new LinearLayout(this.getContext());
        int ind=0;
        int idDeR;
        int[] vr = {R.id.registro1,R.id.registro2,R.id.registro3,R.id.registro4};
        for(habitacion h : hab){
            if ((ind%2)==0) {
                tmp=null;
                tmp = new LinearLayout(this.getContext());
            }

            tmp.setOrientation(LinearLayout.HORIZONTAL);
            tmp.addView(h.getView());

            if ((ind%2)==1) {
                linearLayout.add(tmp);
            }
            idDeR= ((h.getIdValue()%2)+2*h.getIdValue());
            //respuesta.findViewById(vr[idDeR])
            // linearLayout.addView(h);
        }
        if ((ind%2)==0){
            // agregar el ultimo para que cierre.
            linearLayout.add(tmp);
        }

        //respueta.addChildrenForAccessibility((ArrayList<habitacion>) getHabitacionAray() );
        //inflater.inflate(R.layout.activity_ambiente , parent, false);
        return respuesta;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    /*
    public void ConstruirCasa(){
        onConstruct();
        /* Funcion para la construccion del domicilio. * /

        // responde un json de todas las configuraciones.
        msg.echo("raspberry: se procede a obtener datos del servidor:");
        msg.msg(Cnt,"raspberry: obteniendo los datos del servidor y aplicando los cambios.");

        raspberry rt = conex.raspyServer();

        msg.echo("raspberry: datos obtenidos asignando las habitaciones:");
        this.setHabitacionAray((ArrayList<habitacion>) rt.getHabitacionAray() );


    }
    */

    public boolean sendcmd(String dispositivo,String accion,String valor){

        return conex.send(dispositivo,accion,valor,false);
    }
    public raspberry setContexto(Context o){
        this.Cnt = o;
        return this;
    }
    public Context getContext(){
        // .getContext();
        //return getParentFragment().getActivity();
        return getActivity();
    }

    /*
    public conexion getConex() {
        return conex;
    }

    public void setConex(conexion conex) {
        this.conex = conex;
    }
    */

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getHashUsaroi() {
        return hashUsaroi;
    }

    public void setHashUsaroi(String hashUsaroi) {
        this.hashUsaroi = hashUsaroi;
    }

    public ArrayList<habitacion> getHabitacionAray() {
        return habitacionAray;
    }

    public habitacion getHabitacionIND(int id){
        return habitacionAray.get(id);
    }
    public habitacion getHabitacionByName(String nombre){

        for(int t=0 ; t < habitacionAray.size() ; t++){
            if (habitacionAray.get(t).getNombreHabitacion() == nombre){
                return habitacionAray.get(t);
            }
        }
        return null;

    }

    public String getUrlRPI() {
        return urlRPI;
    }

    public void setUrlRPI(String urlRPI) {
        this.urlRPI = urlRPI;
    }

    public void setHabitacionAray(ArrayList<habitacion> habitacionAray) {
        this.habitacionAray = habitacionAray;
    }

    public raspberry raspyJSONConverter(JSONObject raspberryJsonObject ){
        // devolver un objeto raspberry completo:
        this.raspberryJsonObjectOriginator = raspberryJsonObject;

        msg.echo("procesando datos:");
        class descriptor{

            public int id=0;
            public String tipo="";
            public String habitacion="";
            public String nombre="";
            public String posicion="";
            public String imagen="";
            public descriptor(JSONObject objetoJSON) {

                try {
                    id = objetoJSON.getInt("id");
                    tipo = objetoJSON.getString("tipo");
                    habitacion = objetoJSON.getString("habitacion");
                    nombre = objetoJSON.getString("nombre");
                    posicion = objetoJSON.getString("posicion");
                    imagen = objetoJSON.getString("imagen");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


        msg.echo("construccion de raspberry:");
        raspberry rasp = new raspberry();

        if (raspberryJsonObject != null ) {

            /*
             * TODO: ok se debe construir todos los objetos a partid de aqui.
             *
             * */
            int error=99;
            try {
                error = (int) raspberryJsonObject.get("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String status="false";
            try {
                status= (String) raspberryJsonObject.get("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Object respuesta=null;
            try {
                respuesta = raspberryJsonObject.get("respuesta");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (error == 0 && status == "ok"){
                if (respuesta != null ) {
                    msg.echo("cnt: construyendo:");

                    JSONObject listado = (JSONObject) respuesta;
                    // JSONObject desc = new JSONObject(descriptor.class);

                    msg.var_dump(listado);

                    descriptor desc = new descriptor(listado );
                    habitacion hab ;//= new habitacion(rasp,0,"");

                    for (int ind =0 ; ind < listado.length() ; ind++ ) {
                        msg.echo("cnt: construcciones: bucle");
                        try {
                            desc = new descriptor(listado.getJSONObject(ind+""));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // constructor de objetos:

                        if (rasp.getHabitacionByName(desc.habitacion) != null ){
                            // si la habitacion existe => un nuevo control a la misma.
                            rasp.getHabitacionByName(desc.habitacion).getArrayControles().add(
                                    (new Televisor())
                                            .setHabitacion(rasp.getHabitacionByName(desc.habitacion))
                                            .setTelevisor(desc.tipo)
                                            .setDispositivo(desc.nombre)
                            );

                        }else{
                            hab =  new habitacion( ).setPadre(rasp)
                                    .setIdValue(desc.id+0)
                                    .setNombreHabitacion( desc.habitacion+"");
                            //desc.id,desc.habitacion +""))
                            hab.getArrayControles().add(
                                    (new Televisor().setHabitacion(hab))
                                            .setTelevisor(desc.tipo)
                                            .setDispositivo(desc.nombre)
                            );
                            rasp.getHabitacionAray().add(hab);
                        }
                    }
                    /**     el objeto resultado debe tener una forma similar a esta:
                     *
                     *      "0":{"id":"1","tipo":"CONTROL0","habitacion":"living","nombre":"TV0","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "1":{"id":"2","tipo":"CONTROL0","habitacion":"Comedor","nombre":"TV1","posicion":"2","imagen":"[img:\"Cubiertos.png\"]"},
                     *      "2":{"id":"3","tipo":"CONTROL0","habitacion":"living","nombre":"DVD","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "3":{"id":"4","tipo":"CONTROL0","habitacion":"Comedor","nombre":"PR0","posicion":"2","imagen":"[img:\"Cubiertos.png\"]"},
                     *      "4":{"id":"5","tipo":"LUZ","habitacion":"living","nombre":"GP0","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "5":{"id":"6","tipo":"LUZ","habitacion":"living","nombre":"GP1","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "6":{"id":"7","tipo":"LUZ","habitacion":"living","nombre":"GP2","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "7":{"id":"8","tipo":"LUZ","habitacion":"living","nombre":"GP3","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "8":{"id":"15","tipo":"LUZ","habitacion":"Ba\u00f1o","nombre":"GP4","posicion":"3","imagen":"[img:\"ba\u00f1o.png\"]"},
                     *      "9":{"id":"16","tipo":"LUZ","habitacion":"DormitorioPrincipal","nombre":"GP5","posicion":"4","imagen":"[img:\"cama.png\"]"},
                     *      "10":{"id":"17","tipo":"LUZ","habitacion":"DormitorioPrincipal","nombre":"GP6","posicion":"4","imagen":"[img:\"cama.png\"]"},
                     *      "11":{"id":"18","tipo":"LUZ","habitacion":"DormitorioSecundario","nombre":"GP7","posicion":"5","imagen":"[img:\"cama.png\"]"
                     *
                     *
                     * */
                }

            }


        }else{
            // rts es nullo.
            return null;
        }
        return rasp;


    }

    public JSONObject getRaspberryJsonObjectOriginator() {
        return raspberryJsonObjectOriginator;
    }

    public raspberry setRaspberryJsonObjectOriginator(JSONObject raspberryJsonObjectOriginator) {
        this.raspberryJsonObjectOriginator = raspberryJsonObjectOriginator;
        return this;
    }
}
