package com.example.radioquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup aGroup;
    Button play;
    ImageView cpuimage;
    TextView total;
    TextView result;
    int userSelection;
    int cpuSelection;
    int sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aGroup = findViewById(R.id.id_radiogroup);
        play = findViewById(R.id.id_play);
        cpuimage = findViewById(R.id.id_cpuimage);
        total = findViewById(R.id.id_total);
        result = findViewById(R.id.id_result);

        aGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.id_one){
                    userSelection = 0;
                    cpuimage.setImageResource(R.drawable.selection);
                    total.setText("Total");
                    result.setText("Result");
                    userSelection = 1;
                }
                else if(checkedId == R.id.id_Two){
                    userSelection = 0;
                    cpuimage.setImageResource(R.drawable.selection);
                    total.setText("Total");
                    result.setText("Result");
                    userSelection = 2;
                }

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpuSelection = (int)(Math.random()*2)+1;
                if(cpuSelection == 1){
                    cpuimage.setImageResource(R.drawable.one);
                }
                else if(cpuSelection == 2){
                    cpuimage.setImageResource(R.drawable.two);
                }
                if(userSelection != 1 && userSelection != 2){
                  Toast.makeText(MainActivity.this, "This is an Error", Toast.LENGTH_SHORT).show();
                }
                sum = userSelection + cpuSelection;
                total.setText("Total is " + sum);
                if(sum%2==0){
                    result.setText("You Win");
                }
                else if(sum%2==1){
                    result.setText("CPU Wins");
                }
            }
        });
    }
}
