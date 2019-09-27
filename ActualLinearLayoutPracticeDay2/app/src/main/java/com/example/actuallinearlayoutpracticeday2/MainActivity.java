package com.example.actuallinearlayoutpracticeday2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button red;
    Button blue;
    Button green;
    Button cyan;
    Button gray;
    Button magenta;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = findViewById(R.id.id_button1);
        blue = findViewById(R.id.id_button2);
        green = findViewById(R.id.id_button3);
        cyan = findViewById(R.id.id_button4);
        gray = findViewById(R.id.id_button5);
        magenta = findViewById(R.id.id_button6);
        layout = findViewById(R.id.id_mainlayout);
    }

    public void onClickRed (View view){
        cyan.setTextColor(Color.RED);
        gray.setTextColor(Color.RED);
        magenta.setTextColor(Color.RED);
    }

    public void onClickBlue (View view){
        cyan.setTextColor(Color.BLUE);
        gray.setTextColor(Color.BLUE);
        magenta.setTextColor(Color.BLUE);
    }

    public void onClickGreen (View view){
        cyan.setTextColor(Color.GREEN);
        gray.setTextColor(Color.GREEN);
        magenta.setTextColor(Color.GREEN);

    }

    public void onClickCyan (View view){
        layout.setBackgroundColor(Color.CYAN);
    }

    public void onClickGray (View view){
        layout.setBackgroundColor(Color.GRAY);
    }

    public void onClickMagenta (View view){
        layout.setBackgroundColor(Color.MAGENTA);
    }
}
