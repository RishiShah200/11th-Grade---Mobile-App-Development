package com.example.buttonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.id_button);
        button2 = findViewById(R.id.id_button2);
    }

    public void colorChange (View view){
        int r = (int)(Math.random()*255)+0;
        int g = (int)(Math.random()*255)+0;
        int b = (int)(Math.random()*255)+0;
        button.setTextColor(Color.rgb(r,g,b));
    }

    public void swapText (View view){
        cnt++;
       if(cnt%2==0){
           button.setText("not Color");
           button2.setText("Color");
       }
       else{
           button.setText("Color");
           button2.setText("not Color");
       }
    }
}
