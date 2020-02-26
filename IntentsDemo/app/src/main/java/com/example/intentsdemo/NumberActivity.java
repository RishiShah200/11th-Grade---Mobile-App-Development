package com.example.intentsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberActivity extends AppCompatActivity {

    EditText enterNumber;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        Toast.makeText(this,getIntent().getStringExtra("TEST"),Toast.LENGTH_SHORT).show();

        enterNumber = findViewById(R.id.id_enterNumber);
        close = findViewById(R.id.id_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendInfoBack = new Intent();
                sendInfoBack.putExtra(MainActivity.INTENT_CODE,enterNumber.getText().toString());
                setResult(RESULT_OK,sendInfoBack);
                finish();
            }
        });

    }
}
