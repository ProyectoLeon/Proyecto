package com.android.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamenActivity extends AppCompatActivity {
 Button buttonFiguraInc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        buttonFiguraInc = (Button) findViewById(R.id.buttonFiguraInc);
        buttonFiguraInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ExamenActivity.this, FiguraIncompletaActivity.class);
                ExamenActivity.this.startActivity(mainIntent);
                ExamenActivity.this.finish();
            }
        });
    }
}
