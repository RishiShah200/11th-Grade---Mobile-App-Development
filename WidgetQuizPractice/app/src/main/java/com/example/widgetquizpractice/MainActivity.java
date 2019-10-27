package com.example.widgetquizpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar bar;
    EditText text;
    Switch aSwitch;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.id_seekBar1);
        text = findViewById(R.id.id_editText1);
        aSwitch = findViewById(R.id.id_switch1);
        button = findViewById(R.id.id_button1);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button.setWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    button.setTextColor(Color.YELLOW);
                }
                else{
                    button.setTextColor(Color.GREEN);
                }
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("RishiShah")){
                    aSwitch.setEnabled(false);
                }
                else{
                    aSwitch.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
