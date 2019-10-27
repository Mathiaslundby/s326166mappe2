package com.example.s326166mappe2;

import android.provider.BaseColumns;

public final class DbContract {

    private DbContract() {}

    public static class Events implements BaseColumns {
        public static final String TABLE_NAME = "events_table";
        public static final String COLUMN_REST = "restaurant";
        public static final String COLUMN_TIME = "time";

        public final static String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_REST + " INTEGER, " +
                        COLUMN_TIME + " TEXT);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SELECT_ALL =
                "SELECT * FROM " + TABLE_NAME;

        public static String SELECT_WITH_REST_ID(long restId) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE " +
                    COLUMN_REST + " = " + restId;
        }
    }



    public static class Restaurants implements BaseColumns {
        public static final String TABLE_NAME = "rests_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PH_NO = "phone_number";
        public static final String COLUMN_TYPE = "type";

        public final static String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PH_NO + " TEXT, " +
                COLUMN_TYPE + " TEXT);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SELECT_ALL =
                "SELECT * FROM " + TABLE_NAME;

        public static String SELECT_ONE(long id) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE _id = " + id;
        }

        public static String SELECT_WHERE(String name, String address) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + name +
                    "' AND " + COLUMN_ADDRESS + " = '" + address + "';";
        }
    }



    public static class EventFriend implements BaseColumns {
        public static final String TABLE_NAME = "event_friend";
        public static final String COLUMN_EVENT = "event";
        public static final String COLUMN_FRIEND = "friend";

        public final static String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_EVENT + " INTEGER, " +
                        COLUMN_FRIEND + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static String SELECT_WHERE(long id) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EVENT + " = " + id;
        }
    }



    public static class Friends implements BaseColumns {
        public static final String TABLE_NAME = "friends_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PH_NO = "phone_number";

        public final static String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PH_NO + " TEXT);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SELECT_ALL =
                "SELECT * FROM " + TABLE_NAME;

        public static String SELECT_ONE(long id) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE _id = " + id;
        }

        public static String SELECT_WHERE(String name, String number) {
            return "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + name +
                    "' AND " + COLUMN_PH_NO + " = '" + number + "';";
        }
    }
}
