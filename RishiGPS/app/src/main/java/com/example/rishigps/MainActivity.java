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
import java.util.List;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    LocationListener locationListener;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    Double lat = 0.0;
    Double lon = 0.0;

    TextView longitude;
    TextView latitude;
    TextView currentAddress;

    Float totalDistance;

    List<Address> addressList;


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


        try{
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            addressList = geocoder.getFromLocation(lat,lon,1);
            Log.d("LIST",addressList.toString());
            if(addressList != null && addressList.size() > 0){
                String address = addressList.get(0).getAddressLine(0);
                currentAddress.setText(address);
            }

        }catch (IOException e){

        }

        Location currentLocation = new Location("Current Location");
        currentLocation.setLatitude(lat);
        currentLocation.setLongitude(lon);

        Log.d("LOCATION",currentLocation.toString());

        Location previousLocation = new Location("Previous Location");
        if(addressList != null && addressList.size() > 0) {
            previousLocation.setLatitude(addressList.get(0).getLatitude());
            previousLocation.setLongitude(addressList.get(0).getLongitude());
        }

        Log.d("LOCATION",addressList.size()+"");

        totalDistance = previousLocation.distanceTo(currentLocation);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        currentAddress = findViewById(R.id.currentaddress);

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
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);     //5*60*1000
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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);     //5*60*1000
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
