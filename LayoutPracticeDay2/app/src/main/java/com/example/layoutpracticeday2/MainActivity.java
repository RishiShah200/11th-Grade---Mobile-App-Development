package com.example.layoutpracticeday2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    Button a;
    Button b;
    Button c;

    boolean clickA = true;
    boolean clickB = true;
    boolean clickC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.id_button1);
        b  =findViewById(R.id.id_button2);
        c = findViewById(R.id.id_button3);
    }

   public void onClickA (View view){
        clickA = false;
       if(!clickA && !clickB && !clickC){
           a.setEnabled(false);
           b.setEnabled(false);
           c.setEnabled(false);
       }
    }
    public void onClickB (View view){
        clickB = false;
        if(!clickA && !clickB && !clickC){
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
        }
    }
    public void onClickC (View view){
        clickC = false;
        if(!clickA && !clickB && !clickC){
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
        }
    }
}
