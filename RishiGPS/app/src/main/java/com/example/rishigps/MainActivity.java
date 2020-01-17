package com.example.rishigps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    LocationListener locationListener;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    Double lat = 0.0;
    Double lon = 0.0;


    TextView longitude;
    TextView latitude;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions

                boolean permissionAccessCoarseLocationApproved =
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED;

                if (permissionAccessCoarseLocationApproved) {
                    boolean backgroundLocationPermissionApproved =
                            ActivityCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED;

                    if (backgroundLocationPermissionApproved) {
                        // App can access location both in the foreground and in the background.
                        // Start your service that doesn't have a foreground service type
                        // defined.
                    } else {
                        // App can only access location in the foreground. Display a dialog
                        // warning the user that your app must have all-the-time access to
                        // location in order to function properly. Then, request background
                        // location.
                        ActivityCompat.requestPermissions(this, new String[] {
                                        Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                } else {
                    // App doesn't have access to the device's location at all. Make full request
                    // for permission.
                    ActivityCompat.requestPermissions(this, new String[] {
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                            },
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }

                // here to request the missing permissions, and then overriding
                public void onRequestPermissionsResult(int requestCode,
                String[] permissions, int[] grantResults) {

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
                                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                                }

                            } else {

                                // permission denied, boo! Disable the
                                // functionality that depends on this permission.

                            }
                            return;
                        }

                    }
                }

                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 20, locationListener);     //5*60*1000
    }

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
        latitude.setText(lat+"");
        longitude.setText(lon+"");



    }
}
