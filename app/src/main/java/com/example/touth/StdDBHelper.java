package com.example.touth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class StdDBHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Class.db";
    private static final int DATABASE_VERSION = 1;
    public StdDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatSQL = "CREATE TABLE students (" +
                "id INTEGER primary key AUTOINCREMENT, " +
                "X VARCHAR(250)," +
                "Y VARCHAR(250))";
        db.execSQL(creatSQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}