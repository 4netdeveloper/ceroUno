package com.desarrollo.cerouno.aparatos;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.desarrollo.cerouno.manejadores.ambiente.conex;
import com.desarrollo.cerouno.R;

public class LucesLed extends Cajas implements View.OnClickListener {

    private ImageView mImageView;
    private TextView mResultTv;
    private View mColorView;

    private Bitmap bitmap;
    private String hex;

    public LucesLed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myView = inflater.inflate(R.layout.fragment_luces_led, container, false);


        mImageView = myView.findViewById(R.id.imageLed);
        mResultTv = myView.findViewById(R.id.resultTv);
        mColorView = myView.findViewById(R.id.colorView);

        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        //imageView Metodo OnTouchListener
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN  ||
                        event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = mImageView.getDrawingCache();

                    int pixel = bitmap.getPixel((int) event.getX(), (int)event.getY());

                    //se obtienen los valores RGB
                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //se obtienen los valores HEX
                    hex = Integer.toHexString(pixel);

                    //se cambia el fondo segun color elegido
                    mColorView.setBackgroundColor(Color.rgb (r,g,b));

                    //se ingresa los valores RGB y HEX en el TextView
                    mResultTv.setText("RGB: "+r +", "+g +", "+b +"\nHEX: #"+hex);

                    conex.send((String) mImageView.getTag(), "A", hex);
                    Log.i("HEXADECIMAL ---------->", hex);

                }
                return true;
            }
        });

        return myView;
    }

    @Override
    public void onClick(View view) {


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
