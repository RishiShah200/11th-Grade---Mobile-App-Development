package com.example.listviewsandlifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
            View view = layoutInflater.inflate(R.layout.adapter_custom,null);

            TextView textView = view.findViewById(R.id.id_adapter_text);
            Button button = view.findViewById(R.id.id_adapter_button);

            return view;
        }
    }
}
