package com.example.buttonquizpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button color;
    Button  swap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color = findViewById(R.id.id_button1);
        swap = findViewById(R.id.id_swap);

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = (int)(Math.random()*3);

                switch(rand){
                    case 0:
                        ((Button) v).setTextColor(Color.RED);
                        break;
                    case 1:
                        ((Button) v).setTextColor(Color.YELLOW);
                        break;
                    case 2:
                        ((Button) v).setTextColor(Color.BLUE);
                        break;
                }
            }
        });
    }

    public void swapText(View v){
        CharSequence a = color.getText();
        color.setText(swap.getText());
        swap.setText(a);
    }
}
