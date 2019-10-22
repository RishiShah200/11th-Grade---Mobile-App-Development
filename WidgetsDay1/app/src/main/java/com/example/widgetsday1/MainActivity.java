package com.example.widgetsday1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Switch aSwitch;
    EditText editText;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        aSwitch = findViewById(R.id.switch1);       //compounds button
        editText = findViewById(R.id.editText);
        textView2 = findViewById(R.id.textView2);

        editText.addTextChangedListener(new TextWatcher() {     //remember this bruh
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView2.setText(s);   //string implements charsequence
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {   //switch
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {    //is button checked or not
                if(isChecked){
                    aSwitch.setText("On");
                }
                else{
                    aSwitch.setText("Off");
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {      //seekbar

            int currentProgress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {        //when dragging
                currentProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {     //first click

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {      //when released
                textView.setText("Progress " + currentProgress+"%");
            }
        });
    }
}
