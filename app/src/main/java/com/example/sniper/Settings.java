package com.example.sniper;

import androidx.activity.OnBackPressedDispatcher;
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
        Float BulletG7;
        try {
            String BulletName = BulletNameEdt.getText().toString();
            String BulletCal = BulletCalEdt.getText().toString();
            Float BulletWeight = Float.valueOf(BulletWeightEdt.getText().toString());
            Float BulletG1 = Float.valueOf(BulletG1Edt.getText().toString());
            try {
                BulletG7 = Float.valueOf(BulletG7Edt.getText().toString());
            } catch (Exception e){
                BulletG7 = 0.0f;
            }
            Float BulletStart_speed = Float.valueOf(BulletStart_speedEdt.getText().toString());

            if (BulletName.isEmpty() || BulletCal.isEmpty() || BulletWeight == null || BulletG1 == null || BulletStart_speed == null) {
                Toast.makeText(Settings.this, getString(R.string.enter_data), Toast.LENGTH_SHORT).show();
                return;
            }


            Float sq = dbHandler.useBullet(BulletCal);

            if (sq == 0) {
                Toast.makeText(Settings.this, getString(R.string.no_caliber), Toast.LENGTH_SHORT).show();
                return;
            }

            String check = dbHandler.checkBulletName(BulletName);
            if (!check.isEmpty()) {
                Toast.makeText(Settings.this, getString(R.string.bullet_exist), Toast.LENGTH_SHORT).show();
                return;
            }

            dbHandler.addNewBullet(BulletName, BulletCal, BulletWeight, BulletG1, BulletG7, BulletStart_speed);
            Toast.makeText(Settings.this, getString(R.string.bullet_added), Toast.LENGTH_SHORT).show();
            BulletNameEdt.setText("");
            BulletCalEdt.setText("");
            BulletG7Edt.setText("");
            BulletG1Edt.setText("");
            BulletWeightEdt.setText("");
            BulletStart_speedEdt.setText("");

        }
        catch (Exception e) {
            Toast.makeText(Settings.this, getString(R.string.check_inputs), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent allBullets = new Intent(Settings.this, MainActivity.class);
        startActivity(allBullets);
    }
    public void show_bullets(View v) {
        // opening a new activity via a intent.
        Intent allBullets = new Intent(Settings.this, ViewBullets.class);
        startActivity(allBullets);
    }

}