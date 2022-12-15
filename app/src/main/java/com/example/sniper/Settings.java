package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private DBHandler dbHandler;
    private EditText BulletNameEdt, BulletCalEdt, BulletWeightEdt, BulletG1Edt, BulletG7Edt, BulletStart_speedEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dbHandler = new DBHandler(Settings.this);
        BulletNameEdt = findViewById(R.id.Edit_name);
        BulletCalEdt = findViewById(R.id.Edit_caliber);
        BulletWeightEdt = findViewById(R.id.Edit_weight);
        BulletG1Edt = findViewById(R.id.Edit_g1);
        BulletG7Edt = findViewById(R.id.Edit_g7);
        BulletStart_speedEdt = findViewById(R.id.Edit_start_speed);
    }
    public void addBullet(View v) {
        String BulletName = BulletNameEdt.getText().toString();
        String BulletCal = BulletCalEdt.getText().toString();
        Float BulletWeight = Float.valueOf(BulletWeightEdt.getText().toString());
        Float BulletG1 = Float.valueOf(BulletG1Edt.getText().toString());
        Float BulletG7 = Float.valueOf(BulletG7Edt.getText().toString());
        Float BulletStart_speed = Float.valueOf(BulletStart_speedEdt.getText().toString());

        if (BulletCal.isEmpty() &&BulletWeight==null && BulletG1==null && BulletG7==null && BulletStart_speed==null ) {
            Toast.makeText(Settings.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        Float sq = dbHandler.useBullet(BulletCal);

        if (sq == 0){
            Toast.makeText(Settings.this, "There is no such caliber", Toast.LENGTH_SHORT).show();
            return;
        }

        String check = dbHandler.checkBulletName(BulletName);
        if (!check.isEmpty()){
            Toast.makeText(Settings.this, "A bullet with that name already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHandler.addNewBullet(BulletName, BulletCal, BulletWeight, BulletG1, BulletG7, BulletStart_speed);
        Toast.makeText(Settings.this, "Bullet has been added.", Toast.LENGTH_SHORT).show();
        BulletNameEdt.setText("");
        BulletCalEdt.setText("");
        BulletG7Edt.setText("");
        BulletG1Edt.setText("");
        BulletWeightEdt.setText("");
        BulletWeightEdt.setText("");
    }


    public void show_bullets(View v) {
        // opening a new activity via a intent.
        Intent allBullets = new Intent(Settings.this, ViewBullets.class);
        startActivity(allBullets);
    }

}