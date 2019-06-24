package com.example.cerouno.aparatos;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cerouno.R;
import com.example.cerouno.administrador.msg;
import com.example.cerouno.ambientes.habitacion;


@SuppressLint("ValidFragment")
public class Televisor extends Fragment implements View.OnClickListener {


    static int[] CONTROL0 = {
        R.id.input_tv,R.id.onOff_tv
        ,R.id.mute,R.id.can_ant
        ,R.id.canal0,R.id.canal1,R.id.canal2,R.id.canal3,R.id.canal4,R.id.canal5
        ,R.id.canal6,R.id.canal7,R.id.canal8,R.id.canal9,R.id.volumeUp,R.id.volumeDw
        ,R.id.canalUp,R.id.canalDw,R.id.flechaUp,R.id.flechaDw,R.id.flechaLeft,R.id.flechaRight,R.id.botonOk

    };
    static int[] GP = {R.id.luz_01,R.id.luz_02,R.id.luz_03};

    private int[] ControlSelcionado;

    private String contro; // identificador de control ej; GP0 o TV0 o TV3
    private String dispositivo;
    private habitacion habitacion;

/*    public Televisor( habitacion habitacion) {
        super();
        this.habitacion = habitacion;
    }
*/
    public Televisor setTelevisor(String control)
    {
        this.contro = control;
        return this;
    }
    public Televisor setDispositivo(String dispositivo){
        this.dispositivo = dispositivo;
        return this;
    }
   // private admin admin = new admin("", "");

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View myView;

        switch(contro.substring(0,2)) {
            case "TV":
                //listeners(myView);
                 myView = inflater.inflate(R.layout.fragment_televisor, container, false);
                 ControlSelcionado = CONTROL0;
                for (int t : CONTROL0) {
                    myView.findViewById(t).setOnClickListener(this);
                };
                break;
            case "GP":
            default :
                ControlSelcionado = GP;
                myView = inflater.inflate(R.layout.fragment_patio, container, false);
                myView.findViewById(R.id.TextHabitacion).setTooltipText("-"+contro+"-");
                for (int t : GP){
                    myView.findViewById(t).setOnClickListener(this);
                    // myView.findViewById(t);
                }
        }

        return myView;
    }



    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }

    @Override
    public void onClick(View view) {

        //cargarFragmento(new Living());
        // Intent intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
        // startActivity(intent);
        int index=0;
        // buscar el id en el indice de botones para obtener su correlacion
        for(int t : ControlSelcionado ){
            msg.echo("index:"+index+" view:"+view.getId()+" t="+t);
            if (view.getId() == t){
                break;
            }
            index++;
        }
        msg.msg(this.getContext(),"boton:"+ ((String)view.getTag() ) );
        // el tag se coloca en el xml.
        habitacion.getPadre().sendcmd(dispositivo,contro,((String)view.getTag() ));

    }
    public Televisor setHabitacion(habitacion habitacion){
        this.habitacion = habitacion;
        return this;
    }
    public habitacion getHabitacion(){
        return this.habitacion;
    }

}





