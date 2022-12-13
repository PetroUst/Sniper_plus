package com.example.sniper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "sniperdb";

    private static final int DB_VERSION = 1;

    private static final  String TABLE_NAME = "bullet";

    private static final  String TABLE_NAME2 = "calibers";

    private static final String DIAMETER_COL = "diameter";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String CALIBER_COL = "caliber";

    private static final String WEIGHT_COL = "weight";

    private static final String G1_COL = "G1";

    private static final String G7_COL = "G7";

    private static final String START_SPEED_COL = "start_speed";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query_bullet = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + CALIBER_COL + " TEXT, "
                + WEIGHT_COL + " REAL,"
                + G1_COL + " REAL,"
                + G7_COL + " REAL,"
                + START_SPEED_COL + " REAL)"
                ;

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query_bullet);

        String query_cal = "CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CALIBER_COL + " TEXT, "
                + DIAMETER_COL + " REAL)"
                ;

        db.execSQL(query_cal);

        String create_query = "INSERT INTO " + TABLE_NAME + " (" + NAME_COL + ", " + CALIBER_COL + ", " + WEIGHT_COL + ", "
                + G1_COL + ", " + G7_COL + ", " + START_SPEED_COL + ") VALUES ( \".308 Spitzer test\", \".308\", 147,  0.360, 0.180, 850)";
        db.execSQL(create_query);

        create_query = "INSERT INTO " + TABLE_NAME + " (" + NAME_COL + ", " + CALIBER_COL + ", " + WEIGHT_COL + ", "
                + G1_COL + ", " + G7_COL + ", " + START_SPEED_COL + ") VALUES ( \".308 Spitzer test2\", \".308\", 147,  0.360, 0.180, 850)";
        db.execSQL(create_query);

        create_query = "INSERT INTO " + TABLE_NAME + " (" + NAME_COL + ", " + CALIBER_COL + ", " + WEIGHT_COL + ", "
                + G1_COL + ", " + G7_COL + ", " + START_SPEED_COL + ") VALUES ( \".408 Spitzer\", \".408\", 147,  0.360, 0.180, 850)";
        db.execSQL(create_query);

        create_query = "INSERT INTO " + TABLE_NAME2 + " (" + CALIBER_COL + ", " + DIAMETER_COL + ") VALUES (\".308\", 120)";
        db.execSQL(create_query);

        create_query = "INSERT INTO " + TABLE_NAME2 + " (" + CALIBER_COL + ", " + DIAMETER_COL + ") VALUES (\".408\", 150)";
        db.execSQL(create_query);

    }

    // this method is use to add new course to our sqlite database.
    public void addNewBullet( String bulletName, String bulletCal, Float bulletWeight, Float bulletG1, Float bulletG7, Float bulletStart_speed) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, bulletName);
        values.put(CALIBER_COL, bulletCal);
        values.put(WEIGHT_COL, bulletWeight);
        values.put(G1_COL, bulletG1);
        values.put(G7_COL, bulletG7);
        values.put(START_SPEED_COL, bulletStart_speed);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<BulletModel> readBullets() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorBullets = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<BulletModel> bulletModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorBullets.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                bulletModelArrayList.add(new BulletModel(cursorBullets.getString(1),
                        cursorBullets.getString(2),
                        cursorBullets.getFloat(3),
                        cursorBullets.getFloat(4),
                        cursorBullets.getFloat(5),
                        cursorBullets.getFloat(6)));
            } while (cursorBullets.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorBullets.close();
        return bulletModelArrayList;
    }

    // below is the method for updating our courses
    public void updateBullet(String originalBulletName, String bulletName, String bulletCal, Float bulletWeight,
                             Float bulletG1, Float bulletG7, Float bulletStartSpeed) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, bulletName);
        values.put(CALIBER_COL, bulletCal);
        values.put(WEIGHT_COL, bulletWeight);
        values.put(G1_COL, bulletG1);
        values.put(G7_COL, bulletG7);
        values.put(START_SPEED_COL, bulletStartSpeed);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalBulletName});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteBullet(String bulletName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{bulletName});
        db.close();
    }

    public float useBullet(String bulletCal) {

        // on below line we are creating
        // a variable to write our database.
        /*String findSquare = "SELECT * FROM " + TABLE_NAME2
                + " WHERE " + CALIBER_COL + " = " + bulletCal;
        Cursor cursor = db.rawQuery(findSquare, null);

        @SuppressLint("Range") float square = cursor.getFloat(cursor.getColumnIndex("square"));
        db.close();*/

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cC = db.rawQuery("SELECT " + DIAMETER_COL + " FROM " + TABLE_NAME2 +
                " WHERE " + CALIBER_COL + " = " + "\"" + bulletCal + "\"" , null);

        float ssquare = 0;
        if (cC != null && cC.moveToFirst()) {
            ssquare = cC.getFloat(cC.getColumnIndex("diameter"));
            cC.close();
        }
        return ssquare;
    }

}


