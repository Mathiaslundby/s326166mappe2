package com.example.s326166mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    static String TABLE_RESTAURANTS = "Restaurants";
    static String REST_ID = "_ID";
    static String REST_NAME = "Name";
    static String REST_ADDRESS = "Address";
    static String REST_PH_NO = "PhoneNumber";
    static String REST_TYPE = "Type";

    static String TABLE_FRIENDS = "Friends";
    static String FRIEND_ID = "_ID";
    static String FRIEND_NAME = "Name";
    static String FRIEND_PH_NO = "PhoneNumber";

    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Restaurant_visits";

    public DbHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_REST_TABLE = "CREATE TABLE " + TABLE_RESTAURANTS + "(" + REST_ID +
                " INTEGER PRIMARY KEY, " + REST_NAME + " TEXT," + REST_ADDRESS + " TEXT," +
                REST_PH_NO + " TEXT," + REST_TYPE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_REST_TABLE);

        String CREATE_FRIEND_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "(" + FRIEND_ID +
                " INTEGER PRIMARY KEY, " + FRIEND_NAME + " TEXT," + FRIEND_PH_NO  + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        onCreate(sqLiteDatabase);
    }


    public void addRestaurant(Restaurant r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(REST_NAME, r.getName());
        values.put(REST_ADDRESS, r.getAddress());
        values.put(REST_PH_NO, r.getTlf());
        values.put(REST_TYPE, r.getType());
        db.insert(TABLE_RESTAURANTS, null, values);
        db.close();
    }

    public void addFriend(Friend f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FRIEND_NAME, f.getName());
        values.put(FRIEND_PH_NO, f.getTlf());
        db.insert(TABLE_FRIENDS, null, values);
        db.close();
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> rList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Restaurant r = new Restaurant();
                r.set_ID(cursor.getLong(0));
                r.setName(cursor.getString(1));
                r.setAddress(cursor.getString(2));
                r.setTlf(cursor.getString(3));
                r.setType(cursor.getString(4));
                rList.add(r);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return rList;
    }
}
