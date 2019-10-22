package com.example.widgetday1practice;

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

    Switch aSwitch;
    TextView textColor;
    EditText editText;
    SeekBar seekBar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.id_switchcontrol);
        textColor = findViewById(R.id.id_colortext);
        editText = findViewById(R.id.id_edittext);
        seekBar = findViewById(R.id.id_seekbar);
        btn = findViewById(R.id.id_button);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    seekBar.setEnabled(false);
                }
                else{
                    seekBar.setEnabled(true);
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.equals("red")){
                    textColor.setTextColor(Color.parseColor((String)s));
                }
                else if(s.equals("blue")){
                    textColor.setTextColor(Color.parseColor((String)s));
                }
                else if(s.equals("green")){
                    textColor.setTextColor(Color.parseColor((String)s));
                }
                else{
                    textColor.setTextColor(Color.BLACK);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btn.setWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

}
