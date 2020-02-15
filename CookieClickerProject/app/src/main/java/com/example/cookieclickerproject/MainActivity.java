package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    static TextView btcView;

    public static AtomicInteger btc;

    CardView ramupgrade;
    int ramUpgradeCost = 50;
    static int ramMultiplier = 1;
    ObjectAnimator objectAnimator;

    ConstraintLayout constraintLayout;
    TextView textView;

    Button storeMenu;

    static int passiveIncomeTime;

    protected void onStart() {
        super.onStart();
        Log.d("tag","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag","Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag","Resume");
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bitcoin Clicker");

        passiveIncomeTime = 2000;

        storeMenu = findViewById(R.id.storeMenu);

        imageView = findViewById(R.id.basketball);

        btcView = findViewById(R.id.score);

        btc = new AtomicInteger();

        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.25f,1.0f,1.25f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(250);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                btc.addAndGet(ramMultiplier);
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

                float rand = (float)(Math.random()*.5)+.35f;
                constraintSet.setHorizontalBias(textView.getId(),rand);
                constraintSet.setVerticalBias(textView.getId(),0.3f);

                constraintSet.applyTo(constraintLayout);

                objectAnimator = ObjectAnimator.ofFloat(textView,"translationY",-600f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();

            }
        });

        storeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startStore = new Intent(MainActivity.this,StoreActivity.class);
                startStore.putExtra("ramUpgradeCost",ramUpgradeCost);
                startStore.putExtra("ramMultiplier",ramMultiplier);
                startStore.putExtra("passiveIncomeTime",passiveIncomeTime);
                startStore.putExtra("BTC",btc.intValue());
                startActivity(startStore);
            }
        });

        ramUpgradeCost = getIntent().getIntExtra("ramUpgradeCostAfterStore",50);
        ramMultiplier = getIntent().getIntExtra("ramMultiplierAfterStore",1);
        passiveIncomeTime = getIntent().getIntExtra("passiveIncomeTime",2000);
        btc.set(getIntent().getIntExtra("BTC",0));
        btcView.setText(btc.toString() + "Ƀ");

        if (savedInstanceState != null) {
            Log.d("BTCVALUE","TEST");
            Log.d("BTCVALUE",savedInstanceState.getInt("BTC")+"");
            btc.set(savedInstanceState.getInt("BTC"));
            btcView.setText(btc.toString() + "Ƀ");
            ramMultiplier = savedInstanceState.getInt("ramMultiplier");
            ramUpgradeCost = savedInstanceState.getInt("ramUpgradeCost");
            passiveIncomeTime = savedInstanceState.getInt("passiveIncomeTime");
        }


        btcView.setText(btc.toString() + "Ƀ");

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        Log.d("BTCVALUE",btc.intValue()+"");
        outState.putInt("BTC",btc.intValue());
        outState.putInt("ramUpgradeCost",ramUpgradeCost);
        outState.putInt("ramMultiplier",ramMultiplier);
        outState.putInt("passiveIncomeTime",passiveIncomeTime);
    }

    public static class MyThread extends Thread{
        Handler handler = new Handler();

        public void run(){
            try{
                Thread.sleep(2);
            }catch(Exception e){ }
            while(true) {
                btc.addAndGet(ramMultiplier);
                try {
                    Thread.sleep(passiveIncomeTime);
                    Log.d("passiveIncomeTime",passiveIncomeTime+"");
                } catch (Exception e) {

                }

                handler.post(new Runnable() {
                    public void run() {
                        btcView.setText(btc.toString() + "Ƀ");
                    }
                });

            }

        }
    }

}
