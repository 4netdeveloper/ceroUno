package com.example.cerouno;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;


public class Luces extends Fragment implements  View.OnClickListener{


    public Button bt_luz1;
    public Button bt_luz2;
    public Button bt_luz3;
    public Button bt_luz4;

    private int[] botonesLuces = {R.id.btn_tv, R.id.luz1,R.id.luz2};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_luces, container, false);


    }

    @Override
    public void onClick(View view) {


        }

    }

