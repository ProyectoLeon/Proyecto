package com.android.myapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class Pantalla2Activity extends AppCompatActivity implements OnGesturePerformedListener {
    private GestureLibrary gestureLib;

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
       setContentView(R.layout.activity_pantalla2);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
       // toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
  //      setSupportActionBar(toolbar);                // Setting toolbar as the ActionBar with setSupportActionBar() call
//
        // Inflate your custom layout
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.action_bar,
                null);


        getSupportActionBar().setCustomView(actionBarLayout);
         getSupportActionBar().setDisplayShowCustomEnabled(true);

        final Context context = this.getApplicationContext();

        Button boton =(Button) getSupportActionBar().getCustomView().findViewById(R.id.boton_actionbar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Pantalla2Activity.this)
                        .setTitle("Popup")
                        .setMessage("Por favor seleccione opción")
                        .setPositiveButton("No Guardar y Salir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent mainIntent = new Intent(Pantalla2Activity.this, ExamenActivity.class);
                                Pantalla2Activity.this.startActivity(mainIntent);
                            }
                        })
                        .setNegativeButton("Retormar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setNeutralButton("3era opcion", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });



        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gestureLib.load()) {
            finish();
        }
        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestures);


      //  try {
               gestureOverlayView.addOnGesturePerformedListener(this);
      //  } catch(Exception ex) {

        //}

        //GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        //View inflate = getLayoutInflater().inflate(R.layout.activity_pantalla2, null);
        //gestureOverlayView.addView(inflate);
        //gestureOverlayView.addOnGesturePerformedListener(handleGestureListener);

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
       // Toolbar tb = (Toolbar) findViewById(R.id.tool_bar);
    //tb.inflateMenu(R.menu.menu_main);
        //tb.setOnMenuItemClickListener(
           //     new Toolbar.OnMenuItemClickListener() {
             //       @Override
               //     public boolean onMenuItemClick(MenuItem item) {
                 //       return onOptionsItemSelected(item);
                   // }
    //            });
//11        return true;
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_cancel: {
            Intent mainIntent = new Intent(Pantalla2Activity.this, ExamenActivity.class);
            Pantalla2Activity.this.startActivity(mainIntent);
        }
            case R.id.action_play: {
                return true;
            }
        }
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        for (Prediction prediction : predictions) {
            if (prediction.score > 5.0) {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }
    }
/*
    private OnGesturePerformedListener handleGestureListener = new OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureView,
                                       Gesture gesture) {

            ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

            // one prediction needed
            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                // checking prediction
                if (prediction.score > 1.0) {
                    // and action
                    Toast.makeText(Pantalla2Activity.this, prediction.name,
                            Toast.LENGTH_SHORT).show();
                }
            }

        }
    };*/
}
