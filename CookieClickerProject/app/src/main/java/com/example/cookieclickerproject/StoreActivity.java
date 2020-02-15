package com.example.cookieclickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class StoreActivity extends AppCompatActivity {

    static AtomicInteger btc;
    int ramUpgradeCost;
    int ramMultiplier;
    CardView ramupgrade;

    Button returnToGame;
    TextView costOfRamUpgrade;

    TextView ramcnt;
    int numOfRamUpgradesBought;

    int passiveIncomeTime;

    protected void onStart() {
        super.onStart();
        Log.d("tag2","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag2","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag2","Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag2","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag2","Resume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        btc = new AtomicInteger();

        costOfRamUpgrade = findViewById(R.id.costoframupgrade);
        ramcnt = findViewById(R.id.ramcnt);

       btc.set(getIntent().getIntExtra("BTC",0));       //maybe make this what is stored in the savedInstanceState
       Log.d("BTCACTUALVALUE",btc.intValue()+"");
        ramUpgradeCost = getIntent().getIntExtra("ramUpgradeCost",50);
        ramMultiplier = getIntent().getIntExtra("ramMultiplier",1);
        passiveIncomeTime = getIntent().getIntExtra("passiveIncomeTime",2000);

        ramupgrade = findViewById(R.id.ramupgrade);
        returnToGame = findViewById(R.id.returnToGame);

        Log.d("ramUpgradeCost",ramUpgradeCost+"");

        if(ramupgrade != null) {
            if (btc.intValue() > ramUpgradeCost) {
                ramupgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numOfRamUpgradesBought++;
                        passiveIncomeTime = (int)(passiveIncomeTime - passiveIncomeTime*0.2);
                        ramcnt.setText(numOfRamUpgradesBought+"");
                        ramMultiplier+=1;
                        btc.addAndGet(-ramUpgradeCost);
                        Log.d("AMOUNT",btc.intValue()+"");
                        ramUpgradeCost = (int)(ramUpgradeCost + ramUpgradeCost*.5);
                        new MainActivity.MyThread().start();
                    }
                });
            }
            else {
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
                backToGame.putExtra("passiveIncomeTime",passiveIncomeTime);
                Log.d("passiveIncomeTime",passiveIncomeTime+"");
                backToGame.putExtra("BTC",btc.intValue());
                startActivity(backToGame);
            }
        });

        costOfRamUpgrade.setText(ramUpgradeCost+"");
        ramcnt.setText(numOfRamUpgradesBought+"");
        if (savedInstanceState != null) {
            Log.d("REACHED","REACHED");
            //numOfRamUpgradesBought = savedInstanceState.getInt("ramcnt");
          //  ramcnt.setText(numOfRamUpgradesBought+"");
            numOfRamUpgradesBought = Integer.parseInt(savedInstanceState.getString("ramcnt"));
            ramcnt.setText(numOfRamUpgradesBought+"");

        }

    }


    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("REACHED",(String)ramcnt.getText());
      //  outState.putInt("ramcnt",numOfRamUpgradesBought);
        outState.putString("ramcnt",(String)ramcnt.getText());
    }
}
