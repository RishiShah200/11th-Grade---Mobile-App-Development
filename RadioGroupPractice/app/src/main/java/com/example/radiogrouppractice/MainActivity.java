package com.example.radiogrouppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        RadioGroup radioGroup;
        ImageView imageView;
        String harrytext = "You have selected Harry!";
        String hermionetext = "You have selected Hermione";
        String rontext = "You have selected Ron";
        Toast myToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.id_radiogroup);
        imageView = findViewById(R.id.id_harrypotterlogo);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.id_harry) {
                    imageView.setImageResource(R.drawable.harrypotter);
                    myToast = Toast.makeText(MainActivity.this,harrytext,Toast.LENGTH_SHORT);
                }
                if(checkedId == R.id.id_hermione) {
                    imageView.setImageResource(R.drawable.hermione);
                    myToast = Toast.makeText(MainActivity.this,hermionetext,Toast.LENGTH_SHORT);
                }
                if(checkedId == R.id.id_ron) {
                    imageView.setImageResource(R.drawable.ron);
                    myToast = Toast.makeText(MainActivity.this, rontext, Toast.LENGTH_SHORT);
                }
                myToast.show();
            }
        });
    }
}
