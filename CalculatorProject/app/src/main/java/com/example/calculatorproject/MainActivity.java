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
    int total;
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

        try {
            if (!(((Button) v).getText().equals("="))&&!(((Button) v).getText().equals("C"))) {
                String a = (String) ((Button) v).getText();
                temp += a;
                output.setText(temp);
            }

            if (((Button) v).getText().equals("=")) {
                StringTokenizer tokenizer = new StringTokenizer(temp, delim, true);
                while (tokenizer.hasMoreTokens()) {        //return boolean
                    String currentWord = tokenizer.nextToken();
                    list.add(currentWord);
                }
                for (int x = 0; x < list.size(); x++) {
                   /* if(list.get(x).equals("*")){

                    }*/
                  /*  if(list.get(x).equals("/")){

                    }*/
                    if (list.get(x).equals("+")) {
                        String before = list.get(x - 1);
                        String after = list.get(x + 1);
                        int temp = Integer.parseInt(before);
                        int temp2 = Integer.parseInt(after);
                        total = temp + temp2;

                    }
                  /*  if(list.get(x).equals("-")){

                    }*/
                    /*list.remove(x);
                    list.remove(x - 1);
                    list.remove(x);*/
                }
                output.setText("" + total);

            }


            if (((Button) v).getText().equals("C")) {
                output.setText("");
                    while (list.size() > 0) {
                        for (int x = 0; x < list.size(); x++) {
                            list.remove(x);
                        }
                        temp = "";
                        total = 0;
                    }
            }

        }catch(Exception e){
            output.setText(e+"Something has gone terribly wrong");
        }

        }
}
