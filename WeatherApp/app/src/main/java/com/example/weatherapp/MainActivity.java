package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader reader;
    InputStreamReader streamReader;
    String text;
    String info = "";

    EditText zipCodeFinder;
    String zipcode;
    Button submitzipcode;

    String currentTemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipCodeFinder = findViewById(R.id.id_zipcode);
        submitzipcode = findViewById(R.id.id_submitzipcode);

        submitzipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncThread().execute(zipCodeFinder.getText().toString());
            }
        });
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {      //takes in three parameters. FIgure out what these are

        @Override
        protected Void doInBackground(String...params) {
            zipcode = params[0];

            try{
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=" + zipcode + "&appid=ece003293f26ba768ee74719308fc712");
                connection = url.openConnection();
                stream = connection.getInputStream();
                streamReader = new InputStreamReader(stream);
                reader = new BufferedReader(streamReader);
                while((text = reader.readLine()) != null){
                    info += text;
                }
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                Log.d("INFORMATION",info);
                JSONObject weatherData = new JSONObject(info);
                currentTemp = weatherData.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp") + "";
                Log.d("WEATHER",currentTemp);

            }catch (Exception e){

            }
        }
    }

}