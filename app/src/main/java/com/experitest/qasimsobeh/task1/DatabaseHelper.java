package com.experitest.qasimsobeh.task1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by karou on 13-Sep-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Players.db";
    public static final String TABLE_NAME = "PlayersTable";
    public static final String col1 = "email";
    public static final String col2 = "firstName";
    public static final String col3 = "lastName";
    public static final String col4 = "score";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + col1 + " TEXT UNIQUE," + col2 + " TEXT," + col3 + " TEXT," + col4 + " integer)");
        sqLiteDatabase.execSQL("CREATE TABLE PlayersTable (email TEXT UNIQUE, firstName TEXT, lastName TEXT,score integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String fName, String lName, String email, int score, TextView t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen())
            t.setText("DB is Open");
        else
            t.setText("DB is close");

        ContentValues contentValues = new ContentValues();

        contentValues.put(col1,email);
        contentValues.put(col2,fName);
        contentValues.put(col3,lName);
        contentValues.put(col4,score);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}
