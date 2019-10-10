package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button a1;
    Button a2;
    Button a3;
    Button a4;
    Button a5;
    Button a6;
    Button a7;
    Button a8;
    Button a9;
    Button a0;
    Button clear;
    Button equals;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    TextView output;
    String temp = "";
    String delim = "+-*/";
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = findViewById(R.id.id_button1);
        a1.setOnClickListener(this);
        a2 = findViewById(R.id.id_button2);
        a2.setOnClickListener(this);
        a3 = findViewById(R.id.id_button3);
        a3.setOnClickListener(this);
        a4 = findViewById(R.id.id_button4);
        a4.setOnClickListener(this);
        a5 = findViewById(R.id.id_button5);
        a5.setOnClickListener(this);
        a6 = findViewById(R.id.id_button6);
        a6.setOnClickListener(this);
        a7 = findViewById(R.id.id_button7);
        a7.setOnClickListener(this);
        a8 = findViewById(R.id.id_button8);
        a8.setOnClickListener(this);
        a9 = findViewById(R.id.id_button9);
        a9.setOnClickListener(this);
        a0 = findViewById(R.id.id_button0);
        a0.setOnClickListener(this);
        clear = findViewById(R.id.id_buttonclear);
        clear.setOnClickListener(this);
        equals = findViewById(R.id.id_buttonequals);
        equals.setOnClickListener(this);
        plus = findViewById(R.id.id_buttonplus);
        plus.setOnClickListener(this);
        minus = findViewById(R.id.id_buttonminus);
        minus.setOnClickListener(this);
        multiply = findViewById(R.id.id_buttonmultiply);
        multiply.setOnClickListener(this);
        divide = findViewById(R.id.id_buttondivide);
        divide.setOnClickListener(this);
        output = findViewById(R.id.id_output);
        output.setOnClickListener(this);
    }

    @Override
        public void onClick(View v){
            String a = (String)((Button)v).getText();
            temp+=a;
            output.setText(temp);
            if(((Button)v).getText().equals("=")){
                StringTokenizer tokenizer = new StringTokenizer(temp,delim,true);
                while(tokenizer.hasMoreTokens()){		//return boolean
                    String currentWord = tokenizer.nextToken();
                    list.add(currentWord);
                }

            }



            if(((Button)v).getText().equals("C")){
                output.setText("");
                temp = "";
            }



        }
}
