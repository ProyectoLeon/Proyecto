package com.android.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FiguraIncompletaActivity extends AppCompatActivity {

    ImageView Figura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figura_incompleta);

        Figura = (ImageView) findViewById(R.id.IVFigura);
        Figura.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                event.getX();
                event.getY();
                if ((event.getX()> 480 & event.getX() <640  )& (event.getY()>740 & event.getY()<850)) {
                    Toast.makeText(getApplicationContext(),"Correcto!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No!!! Incorrecto", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}
