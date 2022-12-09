package com.example.sniper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateBulletActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText bulletCalEdt, bulletWeightEdt, bulletG1Edt, bulletG7Edt, bulletStartSpeedEdt;
    private Button updateBulletBtn;
    private DBHandler dbHandler;
    String bulletCal, bulletWeight, bulletG1, bulletG7, bulletStartSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bullet);

        // initializing all our variables.
        bulletCalEdt = findViewById(R.id.idEdtBulletCaliber);
        bulletWeightEdt = findViewById(R.id.idEdtBulletWeight);
        bulletG1Edt = findViewById(R.id.idEdtBulletG1);
        bulletG7Edt = findViewById(R.id.idEdtBulletG7);
        bulletStartSpeedEdt = findViewById(R.id.idEdtBulletStartSpeed);
        updateBulletBtn = findViewById(R.id.idBtnUpdateBullet);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateBulletActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        bulletCal = getIntent().getStringExtra("caliber");
        bulletWeight = getIntent().getStringExtra("weight");
        bulletG1 = getIntent().getStringExtra("G1");
        bulletG7 = getIntent().getStringExtra("G7");
        bulletStartSpeed = getIntent().getStringExtra("start speed");


        // setting data to edit text
        // of our update activity.
        bulletCalEdt.setText(bulletCal);
        bulletWeightEdt.setText(bulletWeight);
        bulletG1Edt.setText(bulletG1);
        bulletG7Edt.setText(bulletG7);
        bulletStartSpeedEdt.setText(bulletStartSpeed);

        // adding on click listener to our update course button.
        updateBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateBullet(bulletCal, bulletCalEdt.getText().toString(), Float.valueOf(bulletWeightEdt.getText().toString()), Float.valueOf(bulletG1Edt.getText().toString()), Float.valueOf(bulletG7Edt.getText().toString()), Float.valueOf(bulletStartSpeedEdt.getText().toString()));

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateBulletActivity.this, "Bullet updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateBulletActivity.this, Settings.class);
                startActivity(i);
            }
        });
    }
}
