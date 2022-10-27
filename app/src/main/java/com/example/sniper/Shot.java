package com.example.sniper;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Shot extends Activity {
    static double distance;
    static double target_high;
    static double bc;//балістичний коефіцієнт
    static double wind_speed;
    static double bullet_speed;
    static double wind_degree;

      double calculate_vertical_correction(){
        EditText _distance = (EditText)this.findViewById(R.id.edit_distance);
        distance=Double.parseDouble(_distance.getText().toString());
        double res=distance;
        return res;
    }




}
