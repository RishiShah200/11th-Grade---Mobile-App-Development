package com.example.listviewsandlifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listview);

        arrayList = new ArrayList<>();
        arrayList.add("Rishi");
        arrayList.add("Rachit");
        arrayList.add("Akash");
        arrayList.add("Zareeb");
        arrayList.add("Arav");
        arrayList.add("Ryan");
        arrayList.add("Ian");
        arrayList.add("Jeff");
        arrayList.add("Neel");
        arrayList.add("Schiff");
        arrayList.add("Bobby");
        arrayList.add("Steve");
        arrayList.add("West");
        arrayList.add("Jim");

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.adapter_custom,arrayList);
        listView.setAdapter(customAdapter);

    }

    public class CustomAdapter extends ArrayAdapter<String>{

        List<String> list;
        Context parentContext;
        int xmlResource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);

            list = objects;
            parentContext = context;
            xmlResource = resource;
        }

        

    }
}
