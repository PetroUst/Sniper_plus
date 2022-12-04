/*package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserActivity extends AppCompatActivity {

    EditText caliberBox;
    EditText weightBox;
    EditText G1Box;
    EditText G7Box;
    EditText squareBox;
    EditText start_speedBox;
    Button delButton;
    Button saveButton;

    DBHandler sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long bulletId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        caliberBox = findViewById(R.id.caliber);
        weightBox = findViewById(R.id.weight);
        G1Box = findViewById(R.id.g1);
        G7Box = findViewById(R.id.g7);
        squareBox = findViewById(R.id.square);
        start_speedBox = findViewById(R.id.start_speed);
        delButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);

        sqlHelper = new DBHandler(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bulletId = extras.getLong("id");
        }
        // если 0, то добавление
        if (bulletId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("SELECT * FROM bullet WHERE " +
                    DBHandler.COLUMN_ID + "=?", new String[]{String.valueOf(bulletId)});
            userCursor.moveToFirst();
            caliberBox.setText(userCursor.getString(1));
            weightBox.setText(String.valueOf(userCursor.getInt(2)));
            G1Box.setText(String.valueOf(userCursor.getFloat(3)));
            G7Box.setText(String.valueOf(userCursor.getFloat(4)));
            squareBox.setText(String.valueOf(userCursor.getFloat(5)));
            start_speedBox.setText(String.valueOf(userCursor.getFloat(6)));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }
    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DBHandler.COLUMN_CALIBER, caliberBox.getText().toString());
        cv.put(DBHandler.COLUMN_WEIGHT, Float.parseFloat(weightBox.getText().toString()));
        cv.put(DBHandler.COLUMN_G1, Float.parseFloat(G1Box.getText().toString()));
        cv.put(DBHandler.COLUMN_G7, Float.parseFloat(G7Box.getText().toString()));
        cv.put(DBHandler.COLUMN_SQUARE, Float.parseFloat(squareBox.getText().toString()));
        cv.put(DBHandler.COLUMN_START_SPEED, Float.parseFloat(start_speedBox.getText().toString()));

        if (bulletId > 0) {
            db.update(DBHandler.TABLE, cv, DBHandler.COLUMN_ID + "=" + bulletId, null);
        } else {
            db.insert(DBHandler.TABLE, null, cv);
        }
        goHome();
    }
    public void delete(View view){
        db.delete(DBHandler.TABLE, "_id = ?", new String[]{String.valueOf(bulletId)});
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}*/