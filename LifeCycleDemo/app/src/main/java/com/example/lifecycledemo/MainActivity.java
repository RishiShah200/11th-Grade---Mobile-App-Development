package com.example.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    int counter = 0;
    public static final String KEY = "abc123";

    @Override
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
        Log.d("tag","onCreate");

        button       = findViewById(R.id.id_button);
        textView     = findViewById(R.id.id_text);

        if(savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY);
            textView.setText("Count: "+counter);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textView.setText("Count: " + counter);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY,counter);
    }
}
