package com.example.cerouno.ambientes;

// import android.support.v4.app.Fragment;
import android.os.Bundle;
// import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cerouno.R;
// import com.example.cerouno.aparatos.Televisor;

public class Living extends Fragment implements View.OnClickListener{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myView = inflater.inflate(R.layout.fragment_living, container, false);

        ImageButton boton = myView.findViewById(R.id.botonTv);
        boton.setOnClickListener(this);

        return myView;

    }

    @Override
    public void onClick(View v) {
        //cargarFragmento(new Televisor());
    }

    private void cargarFragmento (Fragment fragmento){
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.contenedor, fragmento).addToBackStack(null).commit();
    }


}
