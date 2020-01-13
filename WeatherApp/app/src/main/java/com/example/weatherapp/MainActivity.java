package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader reader;
    InputStreamReader streamReader;
    String text;        //used for the 5 day forecast
    String info = "";

    URL url2;
    URLConnection connection2;
    InputStream stream2;
    BufferedReader reader2;
    InputStreamReader streamReader2;
    String text2;       //used for the current weather
    String info2 = "";

    EditText zipCodeFinder;
    String zipcode;
    Button submitzipcode;
    String sendZipCodeToThread = "";


    TextView currentTemp;
    TextView currentHighTemp;
    TextView currentLowTemp;
    TextView currentTime;
    ImageView currentWeatherConditions;
    String currentWeatherInfo;

    TextView day1maxtemp;
    TextView day2maxtemp;
    TextView day3maxtemp;
    TextView day4maxtemp;
    TextView day5maxtemp;
    TextView day1lowtemp;
    TextView day2lowtemp;
    TextView day3lowtemp;
    TextView day4lowtemp;
    TextView day5lowtemp;
    String forecastInfo;

    JSONObject forecastData;
    JSONObject weatherData;
    String formattedDate;

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

        day1lowtemp = findViewById(R.id.day1lowtemp);
        day2lowtemp = findViewById(R.id.day2lowtemp);
        day3lowtemp = findViewById(R.id.day3lowtemp);
        day4lowtemp = findViewById(R.id.day4lowtemp);
        day5lowtemp = findViewById(R.id.day5lowtemp);

        currentHighTemp = findViewById(R.id.id_currentHighTemp);
        currentLowTemp = findViewById(R.id.id_currentLowTemp);
        currentTime = findViewById(R.id.id_currentTime);

        currentWeatherConditions = findViewById(R.id.currentweatherimage);

        zipCodeFinder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sendZipCodeToThread = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        submitzipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncThread().execute(sendZipCodeToThread);
            }
        });
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {      //takes in three parameters. FIgure out what these are

        @Override
        protected Void doInBackground(String... params) {
            zipcode = params[0];

            Log.d("zipcode", zipcode);

            try {        //for the 5 day forecast
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=" + zipcode + "&appid=ece003293f26ba768ee74719308fc712");   //5 day
                connection = url.openConnection();
                stream = connection.getInputStream();
                streamReader = new InputStreamReader(stream);
                reader = new BufferedReader(streamReader);
                info = "";
                while ((text = reader.readLine()) != null) {
                    info += text;
                }

                url2 = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=ece003293f26ba768ee74719308fc712");
                connection2 = url2.openConnection();
                stream2 = connection2.getInputStream();
                streamReader2 = new InputStreamReader(stream2);
                reader2 = new BufferedReader(streamReader2);
                info2 = "";
                while ((text2 = reader2.readLine()) != null) {
                    info2 += text2;
                }

                forecastData = new JSONObject(info);
                weatherData = new JSONObject(info2);
                long unixSeconds = weatherData.getLong("dt");
                Date date = new java.util.Date(unixSeconds * 1000L);
                SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-5"));
                formattedDate = sdf.format(date);

            } catch (Exception e) {

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {        //for the 5 day forecast

                day1maxtemp.setText("High: " + df.format(convertTemp(forecastData.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_max"))) + "°F");
                day2maxtemp.setText("High: " + df.format(convertTemp(forecastData.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_max"))) + "°F");
                day3maxtemp.setText("High: " + df.format(convertTemp(forecastData.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_max"))) + "°F");
                day4maxtemp.setText("High: " + df.format(convertTemp(forecastData.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp_max"))) + "°F");
                day5maxtemp.setText("High: " + df.format(convertTemp(forecastData.getJSONArray("list").getJSONObject(5).getJSONObject("main").getDouble("temp_max"))) + "°F");

                currentTime.setText(formattedDate);

                currentTemp.setText(df.format(convertTemp(weatherData.getJSONObject("main").getDouble("temp"))) + "°F");
                currentHighTemp.setText("Day " + df.format(convertTemp(weatherData.getJSONObject("main").getDouble("temp_max"))) + "°F");
                currentLowTemp.setText("Night " + df.format(convertTemp(weatherData.getJSONObject("main").getDouble("temp_min"))) + "°F");
                currentWeatherInfo = weatherData.getJSONArray("weather").getJSONObject(0).getString("icon");

                switch (currentWeatherInfo) {   //use this https://www.iconfinder.com/iconsets/weather-color-2
                    case "01d":
                        currentWeatherConditions.setImageResource(R.drawable.ic_clearsky);
                        break;
                    case "02d":
                        currentWeatherConditions.setImageResource(R.drawable.ic_fewclouds);
                        break;
                    case "03d":
                        currentWeatherConditions.setImageResource(R.drawable.scatteredclouds);
                        break;
                    case "04d":
                        currentWeatherConditions.setImageResource(R.drawable.brokenclouds);
                        break;
                    case "09d":
                        currentWeatherConditions.setImageResource(R.drawable.showerrain);
                        break;
                    case "10d":
                        currentWeatherConditions.setImageResource(R.drawable.rain);
                        break;
                    case "11d":
                        currentWeatherConditions.setImageResource(R.drawable.thunderstorm);
                        break;
                    case "13d":
                        currentWeatherConditions.setImageResource(R.drawable.snow);
                        break;
                    case "50d":
                        currentWeatherConditions.setImageResource(R.drawable.mist);
                        break;
                    case "01n":
                        currentWeatherConditions.setImageResource(R.drawable.ic_clearskynight);
                        break;
                    case "02n":
                        currentWeatherConditions.setImageResource(R.drawable.ic_fewclouds);
                        break;
                    case "03n":
                        currentWeatherConditions.setImageResource(R.drawable.scatteredclouds);
                        break;
                    case "04n":
                        currentWeatherConditions.setImageResource(R.drawable.brokenclouds);
                        break;
                    case "09n":
                        currentWeatherConditions.setImageResource(R.drawable.showerrain);
                        break;
                    case "10n":
                        currentWeatherConditions.setImageResource(R.drawable.rain);
                        break;
                    case "11n":
                        currentWeatherConditions.setImageResource(R.drawable.thunderstorm);
                        break;
                    case "13n":
                        currentWeatherConditions.setImageResource(R.drawable.snow);
                        break;
                    case "50n":
                        currentWeatherConditions.setImageResource(R.drawable.mist);
                        break;
                    default:
                        currentWeatherConditions.setImageResource(R.drawable.ic_launcher_background);
                        break;
                }
            } catch (Exception e) {

            }

        }
    }

    public double convertTemp(double weather) {
        return (weather - 273.15) * 9 / 5 + 32;
    }


}