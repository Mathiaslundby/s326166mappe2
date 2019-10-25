package com.example.s326166mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Rest_Events";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.Restaurants.SQL_CREATE_ENTRIES);
        db.execSQL(DbContract.Friends.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Restaurants.SQL_DELETE_ENTRIES);
        db.execSQL(DbContract.Friends.SQL_DELETE_ENTRIES);
    }

    public List<Restaurant> getAllRests() {
        List<Restaurant> rList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DbContract.Restaurants.SELECT_ALL, null);

        if(cursor.moveToFirst()) {
            do {
                Restaurant r = new Restaurant();
                r.set_ID(cursor.getLong(0));
                r.setName(cursor.getString(1));
                r.setAddress(cursor.getString(2));
                r.setPh_no(cursor.getString(3));
                r.setType(cursor.getString(4));
                rList.add(r);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return rList;
    }

    public void addRestaurant(Restaurant rest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.Restaurants.COLUMN_NAME, rest.getName());
        values.put(DbContract.Restaurants.COLUMN_ADDRESS, rest.getAddress());
        values.put(DbContract.Restaurants.COLUMN_PH_NO, rest.getPh_no());
        values.put(DbContract.Restaurants.COLUMN_TYPE, rest.getType());
        db.insert(DbContract.Restaurants.TABLE_NAME, null, values);
    }

    public Restaurant getRest(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DbContract.Restaurants.SELECT_ONE(id), null);
        if(cursor.moveToFirst()) {
            Restaurant r = new Restaurant();
            r.set_ID(cursor.getLong(0));
            r.setName(cursor.getString(1));
            r.setAddress(cursor.getString(2));
            r.setPh_no(cursor.getString(3));
            r.setType(cursor.getString(4));
            cursor.close();
            return r;
        }
        return null;
    }

    public int editRestaurant(Restaurant rest) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.Restaurants.COLUMN_NAME, rest.getName());
        values.put(DbContract.Restaurants.COLUMN_ADDRESS, rest.getAddress());
        values.put(DbContract.Restaurants.COLUMN_PH_NO, rest.getPh_no());
        values.put(DbContract.Restaurants.COLUMN_TYPE, rest.getType());
        int changed = db.update(DbContract.Restaurants.TABLE_NAME, values,
                DbContract.Restaurants._ID + "=?",
                new String[]{String.valueOf(rest.get_ID())});
        db.close();
        return changed;
    }

    public void deleteRestuarant(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbContract.Restaurants.TABLE_NAME, DbContract.Restaurants._ID + " =? ",
                new String[]{Long.toString(id)});
    }

    public List<Friend> getAllFriends() {
        List<Friend> fList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DbContract.Friends.SELECT_ALL, null);

        if(cursor.moveToFirst()) {
            do {
                Friend f = new Friend();
                f.set_ID(cursor.getLong(0));
                f.setName(cursor.getString(1));
                f.setPh_no(cursor.getString(2));
                fList.add(f);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return fList;
    }

    public void addFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.Friends.COLUMN_NAME, friend.getName());
        values.put(DbContract.Friends.COLUMN_PH_NO, friend.getPh_no());
        db.insert(DbContract.Friends.TABLE_NAME, null, values);
    }

    public Friend getFriend(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DbContract.Friends.SELECT_ONE(id), null);
        if(cursor.moveToFirst()) {
            Friend f = new Friend();
            f.set_ID(cursor.getLong(0));
            f.setName(cursor.getString(1));
            f.setPh_no(cursor.getString(2));
            cursor.close();
            return f;
        }
        return null;
    }
    
    public int editFriend(Friend friend) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.Friends.COLUMN_NAME, friend.getName());
        values.put(DbContract.Friends.COLUMN_PH_NO, friend.getPh_no());
        int changed = db.update(DbContract.Friends.TABLE_NAME, values,
                DbContract.Friends._ID + "=?",
                new String[]{String.valueOf(friend.get_ID())});
        db.close();
        return changed;
    }

    public void deleteFriend(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbContract.Friends.TABLE_NAME, DbContract.Friends._ID + " =? ",
                new String[]{Long.toString(id)});
    }
}

