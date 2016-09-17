package com.android.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class ExamenActivity extends AppCompatActivity {
    Button buttonFiguraInc;
    Button buttonClaves;
    Button buttonDragDrop;
    Button buttonCubos;
    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);
        //toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        //setActionBar(toolbar);

        buttonFiguraInc = (Button) findViewById(R.id.buttonFiguraInc);
        buttonFiguraInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ExamenActivity.this, FiguraIncompletaActivity.class);
                ExamenActivity.this.startActivity(mainIntent);
                ExamenActivity.this.finish();
            }
        });

        buttonClaves = (Button) findViewById(R.id.buttonClaves);
        buttonClaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ExamenActivity.this,Pantalla2Activity.class);
                ExamenActivity.this.startActivity(mainIntent);
                ExamenActivity.this.finish();
            }
        });

        buttonDragDrop = (Button) findViewById(R.id.buttonDragDrop);
        buttonDragDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ExamenActivity.this,MatricesActivity.class);
                ExamenActivity.this.startActivity(mainIntent);
                ExamenActivity.this.finish();
            }
        });

        buttonCubos = (Button) findViewById(R.id.buttoncubo);
        buttonCubos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ExamenActivity.this,CubosActivity.class);
                ExamenActivity.this.startActivity(mainIntent);
                ExamenActivity.this.finish();
            }
        });
    }
}
