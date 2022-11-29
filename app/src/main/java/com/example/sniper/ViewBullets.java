package com.example.sniper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sniper.BulletModel;
import com.example.sniper.BulletRVAdapter;
import com.example.sniper.DBHandler;

import java.util.ArrayList;

public class ViewBullets extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<BulletModel> courseModalArrayList;
    private DBHandler dbHandler;
    private BulletRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bullets);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewBullets.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readBullets();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new BulletRVAdapter(courseModalArrayList, ViewBullets.this);
        coursesRV = findViewById(R.id.idRVBullets);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewBullets.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
