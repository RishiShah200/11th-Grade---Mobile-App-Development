package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
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

    static int ramMultiplier = 1;

    static boolean RAMbought = false;

    ObjectAnimator objectAnimator;

    ConstraintLayout constraintLayout;
    TextView textView;

    Button storeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeMenu = findViewById(R.id.storeMenu);

        imageView = findViewById(R.id.basketball);

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

                constraintLayout = findViewById(R.id.id_layout);
                textView = new TextView(MainActivity.this);
                textView.setId(View.generateViewId());

                textView.setText(ramMultiplier+"");
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT
                        ,ConstraintLayout.LayoutParams.WRAP_CONTENT);

                textView.setLayoutParams(params);
                constraintLayout.addView(textView);

                ConstraintSet constraintSet = new ConstraintSet();  //whatever constraints were already present will be cloned.
                constraintSet.clone(constraintLayout);

                constraintSet.connect(textView.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
                constraintSet.connect(textView.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
                constraintSet.connect(textView.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
                constraintSet.connect(textView.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);

                float rand = (float)(Math.random()*.5)+.25f;
                constraintSet.setHorizontalBias(textView.getId(),rand);
                constraintSet.setVerticalBias(textView.getId(),0.25f);

                constraintSet.applyTo(constraintLayout);

                objectAnimator = ObjectAnimator.ofFloat(textView,"translationY",-500f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();

            }
        });

        storeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startStore = new Intent(MainActivity.this,StoreActivity.class);
                startActivity(startStore);
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
