package com.example.actuallayoutpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button a;   //topleft button
    Button b;   //topright button
    Button c;   //bottomleft button
    Button d;   //bottomright button
    Button e;   //center button
    TextView v;     //topleft
    TextView w;     //topright
    TextView x;     //bottomleft
    TextView y;     //bottomright
    TextView z;     //center
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.id_button);  //topleft button
        b = findViewById(R.id.id_button2); //topright button
        c = findViewById(R.id.id_button4); //bottomleft button
        d = findViewById(R.id.id_button3); //bottom right button
        e = findViewById(R.id.id_button5);   //center button
        v = findViewById(R.id.id_textView6);
        w = findViewById(R.id.id_textView2);
        x = findViewById(R.id.id_textView8);
        y = findViewById(R.id.id_textView9);
        z = findViewById(R.id.id_textView5);

    }

    public void onClicka(View view){

    }
    public void onClickb(View view){

    }
    public void onClickc(View view){

    }
    public void onClickd(View view){

    }
    public void onClicke(View view){

    }
}
