package com.example.cerouno.administrador;



public class configuraciones {
    // FIXME: 24/05/19 conseguir almacenar los datos de usuario y configuracion del nucleo en un archivo local.
    static String Archivo="Configuraciones.obj";
    // la url tiene que contener el puerto correspondiente:
    private String[] clavesConfig = {"localhost","remotehost"
                ,"hasUsr"   ,"grupos"   ,"dipositivos"};

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
        msg.echo( valor );
        return 0;
    }


    /* **************************
    *
    * guradar y recuperar datos:
    *
    * ***************************/


    private void Guardar(){

    }

    // @ SuppressWarnings("unchecked")
    private void Recuperar() {

    }

    private void inicializar(){inicializar(false);}

    private void inicializar(boolean inicializa) {
    }
}
