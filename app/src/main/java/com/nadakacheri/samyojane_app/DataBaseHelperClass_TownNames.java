package com.nadakacheri.samyojane_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelperClass_TownNames extends SQLiteOpenHelper {

    static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Town_Names.db";
    public static String TABLE_NAME = "Town_Names";
    public static String ID = "ID";
    public static String CODE = "CODE";
    public static String ENGLISHNAME = "ENGLISHNAME";
    public static String KANNADANAME = "KANNADANAME";

    static String CREATE_TABLE ="CREATE TABLE " + TABLE_NAME + "("+ ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CODE+" BIGINT(10)," +ENGLISHNAME+" TEXT,"+KANNADANAME+" TEXT)";

    public DataBaseHelperClass_TownNames(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i("Note",TABLE_NAME+" Table Created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        Log.i("Note","Table Upgraded");
        //db.execSQL(CREATE_TABLE);
    }
}
