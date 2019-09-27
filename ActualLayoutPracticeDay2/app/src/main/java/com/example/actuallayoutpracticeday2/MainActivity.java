package com.example.actuallayoutpracticeday2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button topleft;
    Button topright;
    Button bottomleft;
    Button bottomright;
    Button centerbutton;
    TextView topleftview;
    TextView toprightview;
    TextView bottomleftview;
    TextView bottomrightview;
    TextView centerview;
    boolean clicka = true;
    boolean clickb = true;
    boolean clickc = true;
    boolean clickd = true;
    boolean clicke = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topleft = findViewById(R.id.id_topleftbutton);
        topright = findViewById(R.id.id_toprightbutton);
        bottomleft = findViewById(R.id.id_bottomleftbutton);
        bottomright = findViewById(R.id.id_bottomrightbutton);
        centerbutton = findViewById(R.id.id_centerbutton);
        topleftview = findViewById(R.id.id_toplefttextview);
        toprightview = findViewById(R.id.id_toprighttextview);
        bottomleftview = findViewById(R.id.id_bottomlefttextview);
        bottomrightview = findViewById(R.id.id_bottomrighttextview);
        centerview = findViewById(R.id.id_centertextview);
    }

    public void onClicka(View view){
        if(clicka){
            topleftview.setText("ON");
            clicka = false;
        }
        else{
            topleftview.setText("OFF");
            clicka = true;
        }
    }
    public void onClickb(View view){
        if(clickb){
            toprightview.setText("ON");
            clickb = false;
        }
        else{
            toprightview.setText("OFF");
            clickb = true;
        }

    }

    public void onClickc(View view){
        if(clickc){
            bottomleftview.setText("ON");
            clickc = false;
        }
        else{
            bottomleftview.setText("OFF");
            clickc = true;
        }

    }
    public void onClickd(View view){
        if(clickd){
            bottomrightview.setText("ON");
            clickd = false;
        }
        else{
            bottomrightview.setText("OFF");
            clickd = true;
        }

    }
    public void onClicke(View view){
        if(clicke){
            centerview.setText("ON");
            clicke = false;
        }
        else{
            centerview.setText("OFF");
            clicke = true;
        }

    }
}
