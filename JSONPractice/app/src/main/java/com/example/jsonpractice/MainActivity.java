package com.example.jsonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.id_text);

        JSONObject owners = new JSONObject();

        JSONObject otherInvestments = new JSONObject();
        try{
            otherInvestments.put("company name","Broadcast.com");
        }catch(JSONException e){

        }

        JSONArray moreInfo = new JSONArray();

        moreInfo.put(otherInvestments.toString());


        JSONObject nbaOwner = new JSONObject();

        try{
            nbaOwner.put("name","Mark Cuban");
            nbaOwner.put("net worth","$4.1 Billion");
            nbaOwner.put("age",61);
            nbaOwner.put("Additional Information",moreInfo);
        }catch (JSONException e){

        }

        JSONObject nflOwner = new JSONObject();
        try{
            nflOwner.put("name","Jerry Jones");
            nflOwner.put("net worth","$5 Billion");
            nflOwner.put("age",77);
        }catch (JSONException e){

        }

        JSONObject companyOwner = new JSONObject();
        try{
            companyOwner.put("name","Mark Zuckerberg");
            companyOwner.put("net worth","$72 Billion");
            companyOwner.put("age",35);
        }catch (JSONException e){

        }

        JSONObject businessOwner = new JSONObject();
        try{
            businessOwner.put("name","Bill Gates");
            businessOwner.put("net worth","$107.6 Billion");
            businessOwner.put("age",64);
        }catch (JSONException e){

        }

        JSONObject movie = new JSONObject();
        try{
            movie.put("name","Avengers: Endgame");
            movie.put("gross sales","$853 Million");
            movie.put("movie length",182);
        }catch (JSONException e){

        }

        JSONObject movieOwners = new JSONObject();

        try{
            movieOwners.put("name","Kevin Feige");
            movieOwners.put("net worth","$26.8 Billion");
            movieOwners.put("age",46);
            movieOwners.put("Movie",movie);
        }catch (JSONException e){

        }

        try{
            owners.put("NBA Owner",nbaOwner);
            owners.put("NFL Owner",nflOwner);
            owners.put("Company Owner",companyOwner);
            owners.put("Business Owner",businessOwner);
            owners.put("Movie Owners",movieOwners);
        }catch (JSONException e){

        }



        text.setText(nbaOwner.toString());

        Log.d("TAG",owners.toString());


    }
}
