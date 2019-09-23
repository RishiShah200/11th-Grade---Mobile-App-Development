package com.example.buttonquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button blue;
    Button red;
    Button changesize;
    CharSequence a;
    int lastValue = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blue = findViewById(R.id.id_blue);
        red = findViewById(R.id.id_red);
        changesize = findViewById(R.id.id_changesize);

       /* blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setTextColor(Color.BLUE);
                a = ((Button)v).getText();
                red.setText(a);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setTextColor(Color.RED);
            }
        });*/
    }

    public void onClickBlue(View view){
        ((Button)view).setTextColor(Color.BLUE);
        a = ((Button)view).getText();
        red.setText(a);
    }

    public void onClickRed(View view){
        ((Button)view).setTextColor(Color.RED);
    }

    public void changeSize(View view){
        ((Button)view).setTextSize(TypedValue.COMPLEX_UNIT_DIP,lastValue);
        lastValue = lastValue + 10;
    }
}
