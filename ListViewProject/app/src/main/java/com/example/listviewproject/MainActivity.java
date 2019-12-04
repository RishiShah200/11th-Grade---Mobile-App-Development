package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
    TextView gamesWonText;
    TextView ppgText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         gamesWonText = findViewById(R.id.id_gamesWon);
         ppgText = findViewById(R.id.id_ppg);

        listView = findViewById(R.id.id_listview);

        list = new ArrayList<>();
        Candidate candidate1 =  new Candidate("Giannis Antetokounmpo", 30.9, 17,R.drawable.giannis);
        Candidate candidate2 =  new Candidate("James Harden", 38.9, 13,R.drawable.harden);
        Candidate candidate3 =  new Candidate("Luke Doncic",30.6,13,R.drawable.luka);
        Candidate candidate4 =  new Candidate("LeBron James",25.7,17,R.drawable.lebron);
        Candidate candidate5 =  new Candidate("Anthony Davis",26.1,17,R.drawable.anthonydavis);
        Candidate candidate6 =  new Candidate("Jimmy Butler",18.8,14,R.drawable.jimmy);
        Candidate candidate7 =  new Candidate("Pascal Siakam",25.6,15,R.drawable.pascal);
        Candidate candidate8 =  new Candidate("Karl-Anthony Towns",25.9,10,R.drawable.karl);
        Candidate candidate9 =  new Candidate("Fred Vanvleet",18.6,15,R.drawable.fred);
        Candidate candidate10 = new Candidate("Joel Embiid",22.8,14,R.drawable.joel);


        list.add(candidate1);
        list.add(candidate2);
        list.add(candidate3);
        list.add(candidate4);
        list.add(candidate5);
        list.add(candidate6);
        list.add(candidate7);
        list.add(candidate8);
        list.add(candidate9);
        list.add(candidate10);

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.adapter_custom,list);
        listView.setAdapter(customAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

        }



//https://www.basketball-reference.com/friv/mvp.html
    }

    public class Candidate{

        private double ppg;
        private String name;
        private int gamesWon;
        private int img;

        public Candidate(String name, double ppg,  int gamesWon, int img){

            this.ppg = ppg;
            this.name = name;
            this.gamesWon = gamesWon;
            this.img = img;

        }

        public double getPPG(){
            return ppg;
        }

        public String getName(){
            return name;
        }

        public int gamesWon(){
            return gamesWon;
        }
        public int getImg(){
            return img;
        }

    }

    public class CustomAdapter extends ArrayAdapter<Candidate> {

        ArrayList<Candidate> list;
        Context parentContext;
        int xmlResource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Candidate> objects) {
            super(context, resource, objects);

            list = objects;
            parentContext = context;
            xmlResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
            View view = layoutInflater.inflate(R.layout.adapter_custom,null);

            TextView names = view.findViewById(R.id.id_adapter_name);
            Button button = view.findViewById(R.id.id_adapter_remove_button);

            ImageView imageView = view.findViewById(R.id.id_adpater_image);
            imageView.setImageResource(list.get(position).getImg());

            names.setText(list.get(position).getName());

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  gamesWonText.setText("Points Per Game: "+list.get(position).gamesWon());
                  ppgText.setText((Double.toString(list.get(position).getPPG())));
                }
            });

          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  list.remove(position);
                  notifyDataSetChanged();
              }
          });

            return view;
        }

    }

}


