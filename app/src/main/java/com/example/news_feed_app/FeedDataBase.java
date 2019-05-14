package com.example.news_feed_app;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class FeedDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NewsFeed";
    public static final int VERSION_NUM = 1;
    public static final String TABLE_BOOKMARKS = "Bookmarks";
    public static final String TABLE_SEARCH_TAGS = "Search_Tags";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_LINK = "LINK";
    public static final String COL_TAG = "TAG";

    public FeedDatabase(Activity ctx) {

        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db) {
        //Make sure you put spaces between SQL statements and Java strings:
        db.execSQL("CREATE TABLE " + TABLE_BOOKMARKS + "( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT, "
                + COL_LINK + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_SEARCH_TAGS + "( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TAG + " TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database upgrade", "Old version:" + oldVersion + " newVersion:" + newVersion);

        //Delete the old tables:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH_TAGS);

        //Create a new table:
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database downgrade", "Old version:" + oldVersion + " newVersion:" + newVersion);

        //Delete the old tables:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH_TAGS);

        //Create a new table:
        onCreate(db);
    }
}