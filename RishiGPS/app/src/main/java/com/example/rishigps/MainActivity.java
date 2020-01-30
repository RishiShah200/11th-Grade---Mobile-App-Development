package com.example.rishigps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    LocationListener locationListener;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    Double lat = 0.0;
    Double lon = 0.0;

    float distance = 0.00f;

    TextView longitude;
    TextView latitude;
    TextView currentAddress;
    TextView totalDistance;

    TextView testing;


    List<Address> addressList;
    List<Location> locations;

    DecimalFormat df = new DecimalFormat("0.00##");

    float time = 0.00f;


    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {

        lat = location.getLatitude();
        lon = location.getLongitude();
        latitude.setText(lat + "");
        longitude.setText(lon + "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if(locations!=null) {
                if (locations.size() > 2) {
                    time = (location.getElapsedRealtimeNanos()) - (locations.get(locations.size() - 2).getElapsedRealtimeNanos());
                    time = time / 1000000000;
                    testing.setText(df.format(time));
                }
            }
        }


        try{
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            addressList = geocoder.getFromLocation(lat,lon,1);
            Log.d("LIST",addressList.toString());
            if(addressList != null && addressList.size() > 0){
                String address = addressList.get(0).getAddressLine(0);
                currentAddress.setText(address);
            }

            locations.add(location);
          //  testing.setText(locations.size()+"");
            totalDistance.setText(df.format(distance));

            if(locations!=null){
                if(locations.size() > 2){
                    distance += location.distanceTo(locations.get(locations.size()-2));
                }
            }

            
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        currentAddress = findViewById(R.id.currentaddress);

        locations = new ArrayList<>();

        totalDistance = findViewById(R.id.distance);

        testing = findViewById(R.id.testing);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                boolean permissionAccessCoarseLocationApproved =
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED;

                boolean permissionAccessFineLocationApproved =
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED;


                if (permissionAccessCoarseLocationApproved && permissionAccessFineLocationApproved) {
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);     //5*60*1000
                    } catch (Exception e) {

                    }
                } else {
                    // App doesn't have access to the device's location at all. Make full request
                    // for permission.
                    ActivityCompat.requestPermissions(this, new String[]{
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            },
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }

                // here to request the missing permissions, and then overriding

                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            else{
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);     //5*60*1000
                } catch (Exception e) {

                }
            }

        }

    }

    public void onRequestPermissionsResult ( int requestCode, String[] permissions, int[] grantResults){

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                      //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

}
