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
    ArrayList<String> listFrom;
    ArrayList<String> listTo;
    ArrayAdapter<String> adapterFrom;
    ArrayAdapter<String> adapterTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitFrom = findViewById(R.id.id_unitfrom);
        enterNumber = findViewById(R.id.id_enternumber);
        unitTo = findViewById(R.id.id_unitto);
        result = findViewById(R.id.id_result);

        listFrom = new ArrayList<>();
        listTo = new ArrayList<>();

        listTo.add("Akash");
        listTo.add("Rachit");
        listTo.add("Arav");
        listTo.add("Teacher");

        listFrom.add("Zareeb");
        listFrom.add("Computer");
        listFrom.add("Akash");
        listFrom.add("School");

        adapterFrom = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listFrom);
        unitFrom.setAdapter(adapterFrom);

        adapterTo = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listTo);
        unitTo.setAdapter(adapterTo);

        unitFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
