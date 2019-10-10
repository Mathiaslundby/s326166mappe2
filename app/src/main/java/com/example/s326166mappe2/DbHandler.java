package com.example.s326166mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

public class DbHandler extends SQLiteOpenHelper {

    static String TABLE_RESTAURANTS = "Restaurants";
    static String KEY_ID = "_ID";
    static String KEY_NAME = "Name";
    static String KEY_ADDRESS = "Address";
    static String KEY_PH_NO = "PhoneNumber";
    static String KEY_TYPE = "Type";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Restaurant_visits";

    public DbHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_RESTAURANTS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT," +
                KEY_PH_NO + " TEXT," + KEY_TYPE + " TEXT" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        onCreate(sqLiteDatabase);
    }


    public void addRestaurant(Restaurant r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, r.getName());
        values.put(KEY_ADDRESS, r.getAddress());
        values.put(KEY_PH_NO, r.getTlf());
        values.put(KEY_TYPE, r.getType());
        db.insert(TABLE_RESTAURANTS, null, values);
        db.close();
    }
}
