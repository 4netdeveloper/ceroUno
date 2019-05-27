package com.example.cerouno.administrador;

import android.content.Context;

public class RaspbyConnect {

    /*

    String laurl = destino + puerto + "/index.php/" + dispositivo + "/" + "ac?id="+hasUsuario+"&par="+boton;

    * herramienta para la comunicacion con las raspberry.
    * */
    configuraciones Conf= new configuraciones();

    String host;
    String userHash;
    String nombreEquipo;
    private String estadoRaspberry;
    private miwebService Navegador ;
    String equipo;

    public RaspbyConnect(Context Contexto){


        host=(String) (Conf.getConfig("url"));
        userHash=(Conf.getConfig("hasUsr"));

        /* constructor del objeto de comunicacion*/
        Navegador = new miwebService(  "http://172.18.18.100:8181" , Contexto);

    }

    public void setEquipo(){

    }
    public void setDispositivo() {
        setDispositivo("");
    }

    public void setDispositivo(String equipo){
        this.equipo=equipo;
    }

    public Boolean send(String tecla){
        String laurl =  "/index.php/" + equipo + "/" + "ac?id="+userHash+"&par=" + tecla ;
        return ira(laurl);
    }

    /*** *************************************
     *
     * el navegador propiamente dicho:
     *
     ******************************************/

    public boolean ira(String strurl)  {
        Boolean rt=false;
        switch(estadoRaspberry){
            case "onLan": rt = Navegador.ira(strurl); break;
            case "internet": break;
            case "offline" : Console.echo("no hay servicio de red dispoinible.");break;
            default: break;
        }
        return rt;

    }



}
