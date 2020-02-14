package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicInteger;

public class StoreActivity extends AppCompatActivity {

    static AtomicInteger btc;
    int ramUpgradeCost;
    int ramMultiplier;
    CardView ramupgrade;

    Button returnToGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        btc = new AtomicInteger();

       btc.set(getIntent().getIntExtra("BTC",0));
       Log.d("BTCACTUALVALUE",btc.intValue()+"");
        ramUpgradeCost = getIntent().getIntExtra("ramUpgradeCost",50);
        ramMultiplier = getIntent().getIntExtra("ramMultiplier",1);

        ramupgrade = findViewById(R.id.ramupgrade);
        returnToGame = findViewById(R.id.returnToGame);

        Log.d("ramUpgradeCost",ramUpgradeCost+"");

        if(ramupgrade != null) {
            if (btc.intValue() > ramUpgradeCost) {
                ramupgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ramMultiplier+=1;
                        btc.addAndGet(-ramUpgradeCost);
                        ramUpgradeCost = (int)(ramUpgradeCost + ramUpgradeCost*.5);
                        new MainActivity.MyThread().start();
                    }
                });
            } else {
                ramupgrade.setClickable(false);
                ramupgrade.setCardBackgroundColor(Color.GRAY);
            }
        }

        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGame = new Intent(StoreActivity.this,MainActivity.class);
                backToGame.putExtra("ramUpgradeCostAfterStore",ramUpgradeCost);
                backToGame.putExtra("ramMultiplierAfterStore",ramMultiplier);
                backToGame.putExtra("BTC",btc.intValue());
                startActivity(backToGame);
            }
        });


    }


}
