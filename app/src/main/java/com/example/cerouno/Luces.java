package com.example.cerouno;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.IOException;


public class Luces extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    public ImageButton btn_luz1;
    public ImageButton btn_luz2;
    public ImageButton btn_luz3;
    public ImageButton btn_luz4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_luces);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_luz1 = findViewById(R.id.luz1);
        btn_luz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (linkeador.ira("gp?c=1")){
                        //todo ok
                    }else{
                        notificacion();
                    }
                }catch (IOException e){
                    //fallo otra cosa
                }
            }
        });

        btn_luz2 = findViewById(R.id.luz2);
        btn_luz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (linkeador.ira("gp?c=2")){
                        //todo ok
                    }else{
                        notificacion();
                    }
                }catch (IOException e){
                    //fallo otra cosa
                }
            }
        });

        btn_luz3 = findViewById(R.id.luz3);
        btn_luz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (linkeador.ira("gp?c=3")){
                        //todo ok
                    }else{
                        notificacion();
                    }
                }catch (IOException e){
                    //fallo otra cosa
                }
            }
        });

        btn_luz4 = findViewById(R.id.luz4);
        btn_luz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (linkeador.ira("gp?c=4")){
                        //todo ok
                    }else{
                        notificacion();
                    }
                }catch (IOException e){
                    //fallo otra cosa
                }
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_luces, container, false);
    }
}
