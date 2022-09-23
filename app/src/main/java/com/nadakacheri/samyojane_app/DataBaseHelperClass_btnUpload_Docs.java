package com.nadakacheri.samyojane_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelperClass_btnUpload_Docs extends SQLiteOpenHelper {

    static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Upload_Docs.db";
    public static String TABLE_NAME = "UploadDocs";
    public static String GSCNO = "GSCNO";
    public static String Id = "Id";
    public static String DocumentName = "DocumentName";
    public static String Document = "Document";
    public static String DocumentID = "DocumentId";


    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + Id + " INTEGER PRIMARY KEY NOT NULL,"+ GSCNO +" TEXT,"
             + DocumentName + " TEXT," + DocumentID + " int," +  Document + " TEXT)";


    public DataBaseHelperClass_btnUpload_Docs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Note", "DataBase Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i("Note",TABLE_NAME+" Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.i("Note", "Table Upgraded");
        db.execSQL(CREATE_TABLE);
    }


    public boolean insertData(String gscno, String name, String document,int documentID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GSCNO,gscno);
        contentValues.put(DocumentName, name);
        contentValues.put(Document, document);
        contentValues.put(DocumentID,documentID);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }
}