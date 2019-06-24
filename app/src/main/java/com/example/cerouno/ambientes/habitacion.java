package com.example.cerouno.ambientes;

import android.app.Fragment;
import android.os.Bundle;

//import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cerouno.R;
import com.example.cerouno.administrador.raspberry;
import com.example.cerouno.aparatos.Televisor;

import java.util.ArrayList;

public class habitacion extends Fragment {
    ArrayList<Televisor> arrayControles;
    private int indice=0;
    private int id;
    private static int cantidadHabitaciones=0;
    private String nombreHabitacion;
    protected raspberry Padre;
/*
    public habitacion(raspberry Padre,int id,String nombre){
        this.id=id;
        this.nombreHabitacion=nombre;
        this.Padre = Padre;
    }
*/
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        //int habiente = getIntValue();

        return inflater.inflate(getIntValue() , parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    /*
    @Override
    public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
        return false;
    }
    */

    public habitacion setPadre(raspberry Padre){
        this.Padre = Padre;
        return this;
    }

    public int getIntValue(){
        int[] intvals = {
                R.layout.living,R.layout.cocina
                ,R.layout.comedor,R.layout.bau00f1o
                ,R.layout.dormitorioprincipal,R.layout.dormitoriosecundario
                ,R.layout.patio };
        return intvals[this.indice];
    }

    public raspberry getPadre(){
        return Padre;
    }

    public int getIdValue() {
        return id;
    }
    public habitacion setIdValue(int id){
        this.id = id;


        return this;
    }
    public String getNombreHabitacion() {
        return nombreHabitacion;
    }
    public habitacion setNombreHabitacion(String nombre){
        this.indice = cantidadHabitaciones;
        cantidadHabitaciones++;
        String[] nombres={"living","concina","comedor","ba\\u00f1o"
                ,"dormitorioprincipal","dormitoriosecundario"
                ,"patio"};
        int idx=nombre.indexOf(nombre);
        if (idx!=-1) {
            this.indice=idx;
        }
        this.nombreHabitacion = nombre;
        return this;
    }
    public ArrayList<Televisor> getArrayControles() {
        return arrayControles;
    }
    public void setArrayControles(ArrayList<Televisor> arrayControles) {
        this.arrayControles = arrayControles;
    }


}
