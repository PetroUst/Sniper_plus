package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {


    ListView userList;
    DBHandler databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        databaseHelper = new DBHandler(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor = db.rawQuery("SELECT caliber FROM bullet", null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{DBHandler.COLUMN_CALIBER, DBHandler.COLUMN_WEIGHT};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2,}, 0);
        userList.setAdapter(userAdapter);
    }

    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
/*7
    public void calculate (View v) {
        TextView res_vertical = (TextView) findViewById(R.id.vertical_res);
        //дістаю дані з текстових полів на головному екрані ggfrtrs
        try {
            EditText distance = (EditText) findViewById(R.id.edit_distance);
            EditText wind_speed = (EditText) findViewById(R.id.edit_wind_speed);
            EditText wind_degree = (EditText) findViewById(R.id.edit_wind_degree);
            EditText target_height = (EditText) findViewById(R.id.edit_target_height);
            EditText bullet_speed = (EditText) findViewById(R.id.edit_bullet_speed);
            EditText bc = (EditText) findViewById(R.id.edit_bc);

            //конвертую строки в дабл і передаю в поля класу shot
            Shot.setDistance(Double.parseDouble(distance.getText().toString()));
            Shot.setBc(Double.parseDouble(bc.getText().toString()));
            Shot.setWind_degree(Double.parseDouble(wind_degree.getText().toString()));
            Shot.setWind_speed(Double.parseDouble(wind_speed.getText().toString()));
            Shot.setTarget_high(Double.parseDouble(target_height.getText().toString()));

            Shot.Bullet.set_weight(0.010866);
            Shot.Bullet.set_square(0.000048);
            Shot.Bullet.set_speed(Double.parseDouble(bullet_speed.getText().toString()));


            res_vertical.setText(Double.toString((Shot.correctionV())));
            // Double.toString(s.calculate_vertical_correction())
        } catch (Exception ex) {
            res_vertical.setText("Введіть всі дані");
        }
    }*/
}

