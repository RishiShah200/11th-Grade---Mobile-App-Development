package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
    TextView extraInfo;
    public static final String KEY = "thisisthekey";
    public static final String KEY2 = "thisisthekey2";

    protected void onStart() {
        super.onStart();
        Log.d("tag","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag","Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag","Resume");
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         gamesWonText = findViewById(R.id.id_gamesWon);
         ppgText = findViewById(R.id.id_ppg);

        listView = findViewById(R.id.id_listview);

        list = new ArrayList<>();
        Candidate candidate1 =  new Candidate("Giannis Antetokounmpo", 30.9, 17,R.drawable.giannis,"Giannis Antetokounmpo has a 56.5% field goal percentage and plays 31.7 minutes per game");
        Candidate candidate2 =  new Candidate("James Harden", 38.9, 13,R.drawable.harden,"James Harden has a 43.8% field goal percentage and plays 37.6 minutes per game");
        Candidate candidate3 =  new Candidate("Luke Doncic",30.6,13,R.drawable.luka,"Luka Doncic has a 47.8% field goal percentage and plays 33.7 minutes per game");
        Candidate candidate4 =  new Candidate("LeBron James",25.7,17,R.drawable.lebron,"LeBron James has a 49.8% field goal percentage and plays 34.7 minutes per game");
        Candidate candidate5 =  new Candidate("Anthony Davis",26.1,17,R.drawable.anthonydavis,"Anthony Davis has a 49.0% field goal percentage and plays 34.2 minutes per game");
        Candidate candidate6 =  new Candidate("Jimmy Butler",18.8,14,R.drawable.jimmy,"Jimmy Butler has a 43.6% field goal percentage and plays 34.1 minutes per game");
        Candidate candidate7 =  new Candidate("Pascal Siakam",25.6,15,R.drawable.pascal,"Pascal Siakam has a 50.3% field goal percentage and plays 33.3 minutes per game");
        Candidate candidate8 =  new Candidate("Karl-Anthony Towns",25.9,10,R.drawable.karl,"Karl-Anthony Towns has a 43.9% field goal percentage and plays 31.2 minutes per game");
        Candidate candidate9 =  new Candidate("Fred Vanvleet",18.6,15,R.drawable.fred,"Fred Vanvleet o has a 45.9% field goal percentage and plays 30.3 minutes per game");
        Candidate candidate10 = new Candidate("Joel Embiid",22.8,14,R.drawable.joel,"Joel Embiid has a 51.5% field goal percentage and plays 34.2 minutes per game");
//extra info is going to be field goal percentage and minutes per game

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

        if(savedInstanceState != null){
            list = savedInstanceState.getParcelableArrayList("List");
            ppgText.setText(savedInstanceState.getString(KEY));
            gamesWonText.setText(savedInstanceState.getString(KEY2));
        }

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.adapter_custom,list);
        listView.setAdapter(customAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            extraInfo = findViewById(R.id.id_land_extrainfo);
        }



//https://www.basketball-reference.com/friv/mvp.html




    }

    public class Candidate implements Parcelable{

        private double ppg;
        private String name;
        private int gamesWon;
        private int img;
        private String moreInfo;

        public Candidate(String name, double ppg,  int gamesWon, int img, String moreInfo){

            this.ppg = ppg;
            this.name = name;
            this.gamesWon = gamesWon;
            this.img = img;
            this.moreInfo = moreInfo;

        }

        private Candidate(Parcel in){
            ppg = in.readDouble();
            name = in.readString();
            gamesWon = in.readInt();
            img = in.readInt();
            moreInfo = in.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeDouble(ppg);
            out.writeString(name);
            out.writeInt(gamesWon);
            out.writeInt(img);
            out.writeString(moreInfo);
        }

        public final Parcelable.Creator<Candidate> CREATOR = new Parcelable.Creator<Candidate>() {
            public Candidate createFromParcel(Parcel in) {
                return new Candidate(in);
            }

            public Candidate[] newArray(int size) {
                return new Candidate[size];
            }
        };

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
        public String getMoreInfo(){
           return moreInfo;
        }

    }

    public class CustomAdapter extends ArrayAdapter<Candidate>{

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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gamesWonText.setText("Games Won: "+list.get(position).gamesWon());
                    ppgText.setText((Double.toString(list.get(position).getPPG())));
                }
            });

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                extraInfo = findViewById(R.id.id_land_extrainfo);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gamesWonText.setText("Games Won: "+list.get(position).gamesWon());
                        ppgText.setText((Double.toString(list.get(position).getPPG())));
                        extraInfo.setText(list.get(position).getMoreInfo());
                    }
                });
            }

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

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("List",list);
        outState.putString(KEY, (String) ppgText.getText());
        outState.putString(KEY2, (String) gamesWonText.getText());

    }

}


