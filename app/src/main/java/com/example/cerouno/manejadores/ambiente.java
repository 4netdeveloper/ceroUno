package com.example.cerouno.manejadores;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cerouno.R;
import com.example.cerouno.administrador.conexion;
import com.example.cerouno.ambientes.Dormitorio;

import android.widget.Toast;

public class ambiente extends AppCompatActivity implements View.OnClickListener{


    static int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio, R.id.amb_dormitorio2,  //array de botones
            R.id.amb_patio};
    public static conexion conex;
    int i, j;
    int opcion = i;

    static String [] lucesTag =
    {"GP0A01", "GP0A02", "GP0A03","GPA1A01", "GPA1A02", "GPA1A03","GP2A01",
            "GP3A01", "GP3202", "GP3B01", "GP3B02", "GP4A01", "GP4A02", "GP4A03", "GP5A01", "GP5A02", "GP5A03"};

    static int [] estados = new int[lucesTag.length];

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
        //Login----------
        SharedPreferences prefLectura = getSharedPreferences("usuario", Context.MODE_PRIVATE);

       conex.setUser(prefLectura.getString("user", ""));
        conex.setHash(prefLectura.getInt("hash", 0));

        int status = conex.getStatus();
        Log.i("---- AMBIENTE", String.valueOf(status));

        switch (status){
            case 0: Toast.makeText(this, "Servicio no encontrado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, logIn.class);
                    startActivity(intent);
                    break;

            case 1: SharedPreferences prefGuarda = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefGuarda.edit();
                    editor.putString("user","");
                    editor.putInt("hash", 0);
                    editor.apply();
                    finish();
                    intent = new Intent(this, logIn.class);
                    startActivity(intent);
                    break;

            case 2:
                break;

            default: break;
        }



        setContentView(R.layout.activity_ambiente);

        // bucle de creacion de ambientes:
        for(int ambiente : BOTONESMENU){
            // Console.echo("asignado:"+ambiente);
            // asignando el controlador del boton :
            findViewById(ambiente).setOnClickListener(this);
        }
        Log.i("-------------------", "On Create-------------");

    }



    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }
    public static conexion creaConexion(Context context){
        conex  = new conexion(context);
        return conex;
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

    public static int devuelveEstados (String tag){
        int estado = 0;

        for(int i =0; i<lucesTag.length; i++){
            if(lucesTag[i] == tag){
                estado = estados[i];
            }
        }       return estado;
    }



    //Recibe los datos de todos los botones y los redirecciona a conexion
    public static void recibeBotones (String dev, String acc, String val){
        Log.i ("--------------", "Llamando a conexion");
        //conex.setDev(dev).setAcc(acc).setVal(val).send( );
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("-------------------", "On Pause-------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("-------------------", "On Stop-------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("-------------------", "On Destroy-------------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("-------------------", "On Start-------------");

        for(int i=0; i<lucesTag.length; i++){
            estados[i]=0;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("-------------------", "On Resume-------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("-------------------", "On Restart-------------");
    }
}
