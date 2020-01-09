package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

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


    TextView currentTemp;

    TextView day1maxtemp;
    TextView day2maxtemp;
    TextView day3maxtemp;
    TextView day4maxtemp;
    TextView day5maxtemp;

    DecimalFormat df = new DecimalFormat("#,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipCodeFinder = findViewById(R.id.id_zipcode);
        submitzipcode = findViewById(R.id.id_submitzipcode);

        currentTemp = findViewById(R.id.id_currenttemp);

        day1maxtemp = findViewById(R.id.day1maxtemp);
        day2maxtemp = findViewById(R.id.day2maxtemp);
        day3maxtemp = findViewById(R.id.day3tmaxemp);
        day4maxtemp = findViewById(R.id.day4maxtemp);
        day5maxtemp = findViewById(R.id.day5maxtemp);


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
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=" + zipcode + "&appid=ece003293f26ba768ee74719308fc712");   //5 day
               // url = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=ece003293f26ba768ee74719308fc712");
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
               // currentTemp.setText(weatherData.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp") + "");
                day1maxtemp.setText("High: " + df.format(convertTemp(weatherData.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp"))) + "°F");
                day2maxtemp.setText("High: " + df.format(convertTemp(weatherData.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp"))) + "°F");
                day3maxtemp.setText("High: " + df.format(convertTemp(weatherData.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp"))) + "°F");
                day4maxtemp.setText("High: " + df.format(convertTemp(weatherData.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp"))) + "°F");
                day5maxtemp.setText("High: " + df.format(convertTemp(weatherData.getJSONArray("list").getJSONObject(5).getJSONObject("main").getDouble("temp"))) + "°F");
             //   currentTemp.setText(weatherData.getJSONObject("main").getDouble("temp")+ "");

            }catch (Exception e){

            }
        }
    }

    public double convertTemp(double weather){
        return (weather - 273.15) * 9/5 + 32;
    }

}