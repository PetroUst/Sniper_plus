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
    private EditText bulletNameEdt, bulletCalEdt, bulletWeightEdt, bulletG1Edt, bulletG7Edt, bulletStartSpeedEdt;
    private Button updateBulletBtn, deleteBulletBtn, useBulletBtn;
    private DBHandler dbHandler;
    String bulletName, bulletCal, bulletWeight, bulletG1, bulletG7, bulletStartSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bullet);

        // initializing all our variables.
        bulletNameEdt = findViewById(R.id.idEdtBulletName);
        bulletCalEdt = findViewById(R.id.idEdtBulletCaliber);
        bulletWeightEdt = findViewById(R.id.idEdtBulletWeight);
        bulletG1Edt = findViewById(R.id.idEdtBulletG1);
        bulletG7Edt = findViewById(R.id.idEdtBulletG7);
        bulletStartSpeedEdt = findViewById(R.id.idEdtBulletStartSpeed);
        updateBulletBtn = findViewById(R.id.idBtnUpdateBullet);
        deleteBulletBtn = findViewById(R.id.idBtnDelete);
        useBulletBtn = findViewById(R.id.idBtnUse);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateBulletActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        bulletName = getIntent().getStringExtra("name");
        bulletCal = getIntent().getStringExtra("caliber");
        bulletWeight = String.valueOf(getIntent().getFloatExtra("weight", 0.0f));
        bulletG1 = String.valueOf(getIntent().getFloatExtra("G1", 0.0f));
        bulletG7 = String.valueOf(getIntent().getFloatExtra("G7", 0.0f));
        bulletStartSpeed = String.valueOf(getIntent().getFloatExtra("start speed", 0.0f));


        // setting data to edit text
        // of our update activity.
        bulletNameEdt.setText(bulletName);
        bulletCalEdt.setText(bulletCal);
        bulletWeightEdt.setText(bulletWeight);
        bulletG1Edt.setText(bulletG1);
        bulletG7Edt.setText(bulletG7);
        bulletStartSpeedEdt.setText(bulletStartSpeed);

        // adding on click listener to our update course button.
        updateBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Float sq = dbHandler.useBullet(bulletCal);

                if (sq == 0){
                    Toast.makeText(UpdateBulletActivity.this, "There is no such caliber", Toast.LENGTH_SHORT).show();
                return;}

                String check = dbHandler.checkBulletName(bulletName);
                if ((!check.isEmpty() && !bulletName.equals(bulletNameEdt.getText().toString())) || check.isEmpty()){
                    Toast.makeText(UpdateBulletActivity.this, "A bullet with that name already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateBullet(bulletName, bulletNameEdt.getText().toString(), bulletCalEdt.getText().toString(), Float.valueOf(bulletWeightEdt.getText().toString()), Float.valueOf(bulletG1Edt.getText().toString()), Float.valueOf(bulletG7Edt.getText().toString()), Float.valueOf(bulletStartSpeedEdt.getText().toString()));

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateBulletActivity.this, "Bullet updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateBulletActivity.this, Settings.class);
                startActivity(i);

            }
        });


        // adding on click listener for delete button to delete our course.
        deleteBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteBullet(bulletName);
                Toast.makeText(UpdateBulletActivity.this, "Deleted bullet", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateBulletActivity.this, Settings.class);
                startActivity(i);
            }
        });


        useBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Float sq = dbHandler.useBullet(bulletCal);
                if (sq == 0){
                    Toast.makeText(UpdateBulletActivity.this, "There is no such caliber", Toast.LENGTH_SHORT).show();
                    return;}
                String sqshow = "square = " + sq;
            Toast.makeText(UpdateBulletActivity.this, sqshow, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
