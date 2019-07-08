package com.example.cerouno.manejadores;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cerouno.R;

public class ambiente extends AppCompatActivity implements View.OnClickListener{

    static int[] BOTONESMENU = {R.id.amb_tv, R.id.amb_cocina, R.id.amb_comedor, R.id.amb_bano, R.id.amb_dormitorio, R.id.amb_dormitorio2,  //array de botones
            R.id.amb_patio};
    int i, j;
    int opcion = i;



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
}
