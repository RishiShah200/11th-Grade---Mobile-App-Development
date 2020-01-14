package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    ImageView submitzipcode;
    String sendZipCodeToThread = "";


    TextView currentTemp;
    TextView currentHighTemp;
    TextView currentLowTemp;
    TextView currentTime;
    ImageView currentWeatherConditions;
    String currentWeatherInfo;


    String forecastInfo;

    JSONObject forecastData;
    JSONObject weatherData;
    String formattedDate;

    DecimalFormat df = new DecimalFormat("#,###,##0.00");

    ArrayList<Weather> listWeather;
    ListView listView;

    String cityname;
    TextView city;
    TextView quote;

    Double latitude;
    Double longitude;
    static String TAG = "MainActivity";
    Location gps_loc = null,network_loc=null,final_loc = null;
    String locationZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this,"not granted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"granted",Toast.LENGTH_SHORT).show();
        }

        try{
            Criteria locationCriteria = new Criteria();
            String providerName = locationManager.getBestProvider(locationCriteria,true);
            gps_loc = locationManager.getLastKnownLocation(providerName);
            network_loc = locationManager.getLastKnownLocation(providerName);
        }catch(Exception e){

        }

        if(gps_loc!=null){
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else if(network_loc != null){
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else{
            latitude = 0.0;
            longitude = 0.0;
        }


        try{
            Geocoder geocoder = new Geocoder(getApplicationContext(),Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postal_code = addresses.get(0).getPostalCode();
                locationZipCode = postal_code;
                String knownName = addresses.get(0).getFeatureName();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        city = findViewById(R.id.cityname);

        quote = findViewById(R.id.quote);

        zipCodeFinder = findViewById(R.id.id_zipcode);
        submitzipcode = findViewById(R.id.submitzipcode);

        currentTemp = findViewById(R.id.id_currenttemp);

        listView = findViewById(R.id.listView);

        currentHighTemp = findViewById(R.id.id_currentHighTemp);
        currentLowTemp = findViewById(R.id.id_currentLowTemp);
        currentTime = findViewById(R.id.id_currentTime);

        currentWeatherConditions = findViewById(R.id.currentweatherimage);

        listWeather = new ArrayList<Weather>();

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

        Log.d("ZIPCODE",locationZipCode);
        if(locationZipCode.length() > 3){
            sendZipCodeToThread = locationZipCode;
            new AsyncThread().execute(sendZipCodeToThread);
            zipCodeFinder.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
        submitzipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncThread().execute(sendZipCodeToThread);
                zipCodeFinder.onEditorAction(EditorInfo.IME_ACTION_DONE);
                zipCodeFinder.setCursorVisible(false);
            }
        });
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {      //takes in three parameters. FIgure out what these are

        @Override
        protected Void doInBackground(String... params) {
            zipcode = params[0];

            Log.d("zipcode", zipcode);

            try {        //for the 5 day forecast
                url = new URL("https://api.openweathermap.org/data/2.5/forecast/?zip=" + zipcode + ",us&appid=ece003293f26ba768ee74719308fc712");   //5 day
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
                Log.d("INFORMATION",info2);
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

                JSONArray forecast = forecastData.getJSONArray("list");
                Log.d("FORECASE",forecast.toString());

                for(int x = 0;x<forecast.length();x++){
                    JSONObject object = forecast.getJSONObject(x);
                    listWeather.add(new Weather(object));
                }

                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,R.layout.adapter_custom,listWeather);
                listView.setAdapter(customAdapter);

                currentTime.setText(formattedDate);

                cityname = weatherData.getString("name");
                city.setText(cityname);

                currentTemp.setText(df.format(convertTemp((int)(weatherData.getJSONObject("main").getDouble("temp")))) + "°F");
                currentHighTemp.setText("Day " + df.format(convertTemp((int)(weatherData.getJSONObject("main").getDouble("temp_max")))) + "°F");
                currentLowTemp.setText("Night " + df.format(convertTemp((int)(weatherData.getJSONObject("main").getDouble("temp_min")))) + "°F");
                currentWeatherInfo = weatherData.getJSONArray("weather").getJSONObject(0).getString("icon");

                if (currentWeatherInfo.equals("01d")){
                    quote.setText("You never know the true value of the sun until it becomes a memory \n -Spongebob");
                }
                else if(currentWeatherInfo.equals("02d")){
                    quote.setText("Hey Patrick, I thought of something funnier than clouds...few clouds!");
                }
                else if(currentWeatherInfo.equals("03d")){
                    quote.setText("Well, it may be cloudy, but it's also daytime");
                }
                else if(currentWeatherInfo.equals("04d")){

                }
                else if(currentWeatherInfo.equals("09d")){

                }
                else if(currentWeatherInfo.equals("10d")){
                    quote.setText("Rain cannot replace friendship. \n - Patrick");
                }
                else if(currentWeatherInfo.equals("11d")){

                }
                else if(currentWeatherInfo.equals("13d")){
                    quote.setText("Once there was snow. There was so much that everyone died...the end");
                }
                else if(currentWeatherInfo.equals("50n")){
                    quote.setText("Too bad mist is not here to enjoy mist not being here \n -Squidward");
                }
                else if(currentWeatherInfo.equals("01n")){
                    Log.d("REACHED","RECHED");
                    quote.setText("You don't need the sun to drive \n -Spongebob");
                }
                else if(currentWeatherInfo.equals("02n")){
                    quote.setText("Hey Patrick, I thought of something funnier than clouds...few clouds!");
                }
                else if(currentWeatherInfo.equals("03n")){

                }
                else if(currentWeatherInfo.equals("04n")){

                }
                else if(currentWeatherInfo.equals("09n")){
                    quote.setText("This is not your average, everyday rain shower. This is...ADVANCED rain shower.");
                }
                else if(currentWeatherInfo.equals("10n")){
                    quote.setText("Rain cannot replace friendship. \n - Patrick");
                }
                else if(currentWeatherInfo.equals("11n")){

                }
                else if(currentWeatherInfo.equals("13n")){

                }
                else if(currentWeatherInfo.equals("50n")){
                    quote.setText("Remember, mist is illegal on other planets.");
                }
                decideImage(currentWeatherInfo,currentWeatherConditions);

            } catch (Exception e) {

            }

        }
    }

    public int convertTemp(int weather) {
        return (weather - 273) * 9 / 5 + 32;
    }

    public void decideImage(String s, ImageView v) {
        switch (s) {   //use this https://www.iconfinder.com/iconsets/weather-color-2
            case "01d":
                v.setImageResource(R.drawable.ic_clearsky);
                break;
            case "02d":
                v.setImageResource(R.drawable.ic_fewclouds);
                break;
            case "03d":
                v.setImageResource(R.drawable.scatteredclouds);
                break;
            case "04d":
                v.setImageResource(R.drawable.brokenclouds);
                break;
            case "09d":
                v.setImageResource(R.drawable.showerrain);
                break;
            case "10d":
                v.setImageResource(R.drawable.rain);
                break;
            case "11d":
                v.setImageResource(R.drawable.thunderstorm);
                break;
            case "13d":
                v.setImageResource(R.drawable.snow);
                break;
            case "50d":
                v.setImageResource(R.drawable.mist);
                break;
            case "01n":
                v.setImageResource(R.drawable.ic_clearskynight);
                break;
            case "02n":
                v.setImageResource(R.drawable.ic_fewclouds);
                break;
            case "03n":
                v.setImageResource(R.drawable.scatteredclouds);
                break;
            case "04n":
                v.setImageResource(R.drawable.brokenclouds);
                break;
            case "09n":
                v.setImageResource(R.drawable.showerrain);
                break;
            case "10n":
                v.setImageResource(R.drawable.rain);
                break;
            case "11n":
                v.setImageResource(R.drawable.thunderstorm);
                break;
            case "13n":
                v.setImageResource(R.drawable.snow);
                break;
            case "50n":
                v.setImageResource(R.drawable.mist);
                break;
            default:
                v.setImageResource(R.drawable.ic_launcher_background);
                break;
        }
    }

    public class Weather {
        private Date date;
        private int temp;
        private int tempMin;
        private int tempMax;
        private String icon;
        private String description;

        public Weather(JSONObject json) {
            try {
                long unixTime = json.getLong("dt");
                date = new Date(unixTime * 1000);
                temp = (int) Math.round(json.getJSONObject("main").getDouble("temp"));
                tempMin = (int) Math.round(json.getJSONObject("main").getDouble("temp_min"));
                tempMax = (int) Math.round(json.getJSONObject("main").getDouble("temp_max"));
                icon = json.getJSONArray("weather").getJSONObject(0).getString("icon");
                description = json.getJSONArray("weather").getJSONObject(0).getString("description");
                String[] words = description.split(" ");
                description = "";
                for (String word : words) {
                    word = word.substring(0, 1).toUpperCase() + word.substring(1);
                    description += word + " ";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Date getDate() {
            return date;
        }

        public String getFormattedDate() {
            SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-5"));
            String formattedDate = sdf.format(date);
            return formattedDate;
        }

        public int getTemp() {
            return temp;
        }

        /*public String getQuotation(){
            if (icon.equals("01d")){
                return "You never know the true value of the sun until it becomes a memory /n -Spongebob";
            }
            else if(icon.equals("02d")){
                return "Hey Patrick, I thought of something funnier than clouds...few clouds!";
            }
            else if(icon.equals("03d")){
                return "Well, it may be cloudy, but it's also daytime";
            }
            else if(icon.equals("04d")){

            }
            else if(icon.equals("09d")){

            }
            else if(icon.equals("10d")){
                return "Rain cannot replace friendship. /n - Patrick";
            }
            else if(icon.equals("11d")){

            }
            else if(icon.equals("13d")){
                return "Once there was snow. There was so much that everyone died...the end";
            }
            else if(icon.equals("50n")){
                return "Too bad mist is not here to enjoy mist not being here /n -Squidward";
            }
            else if(icon.equals("01n")){
                Log.d("REACHED","RECHED");
                return "You don't need the sun to drive /n -Spongebob";
            }
            else if(icon.equals("02n")){
                return "Hey Patrick, I thought of something funnier than clouds...few clouds!";
            }
            else if(icon.equals("03n")){

            }
            else if(icon.equals("04n")){

            }
            else if(icon.equals("09n")){
                return "This is not your average, everyday rain shower. This is...ADVANCED rain shower.";
            }
            else if(icon.equals("10n")){
                return "Rain cannot replace friendship. /n - Patrick";
            }
            else if(icon.equals("11n")){

            }
            else if(icon.equals("13n")){

            }
            else if(icon.equals("50n")){
                return "Remember, mist is illegal on other planets.";
            }
            return null;
        }*/

        public int getTempMin() {
            return tempMin;
        }

        public int getTempMax() {
            return tempMax;
        }

        public String getIcon() {
            return icon;
        }

        public int getImage() {
            int image = 0;
            switch (icon) {
                case "01d":
                    image = R.drawable.ic_clearsky;
                    break;
                case "02d":
                    image = R.drawable.ic_fewclouds;
                    break;
                case "03d":
                    image = R.drawable.scatteredclouds;
                    break;
                case "04d":
                    image = R.drawable.brokenclouds;
                    break;
                case "09d":
                    image = R.drawable.showerrain;
                    break;
                case "10d":
                    image = R.drawable.rain;
                    break;
                case "11d":
                    image = R.drawable.thunderstorm;
                    break;
                case "13d":
                    image  = (R.drawable.snow);
                    break;
                case "50d":
                    image = (R.drawable.mist);
                    break;
                case "01n":
                    image = (R.drawable.ic_clearskynight);
                    break;
                case "02n":
                    image = (R.drawable.ic_fewclouds);
                    break;
                case "03n":
                    image = (R.drawable.scatteredclouds);
                    break;
                case "04n":
                    image = (R.drawable.brokenclouds);
                    break;
                case "09n":
                    image = (R.drawable.showerrain);
                    break;
                case "10n":
                    image = (R.drawable.rain);
                    break;
                case "11n":
                    image = (R.drawable.thunderstorm);
                    break;
                case "13n":
                    image = (R.drawable.snow);
                    break;
                case "50n":
                    image = (R.drawable.mist);
                    break;
                default:
                    image = (R.drawable.ic_launcher_background);
                    break;
            }
            return image;
        }

        public String getDescription() {
            return description;
        }

    }


    public class CustomAdapter extends ArrayAdapter<Weather> {

        ArrayList<Weather> list;
        Context parentContext;
        int xmlResource;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Weather> objects) {
            super(context, resource, objects);

            list = objects;
            parentContext = context;
            xmlResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
            View view = layoutInflater.inflate(R.layout.adapter_custom, null);

            TextView date = view.findViewById(R.id.date);
            ImageView image = view.findViewById(R.id.image);
            TextView description = view.findViewById(R.id.description);
            TextView tempMin = view.findViewById(R.id.tempMin);
            TextView tempMax = view.findViewById(R.id.tempMax);

            Weather weather = list.get(position);
            date.setText(weather.getFormattedDate());

            description.setText(weather.getDescription());
            tempMax.setText(convertTemp(weather.getTempMax()) + "°");
            tempMin.setText("/" + convertTemp(weather.getTempMin()) + "°");
            image.setImageResource(weather.getImage());

           // quote.setText(weather.getQuotation());


            return view;
        }

    }

}