package com.example.sniper;
import android.content.ContentValues;
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

class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "sniperdb";

    private static final int DB_VERSION = 1;

    private static final  String TABLE_NAME = "bullet";

    private static final String ID_COL = "id";

    private static final  String CALIBER_COL = "caliber";

    private static final  String WEIGHT_COL = "weight";

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
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CALIBER_COL + " TEXT, "
                + WEIGHT_COL + " REAL,"
                + G1_COL + " REAL,"
                + G7_COL + " REAL,"
                + START_SPEED_COL + " REAL)"
                ;

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewBullet(String bulletCal, Float bulletWeight, Float bulletG1, Float bulletG7, Float bulletStart_speed) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
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
}

    /*private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "sniper.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "bullet"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_ID = "bullet_id";
    static final String COLUMN_CALIBER = "caliber";
    static final String COLUMN_WEIGHT = "weight";
    static final String COLUMN_G1 = "G1";
    static final String COLUMN_G7 = "G7";
    static final String COLUMN_SQUARE = "square";
    static final String COLUMN_START_SPEED = "start_speed";
    private Context myContext;

    DBHandler(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){

        File file = new File(DB_PATH);
        if (!file.exists()) {
            //получаем локальную бд как поток
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                // Открываем пустую бд
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
     */
