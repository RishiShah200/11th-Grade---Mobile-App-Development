package com.example.intentpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button launch;
    TextView userName;
    TextView userGPA;
    EditText inputName;

    static final int NUMBER_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launch = findViewById(R.id.launch);
        userName = findViewById(R.id.userName);
        userGPA = findViewById(R.id.userGPA);
        inputName = findViewById(R.id.inputName);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inputData = new Intent(MainActivity.this,DataActivity.class);
                inputData.putExtra("NAME",userName.getText());
                startActivityForResult(inputData,NUMBER_CODE);
            }
        });

        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userName.setText(s.toString());
            }
        });
    }
}
