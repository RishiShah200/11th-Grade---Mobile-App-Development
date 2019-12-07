package com.example.listviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class FullPictureActivity extends AppCompatActivity {

    ImageView imageView;

    protected void onStart() {
        super.onStart();
        Log.d("tag", "Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag", "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag", "Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag", "Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "Resume");
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_picture);

        imageView = findViewById(R.id.id_new_imageview);
        imageView.setImageResource(MainActivity.image);

    }
}
