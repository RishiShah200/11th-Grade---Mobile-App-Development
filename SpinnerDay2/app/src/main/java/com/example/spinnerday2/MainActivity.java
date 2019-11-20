package com.example.spinnerday2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        TextView textView;
        Spinner spinner;
        Button button;
        ArrayList<String> list;
        ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_output);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            spinner = findViewById(R.id.id_spinner);
            button = findViewById(R.id.id_button);

            list = new ArrayList<>();

            list.add("Rishi");
            list.add("Akash");
            list.add("Zareeb");
            list.add("Rachit");

            adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    textView.setText(list.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    textView.setText("All Elements Removed");
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
            });

        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
           // textView.setTextSize(75);
            textView.setText("The app does not work in landscape mode");
        }
    }
}
