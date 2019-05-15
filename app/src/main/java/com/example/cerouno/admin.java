package com.example.cerouno;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class admin {

    private String[][] _dispositivos ;
    public static String destino, puerto, hasUsuario;
    private String dispositivo, habitacion;
    private boolean estado;
    private ArrayList botones;

    public admin(String habitacion, String dispositivo){

        // por el momento cargado manualmente:
        /*
        this._dispositivos[9][4] = new String();
        this._dispositivos[0]=["","","",""];
        */

        this.dispositivo = dispositivo;
        this.habitacion = habitacion;
        this.idDispositivo = this.buscar(dispositivo,habitacion);
    }

    public void setBoton(String t){
        botones.add(t);
    }
    public void pulsado(String boton){
        /* aqui pulso el boton */
        String laurl = destino + puerto + "/index.php/" + dispositivo + "/" + "ac?id="+hasUsuario+"&par="+boton;
        this.ira();
    }

    /****************************************
     * metodos auxiliares.
     * ^**************************************/
    private int buscar(String dispositivo,String habitacion){
        /*
        foreach{ this.dispositivos
        return
        */
        return 0;
    }

    /* **************************************
        methodos de configuracion
     *************************************** */

    public void setURL(String url){
        this.destino = url;
    }

    public void setPuerto(String puerto){
        this.puerto=puerto;
    }

    public void setHasUsuario(String has){
        this.hasUsuario=has;

    }

    public String getURL(){
        return this.destino + ":" + this.puerto + "/index.php/" ;
    }

    /* **************************************

        metodos de navegacion reales:

    ***************************************** */

    private boolean ira(String url) throws IOException {


        if (!mRPiAddress.equals("no")) {

            String mRPiPort = ":8181/";
            Log.d("linkeador", "ejecuto: url: " + mRPiAddress + mRPiPort + url);

            HttpURLConnection urlConnection = null;

            try {
                //construir datos a enviar
                String urla = mRPiAddress + mRPiPort + url;
                URL urlb = new URL(mRPiAddress + mRPiPort + url);

                urlConnection = (HttpURLConnection) urlb.openConnection();

                //activar metodo post
                urlConnection.setDoOutput(true);
                urlConnection.setFixedLengthStreamingMode(urla.getBytes().length);
                urlConnection.setRequestProperty(mRPiAddress + mRPiPort, url);

                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            } catch (EOFException e) {
                urlConnection.disconnect();
                Log.d("linkeador", "ERROR al ejecutra la solicitud: " + mRPiAddress + mRPiPort + url);
                return false;
            }
            return true; // todo se ejecuto correctamente.
        } else {
            // no se ha descubierto el host de la raspberry.
            Log.d("linkeador", "no esta el host o no ha sido descubierto aun");
            return false;
        }
    }

    private String protoCreator(String acion, String aparato, String variable) {
        String rt = "";
        //rt = (String) Base64.encodeToString("usr=" + usuario + "pas=" + passwd + "ac=" + acion + "ap=" + aparato + "va=" + variable);

        return "cmd=" + rt;
    }

    private String readStream(InputStream is) {
        // leyendo la respuesta de la raspberry.
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
