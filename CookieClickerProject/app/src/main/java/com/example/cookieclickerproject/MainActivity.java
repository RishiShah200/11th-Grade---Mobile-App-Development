package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    static ImageView computer;
    static TextView btcView;

    public static AtomicInteger btc;

    static CardView ramupgrade;
    static AtomicInteger ramUpgradeCost;
    static AtomicInteger ramMultiplier;
    ObjectAnimator objectAnimator;

    ConstraintLayout constraintLayout;
    TextView textView;

    static TextView ramcnt;
    static AtomicInteger numOfRamUpgradesBought;

    static AtomicInteger passiveIncome;
    static TextView costoframupgrade;

    static Handler handler = new Handler();

    ImageView imageView;

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bitcoin Clicker");
        costoframupgrade = findViewById(R.id.costoframupgrade);
        ramcnt = findViewById(R.id.ramcnt);
        ramupgrade = findViewById(R.id.ramupgrade);
        computer = findViewById(R.id.basketball);
        btcView = findViewById(R.id.score);

        ramUpgradeCost = new AtomicInteger();
        passiveIncome = new AtomicInteger();
        ramMultiplier = new AtomicInteger();
        numOfRamUpgradesBought = new AtomicInteger();
        btc = new AtomicInteger();

        ramUpgradeCost.set(50);
        passiveIncome.set(0);
        ramMultiplier.set(1);

        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.25f, 1.0f, 1.25f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(250);

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTransactionValidity();
                v.startAnimation(scaleAnimation);
                btc.addAndGet(ramMultiplier.get());
                btcView.setText(btc.toString() + "Ƀ");

                constraintLayout = findViewById(R.id.id_layout);
                textView = new TextView(MainActivity.this);
                textView.setId(View.generateViewId());

                textView.setText(ramMultiplier + "");
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT
                        , ConstraintLayout.LayoutParams.WRAP_CONTENT);

                textView.setLayoutParams(params);
                constraintLayout.addView(textView);

                ConstraintSet constraintSet = new ConstraintSet();  //whatever constraints were already present will be cloned.
                constraintSet.clone(constraintLayout);

                constraintSet.connect(textView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(textView.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(textView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

                float rand = (float) (Math.random() * .7) + .15f;
                constraintSet.setHorizontalBias(textView.getId(), rand);
                constraintSet.setVerticalBias(textView.getId(), 0.3f);

                constraintSet.applyTo(constraintLayout);

                objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", -650f);
                objectAnimator.setDuration(2000);

                objectAnimator.start();

            }
        });

        ramupgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfRamUpgradesBought.addAndGet(1);
                passiveIncome.addAndGet(1);
                ramcnt.setText(numOfRamUpgradesBought + "");
                ramMultiplier.addAndGet(1);
                btc.addAndGet(-1 * ramUpgradeCost.get());
                ramUpgradeCost.set((ramUpgradeCost.get() * 2));
                costoframupgrade.setText(ramUpgradeCost.get() + "");


                imageView = new ImageView(MainActivity.this);
                imageView.setId(View.generateViewId());

                imageView.setImageResource(R.drawable.ramimage);
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT
                        , ConstraintLayout.LayoutParams.WRAP_CONTENT);

                imageView.setLayoutParams(params);
                constraintLayout.addView(imageView);

                ConstraintSet constraintSet = new ConstraintSet();  //whatever constraints were already present will be cloned.
                constraintSet.clone(constraintLayout);

                constraintSet.connect(imageView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(imageView.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(imageView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(imageView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

                float rand = (float) (Math.random() * .7) + .05f;
                constraintSet.setHorizontalBias(imageView.getId(), rand);
                constraintSet.setVerticalBias(imageView.getId(), 0.15f);

                constraintSet.applyTo(constraintLayout);


            }
        });

        if (savedInstanceState != null) {
            btc.set(savedInstanceState.getInt("BTC"));
            btcView.setText(btc.toString() + "Ƀ");
            ramMultiplier.set(savedInstanceState.getInt("ramMultiplier"));
            ramUpgradeCost.set(savedInstanceState.getInt("ramUpgradeCost"));
            passiveIncome.set(savedInstanceState.getInt("passiveIncome"));
        }

        btcView.setText(btc.toString() + "Ƀ");
        handler.postDelayed(new MyThread(), 1000);

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("BTC", btc.get());
        outState.putInt("ramUpgradeCost", ramUpgradeCost.get());
        outState.putInt("ramMultiplier", ramMultiplier.get());
        outState.putInt("passiveIncome", passiveIncome.get());
    }

    public static class MyThread extends Thread {


        public void run() {
            try {
                checkTransactionValidity();
                btc.addAndGet(passiveIncome.get());
                btcView.setText(btc.get() + "Ƀ");
                if(numOfRamUpgradesBought.get() >= 3){
                    computer.setImageResource(R.drawable.upgradedcomputer);
                }
            } catch (Exception e) {

            }
            Log.d("BTC",(btc.get() >= ramUpgradeCost.get())+"");
            handler.postDelayed(this, 1000);
        }
    }

    public static void checkTransactionValidity() {
        if (btc.get() >= ramUpgradeCost.get()) {
            ramupgrade.setClickable(true);
            ramupgrade.setCardBackgroundColor(Color.RED);
        }
        else if (btc.get() <= ramUpgradeCost.get()) {
            ramupgrade.setClickable(false);
            ramupgrade.setCardBackgroundColor(Color.GRAY);
        }
    }

}
