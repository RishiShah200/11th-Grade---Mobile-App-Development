package com.example.widgetpracticeday2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch1;
    Switch aSwitch2;
    Switch aSwitch3;
    TextView color;
    EditText inputverify;
    Button verify;
    TextView verifytext;
    EditText inputdatabase;
    Button check;
    TextView databasetext;
    TextView size;
    SeekBar bar;
    boolean switch1 = false;
    boolean switch2 = false;
    boolean switch3 = false;
    boolean isVerified;
    boolean inDatabase;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch1 = findViewById(R.id.id_switch1);
        aSwitch2 = findViewById(R.id.id_switch2);
        aSwitch3 = findViewById(R.id.id_switch3);
        color = findViewById(R.id.id_textcolor);
        inputverify = findViewById(R.id.id_inputverify);
        verify = findViewById(R.id.id_verifybutton);
        verifytext = findViewById(R.id.id_verifytext);
        inputdatabase = findViewById(R.id.id_inputdatabase);
        check = findViewById(R.id.id_buttoncheck);
        databasetext = findViewById(R.id.id_databasetext);
        size = findViewById(R.id.id_textsize);
        bar = findViewById(R.id.id_seekbar);

        list.add("myname@gmail.com");
        list.add("myname2@gmail.com");
        list.add("myname3@gmail.com");


        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch1 = isChecked;
                if (switch1 && switch2 && switch3) {
                    color.setTextColor(Color.BLUE);
                } else if (switch1 && switch3 && switch2 == false) {
                    color.setTextColor(Color.RED);
                } else if (!switch1 && !switch2 && switch3) {
                    color.setTextColor(Color.GREEN);
                } else {
                    color.setTextColor(Color.BLACK);
                }
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch2 = isChecked;
                if (switch1 && switch2 && switch3) {
                    color.setTextColor(Color.BLUE);
                } else if (switch1 && switch3 && switch2 == false) {
                    color.setTextColor(Color.RED);
                } else if (!switch1 && !switch2 && switch3) {
                    color.setTextColor(Color.GREEN);
                } else {
                    color.setTextColor(Color.BLACK);
                }
            }
        });

        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch3 = isChecked;
                if (switch1 && switch2 && switch3) {
                    color.setTextColor(Color.BLUE);
                } else if (switch1 && switch3 && switch2 == false) {
                    color.setTextColor(Color.RED);
                } else if (!switch1 && !switch2 && switch3) {
                    color.setTextColor(Color.GREEN);
                } else {
                    color.setTextColor(Color.BLACK);
                }
            }
        });

        inputverify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains("@") && s.toString().contains(".com") && (s.toString().indexOf("@") < s.toString().indexOf(".com"))) {
                    isVerified = true;
                } else {
                    isVerified = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVerified) {
                    verifytext.setText("Verified");
                } else {
                    verifytext.setText("Not Verified");
                }
            }
        });

        inputdatabase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //       Log.d("rishi_error",s.toString() + "list stuff " + list.get(x));
                if (list.contains(s.toString())) {
                    inDatabase = true;
                } else {
                    inDatabase = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inDatabase) {
                    databasetext.setText("In Database");
                } else {
                    databasetext.setText("Not in Database");
                }
            }
        });

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                size.setTextSize(progress);
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
