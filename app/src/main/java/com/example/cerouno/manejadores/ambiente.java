package com.example.cerouno.manejadores;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.administrador.msg;
import com.example.cerouno.administrador.raspberry;

public class ambiente extends AppCompatActivity implements View.OnClickListener{

    static int[] BOTONESMENU = {
            R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano
            , R.id.amb_dormitorio, R.id.amb_dormitorio2,  //array de botones
            R.id.amb_patio};
    int i, j;
    int opcion = i;

    static raspberry rpi;
    static String PI_URL="http://192.168.155.14:8181";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PI_URL = conexion.urlDelCorePi();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);
        //setContentView(R.layout.activity_log_in);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */

        //TODO: verificando el estado de coneccion de la red.
        // RaspbyConnect rp = new RaspbyConnect(this);
        String usuario = getIntent().getExtras().getString("usuario");
        String usrhash = getIntent().getExtras().getString("hasusr");

        Object t =  getIntent().getExtras().get("rpi");
        if (rpi == null ) {
            if (t != null) {
                msg.msg(this, "raspberry cargarada desde configuracion.");
                rpi = (raspberry) t;
            } else {
                // fabricacion automatica obtenida del servidor raspberry:
                rpi = new raspberry();//,new usuario(usuario,usrhash),"http://192.168.155.10:8181" );
                rpi.setHashUsaroi(usrhash);
                // para prueba en hambiente local.
                rpi.setUrlRPI(PI_URL);
                rpi.setStatus("ConstruirCasa");

                rpi = raspberry.onConstruct(rpi);

            }

        }else{
            msg.echo("raspberry cargada.");
            //msg.var_dump(rpi.getRaspberryJsonObjectOriginator().toString());

                rpi = rpi.raspyJSONConverter(conexion.informacion(usrhash,PI_URL));

        }


        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */

        fragmentTransaction.add( R.id.ElContenido,rpi );


        //setContentView(R.layout.activity_ambiente);
        //findViewById(R.id.ElContenido).
        // bucle de creacion de ambientes:
        /*
        for(int ambiente : BOTONESMENU){
            // Console.echo("asignado:"+ambiente);
            // asignando el controlador del boton :
            findViewById(ambiente).setOnClickListener(this);
        }
        */

   }
    /*
    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }
    */

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
        startActivity(intent);
        int index=0;
        // buscar el id en el indice de botones para obtener su correlacion
        for(int t : BOTONESMENU){
            msg.echo("index:"+index+" view:"+v.getId()+" t="+t);
            if (v.getId() == t){
                break;
            }
            index++;
        }
        MenuSlideActivity.opcion = index + 1;

    }
}
