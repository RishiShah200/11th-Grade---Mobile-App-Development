package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Candidate> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listview);


        list = new ArrayList<>();
        list.add(new Candidate("Giannis Antetokounmpo", 30.9, 17));
        list.add(new Candidate("James Harden", 38.9, 13));
        list.add(new Candidate("Luke Doncic",30.6,13));
        list.add(new Candidate("LeBron James",25.7,17));
        list.add(new Candidate("Anthony Davis",26.1,17));
        list.add(new Candidate("Jimmy Butler",18.8,14));
        list.add(new Candidate("Pascal Siakam",25.6,15));
        list.add(new Candidate("Karl-Anthony Towns",25.9,10));
        list.add(new Candidate("Fred Vanvleet",18.6,15));
        list.add(new Candidate("Joel Embiid",22.8,14));

//https://www.basketball-reference.com/friv/mvp.html
    }


    public class CustomAdapter extends ArrayAdapter<String> {

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

            TextView textView = view.findViewById(R.id.id_adapter_name);
            Button button = view.findViewById(R.id.id_adapter_remove_button);

            /*textView.setText("Name : "+list.get(position));
            button.setText("Position " + position);*/

            return view;
        }



    }

    public class Candidate{

        private double ppg;
        private String name;
        private int gamesWon;

        public Candidate(String name, double ppg,  int gamesWon){

            this.ppg = ppg;
            this.name = name;
            this.gamesWon = gamesWon;

        }

        public int getPPG(int ppg){
            return ppg;
        }

        public String getName(String name){
            return name;
        }

        public int gamesWon(int gamesWon){
            return gamesWon;
        }

    }

}


