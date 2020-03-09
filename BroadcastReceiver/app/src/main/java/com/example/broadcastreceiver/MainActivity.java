package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.batteryinfo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        broadcastReceiver = new BatteryMonitor();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }

    public class BatteryMonitor extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {//code that is run when we get a broadcast
            Toast.makeText(context, "Battery Changed", Toast.LENGTH_SHORT).show();

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-99); //default value -99 which is not an extra from anything
            if(status == -1){
                textView.setText("Error!");
            }
            if(status == 5){
                textView.setText("Full Charge!");
            }
            if(status == 2){
                textView.setText("Charging");
            }
        }
    }
}
