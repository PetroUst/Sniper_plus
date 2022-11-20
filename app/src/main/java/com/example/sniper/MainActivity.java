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

        ViewGroup.LayoutParams flagpr = (ViewGroup.MarginLayoutParams) flag.getLayoutParams();
        flagpr.height = calcHeight(50);
        flagpr.width = calcWidth(414);


        ViewGroup.LayoutParams vertical_params = (ViewGroup.MarginLayoutParams) vertical.getLayoutParams();
        vertical_params.height = calcHeight(46);
        vertical_params.width = calcWidth(169);

        ViewGroup.LayoutParams horizontal_params = (ViewGroup.MarginLayoutParams) horizontal.getLayoutParams();
        horizontal_params.height = calcHeight(46);
        horizontal_params.width = calcWidth(176);

        ViewGroup.LayoutParams vertical_respr = (ViewGroup.MarginLayoutParams) vertical_res.getLayoutParams();
        vertical_respr.height = calcHeight(46);
        vertical_respr.width = calcWidth(216);

        ViewGroup.LayoutParams horizontal_respr = (ViewGroup.MarginLayoutParams) horizontal_res.getLayoutParams();
        horizontal_respr.height = calcHeight(46);
        horizontal_respr.width = calcWidth(216);

        ViewGroup.LayoutParams target_heightpr = (ViewGroup.MarginLayoutParams) target_height.getLayoutParams();
        target_heightpr.height = calcHeight(28);
        target_heightpr.width = calcWidth(234);

        ViewGroup.LayoutParams bullet_speedpr = (ViewGroup.MarginLayoutParams) bullet_speed.getLayoutParams();
        bullet_speedpr.height = calcHeight(51);
        bullet_speedpr.width = calcWidth(233);

        ViewGroup.LayoutParams bal_coefficientpr = (ViewGroup.MarginLayoutParams) bal_coefficient.getLayoutParams();
        bal_coefficientpr.height = calcHeight(51);
        bal_coefficientpr.width = calcWidth(233);

        ViewGroup.LayoutParams distance_params = (ViewGroup.MarginLayoutParams) distance.getLayoutParams();
        distance_params.height = calcHeight(28);
        distance_params.width = calcWidth(234);

        ViewGroup.LayoutParams wind_speedpr = (ViewGroup.MarginLayoutParams) wind_speed.getLayoutParams();
        wind_speedpr.height = calcHeight(30);
        wind_speedpr.width = calcWidth(231);

        ViewGroup.LayoutParams wind_degreepr = (ViewGroup.MarginLayoutParams) wind_degree.getLayoutParams();
        wind_degreepr.height = calcHeight(51);
        wind_degreepr.width = calcWidth(234);

        EditText distance_res = (EditText) findViewById(R.id.edit_distance);
        EditText wind_speed_res = (EditText) findViewById(R.id.edit_wind_speed);
        EditText wind_degree_res = (EditText) findViewById(R.id.edit_wind_degree);
        EditText target_height_res = (EditText) findViewById(R.id.edit_target_height);
        EditText bullet_speed_res = (EditText) findViewById(R.id.edit_bullet_speed);
        EditText bc_res = (EditText) findViewById(R.id.edit_bc);

        ViewGroup.LayoutParams distance_respr = (ViewGroup.MarginLayoutParams) distance_res.getLayoutParams();
        distance_respr.height = calcHeight(48);
        distance_respr.width = calcWidth(129);

        ViewGroup.LayoutParams wind_speed_respr = (ViewGroup.MarginLayoutParams) wind_speed_res.getLayoutParams();
        wind_speed_respr.height = calcHeight(48);
        wind_speed_respr.width = calcWidth(129);

        ViewGroup.LayoutParams wind_degree_respr = (ViewGroup.MarginLayoutParams) wind_degree_res.getLayoutParams();
        wind_degree_respr.height = calcHeight(48);
        wind_degree_respr.width = calcWidth(129);

        ViewGroup.LayoutParams target_height_respr = (ViewGroup.MarginLayoutParams) target_height_res.getLayoutParams();
        target_height_respr.height = calcHeight(48);
        target_height_respr.width = calcWidth(129);

        ViewGroup.LayoutParams bullet_speed_respr = (ViewGroup.MarginLayoutParams) bullet_speed_res.getLayoutParams();
        bullet_speed_respr.height = calcHeight(48);
        bullet_speed_respr.width = calcWidth(129);

        ViewGroup.LayoutParams bc_respr = (ViewGroup.MarginLayoutParams) bc_res.getLayoutParams();
        bc_respr.height = calcHeight(48);
        bc_respr.width = calcWidth(129);


        Button result = (Button) findViewById(R.id.calculate);
        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        ImageButton minimap = (ImageButton) findViewById(R.id.minimap);
        ImageButton language = (ImageButton) findViewById(R.id.language);

        ViewGroup.LayoutParams resultpr = (ViewGroup.MarginLayoutParams) result.getLayoutParams();
        resultpr.height = calcHeight(55);
        resultpr.width = calcWidth(123);

        ViewGroup.LayoutParams settingspr = (ViewGroup.MarginLayoutParams) settings.getLayoutParams();
        settingspr.height = calcHeight(55);
        settingspr.width = calcWidth(55);

        ViewGroup.LayoutParams minimappr = (ViewGroup.MarginLayoutParams) minimap.getLayoutParams();
        minimappr.height = calcHeight(55);
        minimappr.width = calcWidth(55);

        ViewGroup.LayoutParams languagepr = (ViewGroup.MarginLayoutParams) language.getLayoutParams();
        languagepr.height = calcHeight(55);
        languagepr.width = calcWidth(55);
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