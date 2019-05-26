package com.example.cerouno;

import android.content.Context;
import android.widget.ImageButton;

public class miBoton extends ImageButton {
    private int valor;

    public miBoton(Context context) {
        super(context);
    }

    public void setVariable(int v){
        this.valor = v;
    }
    public int getVariable(){
        return this.valor;
    }
}
