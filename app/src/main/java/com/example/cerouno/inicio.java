package com.example.cerouno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class inicio extends AppCompatActivity {

    String lista [] = {"Living", "Cocina", "Comedor", "Habitacion 1", "habitacion 2"};
    LinearLayout myLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        for (int i = 0; i < 20; i++){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);

            final int id_ = btn.getId();
            btn.setText("button " + id_);

            if( i < 10)
            {
                myLayout = findViewById(R.id.columna11);
                myLayout.addView(btn, params);
            }
            else
            {
                myLayout = findViewById(R.id.columna21);
                myLayout.addView(btn, params);
            }


            /*btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }*/
            }
        }
        /*    Button newButton = new Button(this);
            newButton.setId(i);
            newButton.setTag(lista[i]);
            newButton = findViewById(R.id.i);
            newButton.setText(lista[i]);


            myLayout = (LinearLayout) findViewById(R.id.contenHab);
            myLayout.addView(newButton);

            newButton.setLayoutParams(myLayout.getLayoutParams());
        }*/



    }

