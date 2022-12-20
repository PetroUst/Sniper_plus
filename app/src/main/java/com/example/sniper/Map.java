package com.example.sniper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    LocationTrack locationTrack;
    double distance;

    public void centreMapOnLocation(Location location, String title){

        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
        //mMap.clear();
        mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,12));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //centreMapOnLocation(lastKnownLocation,"Your Location");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationTrack = new LocationTrack(Map.this);

        if (locationTrack.canGetLocation()) {
            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            LatLng userLocation = new LatLng(latitude,longitude);
            //mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,16));
        }

        mMap.setOnMapLongClickListener(this);
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        Intent intent = getIntent();
        if (intent.getIntExtra("Place Number",0) == 0 ){

            // Zoom into users location
            locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//                    centreMapOnLocation(location,"Your Location");
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }

            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //centreMapOnLocation(lastKnownLocation,"Your Location");
            } else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        } else {

            Location placeLocation = new Location(LocationManager.GPS_PROVIDER);
            //placeLocation.setLatitude(MainActivity.location.get(intent.getIntExtra("Place Number",0)).latitude);
            //placeLocation.setLongitude(MainActivity.location.get(intent.getIntExtra("Place Number",0)).longitude);

            //centreMapOnLocation(placeLocation,MainActivity.places.get(intent.getIntExtra("Place Number",0)));
        }


    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String adress ="";
        try {
            List<Address> listaddress = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);

            if (listaddress != null && listaddress.size()>0){
                if (listaddress.get(0).getThoroughfare() != null){

                    if (listaddress.get(0).getSubThoroughfare() != null){
                        adress += listaddress.get(0).getSubThoroughfare() + "";

                    }
                    adress += listaddress.get(0).getThoroughfare();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions().position(latLng).title(adress));
        /*AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Are you sure to Exit")
//set message
                .setMessage("Exiting will call finish() method")
//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        //finish();
                        mMap.clear();
                    }
                })
//set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();*/
        //MainActivity.places.add(adress);
        //MainActivity.location.add(latLng);
        //MainActivity
        //MainActivity.arrayAdapter.notifyDataSetChanged();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.clear();
        markerOptions.title(String.valueOf(latLng));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.small));
        markerOptions.getPosition();
        mMap.clear();
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        //Marker mCurrLocationMarker = mMap.addMarker(markerOptions);
        locationTrack = new LocationTrack(Map.this);
        double target_long = latLng.longitude;
        double target_lat = latLng.latitude;
        if (locationTrack.canGetLocation()) {
            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            LatLng userLocation = new LatLng(latitude,longitude);
            //mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation));
            distance=2*6371*Math.asin(Math.sqrt
                    (Math.pow(Math.sin((Math.toRadians(target_lat)-Math.toRadians(latitude))/2),2)+
                    Math.cos(Math.toRadians(latitude))*Math.cos(Math.toRadians(target_lat))*Math.pow(Math.sin((Math.toRadians(target_long)-Math.toRadians(longitude))/2),2)));


        }
        distance*=1000;
//        Shot.setDistance(distance);

        Toast.makeText(this, getString(R.string.distance_ukr)+": "+Double.toString(distance)+getString(R.string.meters), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("distanсe",(String.format("%.2f",distance).replace(',','.')));
        setResult(RESULT_OK,intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mMap.clear();
    }
}