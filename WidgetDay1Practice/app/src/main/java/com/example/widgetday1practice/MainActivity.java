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
                if (isChecked) {
                    seekBar.setEnabled(false);
                } else {
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
                if (s.toString().toLowerCase().equals("red")) {
                    textColor.setTextColor(Color.RED);
                } else if (s.toString().toLowerCase().equals("green")) {
                    textColor.setTextColor(Color.BLUE);
                } else if (s.toString().toLowerCase().equals("blue")) {
                    textColor.setTextColor(Color.GREEN);
                } else {
                    textColor.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                btn.setWidth(progress * 15);
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
