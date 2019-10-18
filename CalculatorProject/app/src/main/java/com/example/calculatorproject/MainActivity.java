package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    Button exponent;
    Button sin;     //only if time
    Button cos;
    Button tan;
    TextView output;
    String delim = "^+–*/";
    String temp;
    int multpos;
    int divpos;
    int addpos;     //int values used to hold index values of delimeters in loop
    int subpos;
    int expos;
    double total;
    Vibrator vibrator;      //buttons vibrate when clicked
    HorizontalScrollView scrollview;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = "";
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
        output.setClickable(false);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        scrollview = findViewById(R.id.id_horizontalscrollview);
        scrollview.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        exponent = findViewById(R.id.id_exponent);
        output.setTextColor(Color.WHITE);
        layout = findViewById(R.id.id_mainlayout);
        sin = findViewById(R.id.id_sin);        //add if time
        cos = findViewById(R.id.id_cos);
        tan = findViewById(R.id.id_tan);
    }

    @Override
    public void onClick(View v) {

        ArrayList<String> list = new ArrayList<String>();
        vibrator.vibrate(80);
        try {
            if (!(((Button) v).getText().equals("=")) && !(((Button) v).getText().equals("C"))) {
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

                multpos = list.indexOf("*");
                divpos = list.indexOf("/");
                addpos = list.indexOf("+");
                subpos = list.indexOf("–");
                expos = list.indexOf("^");

                if (multpos < 0 && divpos < 0 && addpos < 0 && subpos < 0 && expos < 0) {       //if just a number, print that number to screen
                    output.setText("" + list.get(0));
                }
                if ((list.get(divpos + 1).equals("0")) && divpos > 0) {
                    throw new Exception("Error");
                }
                if (multpos == 0) {
                    throw new Exception("Error");
                }
                if (addpos == 0) {
                    throw new Exception("Error");
                }
                if (subpos == 0) {
                    throw new Exception("Error");
                }

                while (list.size() > 1) {

                    multpos = list.indexOf("*");
                    divpos = list.indexOf("/");
                    addpos = list.indexOf("+");
                    subpos = list.indexOf("–");
                    expos = list.indexOf("^");

                    if (expos >= 0) {      //checks if there is exponent and calculates if there is
                        double temp = Double.parseDouble(list.get(expos - 1));
                        double temp2 = Double.parseDouble(list.get(expos + 1));
                        total = Math.pow(temp, temp2);
                        this.temp = "" + total; //delete this line after
                        list.set(expos - 1, "" + total);
                        list.subList(expos, expos + 2).clear();
                    }
                    if (divpos >= 0) {      ///checks if there is divison and calculates if there is
                        double temp = Double.parseDouble(list.get(divpos - 1));
                        double temp2 = Double.parseDouble(list.get(divpos + 1));
                        total = temp / temp2;
                        this.temp = "" + total; //delete this line after
                        list.set(divpos - 1, "" + total);
                        list.subList(divpos, divpos + 2).clear();
                    } else if (multpos >= 0) {   //checks if there is multiplication and calculates if there is
                        double temp = Double.parseDouble(list.get(multpos - 1));
                        double temp2 = Double.parseDouble(list.get(multpos + 1));
                        total = temp * temp2;
                        this.temp = "" + total; //delete this line after
                        list.set(multpos - 1, "" + total);
                        list.subList(multpos, multpos + 2).clear();
                    } else if (subpos >= 0) {       //checks if there is subtraction and calculates if there is
                        double temp = Double.parseDouble(list.get(subpos - 1));
                        double temp2 = Double.parseDouble(list.get(subpos + 1));
                        total = temp - temp2;
                        this.temp = "" + total; //delete this line after
                        list.set(subpos - 1, "" + total);
                        list.subList(subpos, subpos + 2).clear();
                    } else if (addpos >= 0) {    //checks if there is addition and calculates if there is
                        double temp = Double.parseDouble(list.get(addpos - 1));
                        double temp2 = Double.parseDouble(list.get(addpos + 1));
                        total = temp + temp2;
                        this.temp = "" + total; //delete this line after
                        list.set(addpos - 1, "" + total);
                        list.subList(addpos, addpos + 2).clear();
                    }
                    output.setText("" + total);       //set text of output to the answer
                }

            }

            if (((Button) v).getText().equals("C")) {       //clears everything if clicked
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
            }
        } catch (Exception e) {        //check for all da errors
            output.setText("Error");    //:( not good
        }
    }

}
