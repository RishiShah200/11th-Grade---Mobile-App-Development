package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

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
    TextView test;
    String temp = "";
    String delim = "+-*/";
    int multpos;
    int divpos;
    int addpos;
    int subpos;

    double total;
    ArrayList<String> list = new ArrayList<String>();

    Vibrator vibrator;

    HorizontalScrollView scrollview;
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
        output.setMovementMethod(new ScrollingMovementMethod());
        output.setClickable(false);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        test = findViewById(R.id.id_test);

        scrollview = findViewById(R.id.id_horizontalscrollview);
        scrollview.arrowScroll(1);

    }

    @Override
        public void onClick(View v){
            vibrator.vibrate(100);
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

                test.setText(""+list);


                while(list.size()>1) {
                    multpos = list.indexOf("*");
                    divpos = list.indexOf("/");
                    addpos = list.indexOf("+");
                    subpos = list.indexOf("-");



                    if(divpos >= 0){      //something here is not right // work in it later
                        double temp = Double.parseDouble(list.get(divpos-1));
                        double temp2 = Double.parseDouble(list.get(divpos+1));
                        total = temp / temp2;
                        this.temp = ""+total; //delete this line after
                        list.set(divpos-1,""+total);
                        list.subList(divpos,divpos+2).clear();
                    }

                    else if(multpos >= 0){        //something here is not right // work in it later
                        double temp = Double.parseDouble(list.get(multpos-1));
                        double temp2 = Double.parseDouble(list.get(multpos+1));
                        total = temp * temp2;
                        this.temp = ""+total; //delete this line after
                        list.set(multpos-1,""+total);
                        list.subList(multpos,multpos+2).clear();

                    }

                    else if(addpos>=0){        //something here is not right // work in it later
                        double temp = Double.parseDouble(list.get(addpos-1));
                        double temp2 = Double.parseDouble(list.get(addpos+1));
                        total = temp + temp2;
                        this.temp = ""+total; //delete this line after
                        list.set(addpos-1,""+total);
                        list.subList(addpos,addpos+2).clear();
                    }

                    else if(subpos>=0){        //something here is not right // work in it later
                        double temp = Double.parseDouble(list.get(subpos-1));
                        double temp2 = Double.parseDouble(list.get(subpos+1));
                        total = temp - temp2;
                        this.temp = ""+total; //delete this line after
                        list.set(subpos-1,""+total);
                        list.subList(subpos,subpos+2).clear();
                    }

                }

                output.setText(""+total);

            }

            if (((Button) v).getText().equals("C")) {
                output.setText("");
                temp = "";
                total = 0;
                    while (list.size() > 0) {
                        for (int x = 0; x < list.size(); x++) {
                            list.remove(x);
                        }
                        temp = "";
                        total = 0;
                    }
                for (int x = 0; x < list.size(); x++) {
                    list.remove(x);
                }
                test.setText(""+list);
            }

        }catch(Exception e){
            output.setText(e+"Something has gone terribly wrong");
        }

        }
}
