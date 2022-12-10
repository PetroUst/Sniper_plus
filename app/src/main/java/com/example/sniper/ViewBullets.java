package com.example.sniper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private ArrayList<BulletModel> BulletModalArrayList;
    private DBHandler dbHandler;
    private BulletRVAdapter bulletRVAdapter;
    private RecyclerView bulletsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bullets);

        // initializing our all variables.
        BulletModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewBullets.this);

        // getting our course array
        // list from db handler class.
        BulletModalArrayList = dbHandler.readBullets();

        // on below line passing our array lost to our adapter class.
        bulletRVAdapter = new BulletRVAdapter(BulletModalArrayList, ViewBullets.this);
        bulletsRV = findViewById(R.id.idRVBullets);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewBullets.this, RecyclerView.VERTICAL, false);
        bulletsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        bulletsRV.setAdapter(bulletRVAdapter);
    }
}
