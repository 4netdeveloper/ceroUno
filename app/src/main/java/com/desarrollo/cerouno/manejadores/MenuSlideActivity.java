package com.desarrollo.cerouno.manejadores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.desarrollo.cerouno.R;
import com.desarrollo.cerouno.ambientes.Bano;
import com.desarrollo.cerouno.ambientes.Cocina;
import com.desarrollo.cerouno.ambientes.Comedor;
import com.desarrollo.cerouno.ambientes.Dormitorio;
import com.desarrollo.cerouno.ambientes.Entrada;
import com.desarrollo.cerouno.ambientes.Living;
import com.desarrollo.cerouno.ambientes.Patio;
import com.desarrollo.cerouno.aparatos.Cajas;
import com.google.android.material.navigation.NavigationView;

public class MenuSlideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Cajas cajas;

    //variable para q aparezca el fragment elegido en la pantalla de aparatos
    public static int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slide);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);





        //FragmentManager fragmentManager = getSupportFragmentManager();

        switch (opcion){
            case 1: cargarFragmento(new Entrada(), "entrada");
                break;
            case 2:
                cargarFragmento(new Patio(), "Patio");
                break;
            case 3:
                cargarFragmento(new Living(), "living");
                break;
            case 4:
                cargarFragmento(new Cocina(), "cocina");
                break;
            case 5:
                cargarFragmento(new Comedor(), "comedor");
                break;
            case 6:
                cargarFragmento(new Bano(), "bano");
                break;
            case 7:
                cargarFragmento(new Dormitorio(), "dormitorioprincipal");
                break;
            case 8:
                cargarFragmento(new Dormitorio(), "dormitoriosecundario");
                break;
            default: cargarFragmento(new Living(), "living"); break;
        }

        navigationView.getMenu().getItem(0).setChecked(true);

    }



    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if(count == 0){
            super.onBackPressed();
        }else{
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slide, menu);
        return true;
    }


    // método para el botón de ajustes
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            cargarFragmento(new ajustes());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //metodo para eleccion de fragment en el menu lateral

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_entrada){
            cargarFragmento(new Entrada(), "entrada");
        } else if (id == R.id.nav_patio) {
            cargarFragmento(new Patio(), "Patio");
        } else if (id == R.id.nav_living) {
            cargarFragmento(new Living(), "living");
        } else if (id == R.id.nav_cocina) {
            cargarFragmento(new Cocina(), "cocina");
        } else if (id == R.id.nav_comedor) {
            cargarFragmento(new Comedor(), "comedor");
        } else if (id == R.id.nav_bano) {
            cargarFragmento(new Bano(), "bano");
        } else if (id == R.id.nav_dorm1) {
            cargarFragmento(new Dormitorio(), "dormitorioprincipal");
            Dormitorio.id = 1;
        } else if (id == R.id.nav_dorm2) {
            cargarFragmento(new Dormitorio(), "dormitoriosecundario");
            Dormitorio.id = 2;
        } else if (id == R.id.nav_cerrar) {
            Log.i("----------------->", "BORRANDO SHARED PREFERENCES");
            SharedPreferences.Editor editor = getSharedPreferences("usuario", MODE_PRIVATE).edit();
            editor.clear().apply();
            //editor.putString("user", "00000");
            //editor.putString("hash", "00000");
            finishAffinity();
            ambiente.conex.setHash("");
            Intent intent = new Intent(getApplicationContext(), logIn.class);
            startActivity(intent);
            Log.i("----------------->", "BORRADO");
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarFragmento (Cajas fragmento, String nombreHab){
        fragmento.setHabitacion(nombreHab);
        fragmento.setEstadoLuz();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction().replace(R.id.contenedor, fragmento);
        fragmentTransaction.commit();


    }

   private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction().replace(R.id.contenedor, fragmento);
        fragmentTransaction.commit();


    }

}
