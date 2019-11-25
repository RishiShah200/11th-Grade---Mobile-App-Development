package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner unitFrom;
    EditText enterNumber;
    Spinner unitTo;
    TextView result;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    String from;
    String to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitFrom = findViewById(R.id.id_unitfrom);
        enterNumber = findViewById(R.id.id_enternumber);
        unitTo = findViewById(R.id.id_unitto);
        result = findViewById(R.id.id_result);

        list = new ArrayList<>();

        list.add("Akash");
        list.add("Zareeb");
        list.add("Computer");
        list.add("Brain");

        result.setTextSize(30);

        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        unitFrom.setAdapter(adapter);
        unitTo.setAdapter(adapter);

        unitFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        unitTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}