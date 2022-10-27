package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calculate (View v)
    {
        Shot s = new Shot();
        TextView res_vertical = (TextView)findViewById(R.id.vertical_res);
        double a =10.2;
        res_vertical.setText(Double.toString((a)));
//        Double.toString(s.calculate_vertical_correction())
    }

}