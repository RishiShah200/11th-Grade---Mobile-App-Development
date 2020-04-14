package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    TextView textView;

    String receivedMessage = "";
    String receivedNumber = "";

    SmsManager smsManager = SmsManager.getDefault();
    Handler handler = new Handler();

    ArrayList<String> stateNames = new ArrayList<>();

    int stateIn, stateOut = 1;
    boolean doneWithLastStage;

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateNames.add("Greeting State");
        stateNames.add("Reason for Message State");
        stateNames.add("Informative State Part 1");
        stateNames.add("Tell about Raise State");
        stateNames.add("Great News");
        stateNames.add("Another STate");

        textView = findViewById(R.id.message);

        stateIn = 0;
        stateOut = 1;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 0);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);
        }

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Object[] pdus = (Object[]) extras.get("pdus");
                    SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                    for (int x = 0; x < smsMessages.length; x++) {
                        smsMessages[x] = SmsMessage.createFromPdu((byte[]) pdus[x], (String) extras.get("format"));
                        receivedMessage = smsMessages[x].getMessageBody();
                        receivedNumber = smsMessages[x].getOriginatingAddress();
                    }
                    stateIn = detectState();
                    Log.d("STATES", "State Out" + stateOut + "\t State In" + stateIn);
                    handler.postDelayed(sendTextMessage(generateMesssage()), 4000);
                    if (stateIn > 0 && stateIn == stateOut)
                        stateOut++;
                    if (stateIn > 0)
                        textView.setText("State:" + stateNames.get(stateIn - 1));     //change this. "stateOut-1"
                }
            }
        };
    }

    public Runnable sendTextMessage(final String message) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                smsManager.sendTextMessage(receivedNumber, null, message, null, null);
            }
        };
        return r;
    }

    public String generateMesssage() {

        ArrayList<String> list = new ArrayList<>();

        if (stateIn == stateOut) {

            if (stateOut == 1) {      //Greeting State
                list.add("Hello. How are you doing?");
                list.add("Hey. How are you doing?");
                list.add("Greeting, how are you at this fine moment?");
            }
            if (stateOut == 2) {    //
                list.add("Yes. I wanted to tell you about something concerning your job.");//I am doing fine thanks for asking. Is there any particular reason for this important message that you texted me about?
                list.add("It is indeed very urgent. It pertains to your job and your position at the company.");
                list.add("It is very important and necessary for me to tell you this information. It involves your involvement in the company.");
            }
            if (stateOut == 3) {
                list.add("No, nothing like that at all. In fact, it is very good information.");       //Is it bad? Did I do something wrong?
                list.add("No no, it is something to be very excited about actually.");
                list.add("Nope, no reason to be worried. It is good news.");
            }
            if (stateOut == 4) {
                list.add("Based on your recent performance in the company, I have decided to give you a raise.");   //Oh thank god. You had me worried for a second.
                list.add("Due to your exceptional performance in the company, I have personally decided to reward you with a hefty raise");
                list.add("I have decided to give you a raise based on your recent success in the company");
            }
            if (stateOut == 5) {
                list.add("It is, I decided to give you a raise of 10% of your salary. Come to my office tomorrow and we will discuss more details.");   //Oh really, thats amazing news.
                list.add("You definitely deserved the raise. I am going to reward you for your performance with a raise. Come into my office tomorrow and I will tell you about the amount");
                list.add("You did an amazing job this past year. Come into my office tomorrow and I wil give you more details.");
                doneWithLastStage = true;
            }
        } else if (doneWithLastStage) {
            list.add("");
        } else {
            list.add("Sorry, can you please rephrase your message. ");
        }

        int rand = (int) (Math.random() * list.size() - 1);
        return list.get(rand);
    }

    public int detectState() {

        if (stateOut == 1 && (receivedMessage.toLowerCase().contains("hi") || receivedMessage.toLowerCase().contains("hello") || receivedMessage.toLowerCase().contains("greetings"))) {
            return 1;
        } else if (receivedMessage.contains("why") || receivedMessage.contains("wondering") || receivedMessage.contains("unsure") || receivedMessage.contains("important") && receivedMessage.indexOf("important") < receivedMessage.indexOf("message")) {
            return 2;
        } else if (receivedMessage.contains("bad") || receivedMessage.contains("wrong") && receivedMessage.indexOf("wrong") > receivedMessage.toLowerCase().indexOf("I")) {
            return 3;
        } else if (receivedMessage.contains("god") && receivedMessage.indexOf("god") > receivedMessage.indexOf("thank") || receivedMessage.contains("worried")) {
            return 4;
        } else if (receivedMessage.contains("amazing") || receivedMessage.contains("really") && receivedMessage.indexOf("really") < receivedMessage.indexOf("amazing")) {
            return 5;
        }
        return 0;
    }

}
