package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }
    public void addBullet(View v) {
        dbHandler = new DBHandler(Settings.this);
        EditText BulletCalEdt = findViewById(R.id.Edit_caliber);
        EditText BulletWeightEdt = findViewById(R.id.Edit_weight);
        EditText BulletG1Edt = findViewById(R.id.Edit_g1);
        EditText BulletG7Edt = findViewById(R.id.Edit_g7);
        EditText BulletStart_speedEdt = findViewById(R.id.Edit_start_speed);
        String BulletCal = BulletCalEdt.getText().toString();
        Float BulletWeight = Float.valueOf(BulletWeightEdt.getText().toString());
        Float BulletG1 = Float.valueOf(BulletG1Edt.getText().toString());
        Float BulletG7 = Float.valueOf(BulletG7Edt.getText().toString());
        Float BulletStart_speed = Float.valueOf(BulletStart_speedEdt.getText().toString());

        if (BulletCal.isEmpty() &&BulletWeight==null && BulletG1==null && BulletG7==null && BulletStart_speed==null ) {
            Toast.makeText(Settings.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }
        dbHandler.addNewBullet(BulletCal, BulletWeight, BulletG1, BulletG7, BulletStart_speed);
        Toast.makeText(Settings.this, "Bullet has been added.", Toast.LENGTH_SHORT).show();
        BulletCalEdt.setText("");
        BulletG7Edt.setText("");
        BulletG1Edt.setText("");
        BulletWeightEdt.setText("");
        BulletWeightEdt.setText("");
    }

}