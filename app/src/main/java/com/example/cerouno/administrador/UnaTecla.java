package com.example.cerouno.administrador;

import android.content.Context;

public final class UnaTecla {
    private String nombre,dispositivo;
    private Context Contenedor;

    public UnaTecla(String dispositivo, Context Contener){
        this.Contenedor = Contenedor;
    }
    public void setTecla(String tecla){

    }
    public void soyPresionado(String tag){
        /* obtener el valor del tag de la tecla
            la cual contiene el string real de valor
         */
        RaspbyConnect r = new RaspbyConnect(Contenedor);
        r.send(tag);

    }
}
