package com.example.radiogroupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup volume;
    RadioGroup rps;
    ImageView img;
    Button play;
    TextView score;
    String choice = "";
    String cpuchoice = "";
    int myScore;
    int cpucnt;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volume = findViewById(R.id.id_volumecontrol);
        rps = findViewById(R.id.id_rockpaperscissor);
        img = findViewById(R.id.id_imageview);
        play = findViewById(R.id.id_button1);
        score = findViewById(R.id.id_score);

        list.add("Rock");
        list.add("Paper");
        list.add("Scissors");

        volume.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.id_100){
                    Toast myToast = Toast.makeText(MainActivity.this,"High Volume can damage your hearing",Toast.LENGTH_SHORT);
                    myToast.show();
                }

            }
        });

        rps.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.id_rock){
                    choice = "Rock";
                }
                if(checkedId == R.id.id_paper){
                    choice = "Paper";
                }
                if(checkedId == R.id.id_scissors){
                    choice = "Scissors";
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = (int)(Math.random()*3);
                cpuchoice = list.get(rand);
                if(choice == "Rock" && cpuchoice == "Paper"){
                    score.setText("CPU Wins! \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.paper);
                    cpucnt++;
                }
                else if(choice == "Rock" && cpuchoice == "Scissors"){
                    score.setText("You Win! \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.scissor);
                    myScore++;
                }
                else if(choice == "Rock" && cpuchoice == "Rock"){
                    score.setText("You Tied \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.rock);
                }
                else if(choice == "Paper" && cpuchoice == "Paper"){
                    score.setText("You Have Tied \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.paper);
                }
                else if(choice == "Paper" && cpuchoice == "Rock"){
                    score.setText("You Win! \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.rock);
                    myScore++;
                }
                else if(choice == "Paper" && cpuchoice == "Scissors"){
                    score.setText("CPU WINS! \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.scissor);
                    cpucnt++;
                }
                else if(choice == "Scissors" && cpuchoice == "Scissors"){
                    score.setText("You Have Tied! \n Player: "+ myScore + " " + "CPU: "+ cpucnt);
                    img.setImageResource(R.drawable.scissor);
                }
                else if(choice == "Scissors" && cpuchoice == "Paper"){
                    score.setText("You Win! \n Player: " + myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.paper);
                    myScore++;
                }
                else if(choice == "Scissors" && cpuchoice == "Rock"){
                    score.setText("CPU Wins! \n Player: "+ myScore + " " + "CPU: " + cpucnt);
                    img.setImageResource(R.drawable.rock);
                    cpucnt++;
                }
            }
        });
    }
}
