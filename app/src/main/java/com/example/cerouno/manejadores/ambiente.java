package com.example.cerouno.manejadores;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.ambientes.Dormitorio;

public class ambiente extends AppCompatActivity implements View.OnClickListener{


    static int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio, R.id.amb_dormitorio2,  //array de botones
            R.id.amb_patio};
    int i, j;
    int opcion = i;

    /* ************************************************ * /
    / *
        A= Alterar/Cambiar, -> recive respuesta de Estado
        R= Pide Estado
        W= Escrive Valor
        ambientes:
        baño: 01, 02, etc
        cocina: 11, 12, etc
        comedor: 21, 22, etc
        dormitorio 1: 31a, 32a, etc
        dormitorio 2: 31b, 32b, etc
        dormitorio 3: 31c, 32c, etc
        living: 41, 42, etc
        patio: 51, 52, etc
        entrada: 61, 62, etc
        exteriores: 71, 72, etc* /
    / * ************************************************ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */


        //RaspbyConnect rp = new RaspbyConnect(this);


        /* ************************************************ * /
        / * manejadores de red y activaciones de servicio: * /
        / * ************************************************ */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente);

        // bucle de creacion de ambientes:
        for(int ambiente : BOTONESMENU){
            // Console.echo("asignado:"+ambiente);
            // asignando el controlador del boton :
            findViewById(ambiente).setOnClickListener(this);
        }

   }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),MenuSlideActivity.class);
        startActivity(intent);
        int index=0;
        if (v.getId() == R.id.amb_dormitorio){
            Dormitorio.id = 1;
            Log.i(String.valueOf(v.getId()),"------------------------");
        }else if (v.getId() == R.id.amb_dormitorio2){
            Dormitorio.id = 2;
            Log.i(String.valueOf(v.getId()),"------------------------");
        }
        // buscar el id en el indice de botones para obtener su correlacion
        for(int t : BOTONESMENU){
           // Console.echo("index:"+index+" view:"+v.getId()+" t="+t);
            if (v.getId() == t){

                break;
            }
            index++;
        }
        MenuSlideActivity.opcion = index + 1;


    }
    static conexion conex = new conexion();
    //Recibe los datos de todos los botones y los redirecciona a conexion
    public static void recibeBotones (String dev, String acc, String val){
        Log.i ("--------------", "Llamando a conexion");
        conex.send( dev, acc, val);
    }
}
