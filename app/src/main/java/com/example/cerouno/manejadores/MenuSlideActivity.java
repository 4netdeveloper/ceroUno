package com.example.cerouno.manejadores;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

import com.example.cerouno.R;
import com.example.cerouno.ambientes.Bano;
import com.example.cerouno.ambientes.Cocina;
import com.example.cerouno.ambientes.Comedor;
import com.example.cerouno.ambientes.Dormitorio;
import com.example.cerouno.ambientes.Living;
import com.example.cerouno.ambientes.Patio;

public class MenuSlideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //variable para q aparezca el fragment elegido en la pantalla de aparatos
    public static int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slide);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (opcion){
            case 1: cargarFragmento(new Living()); break;
            case 2: cargarFragmento(new Cocina()); break;
            case 3: cargarFragmento(new Comedor()); break;
            case 4: cargarFragmento(new Bano());break;
            case 5: cargarFragmento(new Dormitorio());break;
            case 6: cargarFragmento(new Dormitorio());break;
            case 7: cargarFragmento(new Patio());break;

            default: cargarFragmento(new Living()); break;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        if (id == R.id.nav_living) {
            cargarFragmento(new Living());
        } else if (id == R.id.nav_cocina) {
            cargarFragmento(new Cocina());
        } else if (id == R.id.nav_comedor) {
            cargarFragmento(new Comedor());
        } else if (id == R.id.nav_bano) {
            cargarFragmento(new Bano());
        } else if (id == R.id.nav_dorm1) {
            cargarFragmento(new Dormitorio());
            Dormitorio.id = 1;
        } else if (id == R.id.nav_dorm2) {
            cargarFragmento(new Dormitorio());
            Dormitorio.id = 2;
        } else if (id == R.id.nav_patio) {
            cargarFragmento(new Patio());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarFragmento (Fragment fragmento){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction().replace(R.id.contenedor, fragmento);
        fragmentTransaction.commit();

    }

}
