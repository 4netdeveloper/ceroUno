package com.desarrollo.cerouno.manejadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.administrador.conexion;
import com.desarrollo.cerouno.administrador.msg;
import com.desarrollo.cerouno.ambientes.Bano;
import com.desarrollo.cerouno.ambientes.Dormitorio;
import com.desarrollo.cerouno.aparatos.Cajas;

import static com.desarrollo.cerouno.R.drawable.foco;
import static com.desarrollo.cerouno.R.drawable.foco_apagado;


public class ambiente extends AppCompatActivity implements View.OnClickListener{


    static int[] BOTONESMENU = {R.id.amb_entrada, R.id.amb_patio, R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor,
            R.id.amb_bano, R.id.amb_dormitorio,  //array de botones
            R.id.amb_dormitorio2};
    public static conexion conex;
    int i, j;
    int opcion = i;
    static int status;
    public static int banderaStatus;

    Cajas cajas;

    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;

    private ImageButton botones [] = {boton1, boton2, boton3};
    
/* Arrays de tags de luces para determinar el estado inicial
* cada tag pertenece a un aparato  
*/
    static String [] lucesTag = 
            {"GP0A01", "GP0A02", "GP0A03","GPA1A01", "GPA1A02", "GPA1A03","GP2A01",
                    "GP3A01", "GP3202", "GP3B01", "GP3B02", "GP4A01", "GP4A02", "GP4A03",
                        "GP5A01", "GP5A02", "GP5A03", "GP5C01", "GP5C02", "GP5C03"};
    
    /* Arrays de estados de luces para determinar 
    * si esta encendido     
     */    
    static int [] estados = new int[lucesTag.length];

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

        conex=new conexion(this);
        //Login----------

        SharedPreferences prefLectura =
                getSharedPreferences("usuario", Context.MODE_PRIVATE);

        conex.setUser(prefLectura.getString("user", "nouser"));
        conex.setHash(prefLectura.getString("hash", "nouser"));
        banderaStatus = 1;



        status = conex.getStatus(new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                msg.echo("-------estadoamb" + estado);
                validarNuevoUsuario();

            }
        });




            // bucle de creacion de ambientes:
        for(int ambiente : BOTONESMENU){
            // Console.echo("asignado:"+ambiente);
            // asignando el controlador del boton :
            findViewById(ambiente).setOnClickListener(this);
        }

    }


    public void estadoUsuario(int respuesta){
            if (respuesta == 2){
                Toast.makeText(getApplicationContext(),
                        "Bienvenid@!!!", Toast.LENGTH_SHORT).show();
            }else if(respuesta == 1){
                SharedPreferences prefGuarda =
                        getSharedPreferences("usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefGuarda.edit();
                editor.putString("user","000000");
                editor.putString("hash", "000000");
                editor.apply();
                finish();
                Intent intent = new Intent(this, logIn.class);
                startActivity(intent);
            }
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
            msg.echo(v.getId() + "-");
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

    public void meterL(){


    }




    public void validarNuevoUsuario(){
        conex.getStatus(new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int estado) {
                msg.echo("recibido:" + estado);
                estadoUsuario(estado);

            }
        });
    }
    public static int devuelveEstados (String tag){

        int estado = 0;

        for(int i =0; i<lucesTag.length; i++){
            if(lucesTag[i] == tag){
                estado = estados[i];
            }
        }       return estado;
    }

    @Override
    protected void onStart() {
        super.onStart();
        for(int i=0; i<lucesTag.length; i++){
            estados[i]=0;
        }

    }
}



