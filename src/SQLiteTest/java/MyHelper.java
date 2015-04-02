package com.example.sample.sqlitetest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masakisakamoto on 2015/03/31.
 */
public class MyHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "sample_database";
    static int DATABASE_VERSION = 1 ;

    // コンストラクタ
    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table test( name text, age text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
