package com.example.cerouno.administrador;

import java.io.Serializable;

public class config implements Serializable {
    private String key ;
    private Object value;
    private boolean asignado = false;

    public config(String key,String value){
        this.value = value;
        this.key = key;
    }

    public config(){
        this.value = "";
        this.key = "";
    }

    public void setVal(String k,Object v){
        this.key = k;this.value= v;this.asignado = true;
    }
    public void noAsignado(){this.asignado = false;}
    public Boolean getAsignado(){
        return this.asignado;
    }

    public Object getVal(){return this.value;}

    public String getKey(){return this.key;}

}
