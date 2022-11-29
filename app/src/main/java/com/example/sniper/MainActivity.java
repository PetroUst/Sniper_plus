package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//test
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText BulletCalEdt, BulletWeightEdt, BulletG1Edt, BulletG7Edt, BulletStart_speedEdt;
    private Button addBulletBtn, readBulletBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        BulletCalEdt = findViewById(R.id.idEdtCaliber);
        BulletWeightEdt = findViewById(R.id.idEdtWeight);
        BulletG1Edt = findViewById(R.id.idEdtG1);
        BulletG7Edt = findViewById(R.id.idEdtG7);
        BulletStart_speedEdt = findViewById(R.id.idEdtStart_speed);
        addBulletBtn = findViewById(R.id.idBtnAddBullet);
        readBulletBtn = findViewById(R.id.idBtnReadBullet);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add course button.
        addBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String BulletCal = BulletCalEdt.getText().toString();
                Float BulletWeight = Float.valueOf(BulletWeightEdt.getText().toString());
                Float BulletG1 = Float.valueOf(BulletG1Edt.getText().toString());
                Float BulletG7 = Float.valueOf(BulletG7Edt.getText().toString());
                Float BulletStart_speed = Float.valueOf(BulletStart_speedEdt.getText().toString());


                // validating if the text fields are empty or not.
                if (BulletCal.isEmpty() &&BulletWeight==null && BulletG1==null && BulletG7==null && BulletStart_speed==null ) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewBullet(BulletCal, BulletWeight, BulletG1, BulletG7, BulletStart_speed);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Bullet has been added.", Toast.LENGTH_SHORT).show();
                BulletCalEdt.setText("");
                BulletG1Edt.setText("");
            }
        });

        readBulletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewBullets.class);
                startActivity(i);
            }
        });

    }
}


