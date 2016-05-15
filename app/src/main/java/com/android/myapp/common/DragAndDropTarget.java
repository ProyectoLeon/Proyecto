package com.android.myapp.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.myapp.R;

/**
 * Created by Nico on 15/05/2016.
 */
public class DragAndDropTarget implements View.OnDragListener {

    private Context cont;

    public DragAndDropTarget(Context context) {
            cont = context;
    }

    private Drawable getEnterShape(){
        return cont.getResources().getDrawable(R.drawable.shape_droptarget);
    }
    private Drawable getNormalShape(){
        return cont.getResources().getDrawable(R.drawable.shape);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(getEnterShape());
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(getNormalShape());
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                //view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(getNormalShape());
            default:
                break;
        }
        return true;
    }
}
