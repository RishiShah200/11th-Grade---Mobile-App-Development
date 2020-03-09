package com.example.posttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button run;
    Button launch;
    ConstraintLayout constraintLayout;

    int tempID;

    ArrayList<Integer> randomColor;

    int randomNumber;

    TextView name;

    String radioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radiogroup);
        run = findViewById(R.id.run);
        launch = findViewById(R.id.launch);
        constraintLayout = findViewById(R.id.constraintLayout);

        randomColor = new ArrayList<Integer>();

        randomColor.add(Color.RED);
        randomColor.add(Color.BLUE);
        randomColor.add(Color.GREEN);

        name = findViewById(R.id.name);

        randomNumber = (int)(Math.random()*3);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.toast){
                    Toast.makeText(MainActivity.this, "Toast Selected", Toast.LENGTH_SHORT).show();
                    radioText = "The Toast Radio Button Was Selected";
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.color){
                    constraintLayout.setBackgroundColor(randomColor.get(randomNumber));
                    radioText = "The Color Radio Button Was Selected";
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.uppercase){
                    name.setText(name.getText().toString().toUpperCase());
                    radioText = "The Uppercase Radio Button Was Selected";
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tempID = checkedId;
            }
        });

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // run.performClick();
                Intent intent = new Intent (MainActivity.this,OtherActivity.class);
                intent.putExtra("Value",radioText);
                startActivity(intent);
            }
        });

    }
}
