package com.android.myapp;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Pantalla2Activity extends Activity implements OnGesturePerformedListener {
    private GestureLibrary gestureLib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gestureLib.load()) {
            finish();
        }
        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestures);
        gestureOverlayView.addOnGesturePerformedListener(this);

        //GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        //View inflate = getLayoutInflater().inflate(R.layout.activity_pantalla2, null);
        //gestureOverlayView.addView(inflate);
        //gestureOverlayView.addOnGesturePerformedListener(handleGestureListener);


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
