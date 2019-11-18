package com.example.spinnerday1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner prefix;
    EditText editText;
    Button add;
    TextView textView;
    Spinner names;
    ArrayList<String> list;
    String temp;
    String textviewtext = "";
    ArrayList<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefix = findViewById(R.id.id_prefix);
        editText = findViewById(R.id.id_edittext);
        add = findViewById(R.id.id_add);
        textView = findViewById(R.id.id_namesview);
        names = findViewById(R.id.id_names);

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list.add("mono");
        list.add("bi");
        list.add("tri");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
        prefix.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textviewtext = prefix.getSelectedItem().toString();
                textView.setText(textviewtext + " " +  temp);
                list2.add((String)textView.getText());
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,list2);
                names.setAdapter(adapter2);
            }
        });


    }
}
