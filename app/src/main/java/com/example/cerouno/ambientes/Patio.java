package com.example.cerouno.ambientes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cerouno.R;
import com.example.cerouno.manejadores.ambiente;

public class Patio extends Fragment implements View.OnClickListener {

    public ImageButton boton1;
    public ImageButton boton2;
    public ImageButton boton3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_patio, container, false);
        boton1 = myView.findViewById(R.id.l51);
        boton1.setOnClickListener(this);
        boton2 = myView.findViewById(R.id.l52);
        boton2.setOnClickListener(this);
        boton3 = myView.findViewById(R.id.l53);
        boton3.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l51:
                Log.i("-----------------------", "BOTON LUZ 1 PATIO");
                ambiente.recibeBotones(String.valueOf(v.getTag()),  "A", "0");
                break;
            case R.id.l52:
                Log.i("-----------------------", "BOTON LUZ 2 PATIO");
                ambiente.recibeBotones(String.valueOf(v.getTag()),   "A", "0");
                break;
            case R.id.l53:
                Log.i("-----------------------", "BOTON LUZ 3 PATIO");
                ambiente.recibeBotones(String.valueOf(v.getTag()),  "A", "0");
                break;
        }
    }
}
