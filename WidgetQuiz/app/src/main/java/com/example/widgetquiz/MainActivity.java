package com.example.widgetquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText enterPassword;
    EditText reenterPassword;
    Button ok;
    Switch aSwitch;
    TextView checkPassword;

    String temp;
    String temp2;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterPassword = findViewById(R.id.id_enterpassword);
        reenterPassword = findViewById(R.id.id_reenterpassword);
        ok = findViewById(R.id.id_button1);
        aSwitch = findViewById(R.id.id_switch1);
        checkPassword = findViewById(R.id.id_checkpassword);

        list.add("test");
        list.add("123");
        list.add("password");
        list.add("abc");

        enterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp  = s.toString();
                if(list.contains(s.toString())){
                    checkPassword.setText("Password Already Used");
                }
                else{
                    checkPassword.setText("Password Not Used Previously");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reenterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        aSwitch.setEnabled(false);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && (checkPassword.getText().equals("Password Not Used Previously"))){
                    buttonView.setText("Match");
                }
                else{
                    buttonView.setText("Not Match");
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp.equals(temp2) && (checkPassword.getText().equals("Password Not Used Previously"))){
                    aSwitch.setChecked(true);
                }
                else{
                    aSwitch.setChecked(false);
                }
            }
        });
    }
}
