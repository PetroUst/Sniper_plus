package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageButton settings_button;
    ImageButton map_button;
    private ArrayList permissionsToRequest;
    private final ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        settings_button = (ImageButton) findViewById(R.id.settings);
        map_button = (ImageButton) findViewById(R.id.minimap);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v) {
        TextView res_vertical = (TextView) findViewById(R.id.vertical_res);
        TextView res_horizontal = (TextView) findViewById(R.id.vertical_res2);
        //дістаю дані з текстових полів на головному екрані
        try {
            EditText distance = (EditText) findViewById(R.id.edit_distance);
            EditText wind_speed = (EditText) findViewById(R.id.edit_wind_speed);
            EditText wind_degree = (EditText) findViewById(R.id.edit_wind_degree);
            EditText target_height = (EditText) findViewById(R.id.edit_target_height);
            EditText bullet_speed = (EditText) findViewById(R.id.edit_bullet_speed);
            EditText bc = (EditText) findViewById(R.id.edit_bc);


            //конвертую строки в дабл і передаю в поля класу shot
            Shot.setDistance(Double.parseDouble(distance.getText().toString()));
            Shot.Bullet.setBc(Double.parseDouble(bc.getText().toString()));
            Shot.setWindDegree(Double.parseDouble(wind_degree.getText().toString()));
            Shot.setWindSpeed(Double.parseDouble(wind_speed.getText().toString()));
            Shot.setTargetHeight(Double.parseDouble(target_height.getText().toString()));

            Shot.Bullet.setWeight(0.010866);
            Shot.Bullet.setSquare(0.000048);
            Shot.Bullet.setSpeed(Double.parseDouble(bullet_speed.getText().toString()));


            String verticalC = String.format("%.2f", Shot.correctionV()) + "'";
            String horizontalC = String.format("%.2f", Shot.correctionH()) + "'";

            res_vertical.setText( verticalC );
            res_horizontal.setText( horizontalC );
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.error_Enter_all_data), Toast.LENGTH_LONG).show();
        }
//        permissions.add(ACCESS_FINE_LOCATION);
//        permissions.add(ACCESS_COARSE_LOCATION);
//
//        permissionsToRequest = findUnAskedPermissions(permissions);
//        //get the permissions we have asked for before but are not granted..
//        //we will store this in a global list to access later.
//
//
//        if (permissionsToRequest.size() > 0)
//            requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
//
//
//        Button btn = (Button) findViewById(R.id.btn);
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                locationTrack = new LocationTrack(MainActivity.this);
//
//                if (locationTrack.canGetLocation()) {
//
//
//                    double longitude = locationTrack.getLongitude();
//                    double latitude = locationTrack.getLatitude();
//
//                    Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
//                    TextView location = (TextView) findViewById(R.id.textView3);
//                    location.setText("Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude));
//                } else {
//
//                    locationTrack.showSettingsAlert();
//                }
//
//            }
//        });
    }

    public void move_to_map(View v) {
        String uri = "http://maps.google.com/maps?daddr=Lviv, Lviv Oblast, Ukraine";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void move_to_settings(View v) {
        Intent intentSettings = new Intent(MainActivity.this, Settings.class);
        startActivity(intentSettings);
    }

}