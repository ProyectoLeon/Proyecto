package com.android.myapp;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.myapp.common.DragAndDropSource;
import com.android.myapp.common.DragAndDropTarget;

public class MatricesActivity extends Activity {

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrices);
        findViewById(R.id.imgOpcion1).setOnTouchListener(new DragAndDropSource());
        findViewById(R.id.imgOpcion2).setOnTouchListener(new DragAndDropSource());
        findViewById(R.id.imgOpcion3).setOnTouchListener(new DragAndDropSource());
        findViewById(R.id.imgOpcion4).setOnTouchListener(new DragAndDropSource());
        findViewById(R.id.bottomright).setOnDragListener(new DragAndDropTarget(this));
    }
}
