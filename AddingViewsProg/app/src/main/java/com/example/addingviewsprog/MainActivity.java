package com.example.addingviewsprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.id_layout);
        textView = new TextView(this);
        textView.setId(View.generateViewId());

        textView.setText("NO SCHEMING HERE BOIS!!!");
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT
                ,ConstraintLayout.LayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(params);
        constraintLayout.addView(textView);

        ConstraintSet constraintSet = new ConstraintSet();  //whatever constraints were already present will be cloned.
        constraintSet.clone(constraintLayout);

        constraintSet.connect(textView.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraintSet.connect(textView.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(textView.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
        constraintSet.connect(textView.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);

        constraintSet.setHorizontalBias(textView.getId(),0.5f);
        constraintSet.setVerticalBias(textView.getId(),0.25f);

        constraintSet.applyTo(constraintLayout);
    }
}
