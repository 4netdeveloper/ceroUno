package com.example.cerouno.administrador;

import android.util.Log;

// import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class configuraciones {
    static String Archivo="Configuraciones.obj";
    // la url tiene que contener el puerto correspondiente:
    private String[] clavesConfig = {"localhost","remotehost"
                ,"hasUsr"   ,"grupos"   ,"dipositivos"};
    private Object[] valoreConfig = { new String("http://192.168.0.100:8181/" )  ,new String()
                ,new String(), Arrays.asList(new config()), Arrays.asList(new config()) };
    private List<config> conf = new ArrayList<config>();
    public configuraciones(){

        Recuperar();

    }

    public String getConfig(String variable){
        // obtener un valor del archivo de configuracion
        String rt="";

        return rt;
    }
    public void setConfiguracion(String variable,String valor){

    }
    public void unset(){

    }
    static int echo (String valor){
        Console.echo( valor );
        return 0;
    }


    /* **************************
    *
    * guradar y recuperar datos:
    *
    * ***************************/


    private void Guardar()
    {
        /* TODO almacenamiento de informacion */
        // leer archivo:
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(Archivo);
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
            salida.writeObject("Configuracion guardarda el:"+( new Date() )+"\n");
            salida.writeObject(conf);
            salida.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            echo("un error al intentar guardar el archivo.");
        }

    }

    // @ SuppressWarnings("unchecked")
    private void Recuperar() {
        ObjectInputStream entrada;
        String str="";
        try {

            entrada = new ObjectInputStream(new FileInputStream(Archivo));
            str=(String) entrada.readObject();
            //Ejemplares=( ArrayList<Copia> ) entrada.readObject();
            // @SuppressWarnings("unused")
            List conf;
            conf = (ArrayList<config>) entrada.readObject();
            entrada.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            echo("archivo no creado.");
            inicializar(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            echo ("generado un error de exepcion");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            echo ("se a generado un error: no especificado.");
        }
        echo ("str:"+str+" recuperado.\n");

    }

    private void inicializar(){inicializar(false);};
    private void inicializar(boolean inicializa) {
        // si es la primera vez de la aplicacion no tiene
        // definido las variables y fallo la carga de datos
        int index = 0;
        if (inicializa){
            for (String key : clavesConfig) {

                // inicializar el arreglo
                config co = new config();
                co.setVal(key, valoreConfig[index]);
                co.noAsignado();
                conf.add(co);
                co = null;
                index++;
            }
        }else {
            for (String key : clavesConfig) {

                for (config c : conf) {
                    if (c.getKey() == key && !(c.getAsignado())) {
                        c.setVal(key, valoreConfig[index]);

                    }

                }
                index++;
            }
        } // fin del else de inicio.
    }
}
