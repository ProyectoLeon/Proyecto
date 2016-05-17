package com.android.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FiguraIncompletaActivity extends AppCompatActivity {

    ImageView Figura;
    static final double PORC_PUNTO_CORRECTO_Y = 0.51;
    static final double PORC_PUNTO_CORRECTO_X = 0.55;
    static final double PORC_COTA_Y = 0.072;
    static final double PORC_COTA_X = 0.08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figura_incompleta);

        findViewById(R.id.IVFigura).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                double puntoCorrectoX = v.getWidth()* PORC_PUNTO_CORRECTO_X;
                double cotaX = v.getWidth()* PORC_COTA_X;
                double puntoCorrectoY = v.getHeight()* PORC_PUNTO_CORRECTO_Y;
                double cotaY = v.getHeight()* PORC_COTA_Y;
                if ((event.getX() > (puntoCorrectoX - cotaX) & event.getX() < (puntoCorrectoX + cotaX))& (event.getY() > (puntoCorrectoY - cotaY) & event.getY() < (puntoCorrectoY + cotaY))) {
                    Toast.makeText(getApplicationContext(),"Correcto!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        findViewById(R.id.IVFigura2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                double puntoCorrectoX = v.getWidth()* PORC_PUNTO_CORRECTO_X;
                double cotaX = v.getWidth()* PORC_COTA_X;
                double puntoCorrectoY = v.getHeight()* PORC_PUNTO_CORRECTO_Y;
                double cotaY = v.getHeight()* PORC_COTA_Y;
                if ((event.getX() > (puntoCorrectoX - cotaX) & event.getX() < (puntoCorrectoX + cotaX))& (event.getY() > (puntoCorrectoY - cotaY) & event.getY() < (puntoCorrectoY + cotaY))) {
                    Toast.makeText(getApplicationContext(),"Correcto2!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
