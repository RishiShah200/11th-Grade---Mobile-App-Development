package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView btcView;

    static AtomicInteger btc;

    CardView ramupgrade;

    int ramUpgradeCost;

    static int ramMultiplier = 0;

    static boolean RAMbought = false;

    Button startMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.basketball);

        startMenu = findViewById(R.id.startMenu);

        startMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SideMenu.class);
                startActivity(intent);
            }
        });

        btcView = findViewById(R.id.score);

        btc = new AtomicInteger();

        ramupgrade = findViewById(R.id.ramupgrade);

        ramUpgradeCost = 50;

        if(btc.intValue() > ramUpgradeCost){
            ramupgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RAMbought = true;
                    ramMultiplier = 1;
                    btc.addAndGet(-ramUpgradeCost);
                    btcView.setText(btc.toString() + "Ƀ");
                }
            });
        }

        if(RAMbought){
            new MyThread().start();
        }

        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.25f,1.0f,1.25f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(250);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                btc.addAndGet(1);
                btcView.setText(btc.toString() + "Ƀ");
            }
        });
    }

    public static class MyThread extends Thread{
        public void run(){
            try{
                Thread.sleep(2);
            }catch(Exception e){ }
           btc.addAndGet(ramMultiplier);

        }
    }

}
