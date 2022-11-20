package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

//test
public class MainActivity extends AppCompatActivity {

    private float dpHeight;
    private float dpWidth;
    private float dDensity;
    private int Width = 409;
    private int Height = 571;

    public int calcHeight(float value){
        return (int)(dpHeight * (value/Height));
    }
    public int calcWidth(float value){
        return (int) (dpWidth * (value/Width));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        TextView vertical = (TextView) findViewById(R.id.vertical);
        TextView horizontal = (TextView) findViewById(R.id.vertical2);
        TextView vertical_res = (TextView) findViewById(R.id.vertical_res);
        TextView horizontal_res = (TextView) findViewById(R.id.vertical_res2);
        TextView target_height = (TextView) findViewById(R.id.target_height);
        TextView bullet_speed = (TextView) findViewById(R.id.bullet_speed);
        TextView bal_coefficient = (TextView) findViewById(R.id.bal_coefficient);
        TextView distance = (TextView) findViewById(R.id.distance);
        TextView wind_speed = (TextView) findViewById(R.id.wind_speed);
        TextView wind_degree = (TextView) findViewById(R.id.wind_degree);
        ImageView flag = (ImageView) findViewById(R.id.imageView4);

        EditText distance_res = (EditText) findViewById(R.id.edit_distance);
        EditText wind_speed_res = (EditText) findViewById(R.id.edit_wind_speed);
        EditText wind_degree_res = (EditText) findViewById(R.id.edit_wind_degree);
        EditText target_height_res = (EditText) findViewById(R.id.edit_target_height);
        EditText bullet_speed_res = (EditText) findViewById(R.id.edit_bullet_speed);
        EditText bc_res = (EditText) findViewById(R.id.edit_bc);



        Button result = (Button) findViewById(R.id.calculate);
        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        ImageButton minimap = (ImageButton) findViewById(R.id.minimap);
        ImageButton language = (ImageButton) findViewById(R.id.language);

    }
    public void calculate (View v)
    {
        TextView res_vertical = (TextView) findViewById(R.id.vertical_res);
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
            Shot.setBc(Double.parseDouble(bc.getText().toString()));
            Shot.setWind_degree(Double.parseDouble(wind_degree.getText().toString()));
            Shot.setWind_speed(Double.parseDouble(wind_speed.getText().toString()));
            Shot.setTarget_high(Double.parseDouble(target_height.getText().toString()));

            Shot.Bullet.set_weight(0.010866);
            Shot.Bullet.set_square(0.000048);
            Shot.Bullet.set_speed(Double.parseDouble(bullet_speed.getText().toString()));


            res_vertical.setText(Double.toString((Shot.correctionV())));
//        Double.toString(s.calculate_vertical_correction())
        }catch (Exception ex){
            res_vertical.setText("Введіть всі дані");
        }
    }

    public void move_to_map (View v) {
        String uri = "http://maps.google.com/maps?daddr=Lviv, Lviv Oblast, Ukraine";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }









}