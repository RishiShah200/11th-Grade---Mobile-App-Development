 package com.example.layoutquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button a;   //relative
    Button b;   //linear
    TextView c; //relative
    TextView d; //linear

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.id_button1);
        b = findViewById(R.id.id_button2);
        c = findViewById(R.id.id_textview1);
        d = findViewById(R.id.id_textview2);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.getText().equals("Not Clicked!")) {
                    c.setText("Clicked");
                }
                else if(c.getText().equals("Clicked")){
                    c.setText("Not Clicked!");
                }

            }
        });


        b.setOnClickListener(this);

    }

    @Override
     public void onClick (View v){
        if(d.getText().equals("Not Clicked!")){
            d.setText("Clicked");
        }
        else if(d.getText().equals("Clicked")){
            d.setText("Not Clicked!");
        }
    }

}
