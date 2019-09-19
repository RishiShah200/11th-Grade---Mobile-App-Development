package com.example.buttonextrapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button button;
    Button swap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.id_button1);
        swap = findViewById(R.id.id_button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int col = (int)(Math.random()*3);
                switch(col){
                    case 0:
                        ((Button) v).setTextColor(Color.RED);
                        break;
                    case 1:
                        ((Button) v).setTextColor(Color.GREEN);
                        break;
                    case 2:
                        ((Button) v).setTextColor(Color.BLUE);
                        break;
                    default:
                        ((Button) v).setTextColor(Color.BLACK);
                        break;
                }
            }
        });



    }

    public void swapText(View view){
        String a = (String)(button.getText());
        String b = (String)(swap.getText());
        button.setText(b);
        swap.setText(a);
    }

}
