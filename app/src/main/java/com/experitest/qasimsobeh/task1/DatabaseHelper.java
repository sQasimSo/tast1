package com.experitest.qasimsobeh.task1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by karou on 13-Sep-17.
 */
final class DBParametersFeed
{
    public static final String DATABASE_NAME = "Players.db";
    public static final String TABLE_NAME = "PlayersTable";
    public static final String col1 = "email";
    public static final String col2 = "firstName";
    public static final String col3 = "lastName";
    public static final String col4 = "score";
    public static final int DB_Version = 1;

    public static final String SQL_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + col1 + " TEXT UNIQUE, "
            + col2 + " TEXT, "
            + col3 + " TEXT, "
            + col4 + " INTEGER)";

    public static final String SQL_DELETE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;
}

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context)
    {
        super(context, DBParametersFeed.DATABASE_NAME, null, DBParametersFeed.DB_Version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DBParametersFeed.SQL_CREATE_STATEMENT);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL(DBParametersFeed.SQL_DELETE_STATEMENT);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public boolean insertData(String fName, String lName, String email, int score, TextView t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBParametersFeed.SQL_CREATE_STATEMENT);

        if(db.isOpen())
            t.setText("DB is Open");
        else
            t.setText("DB is close");

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBParametersFeed.col1,email);
        contentValues.put(DBParametersFeed.col2,fName);
        contentValues.put(DBParametersFeed.col3,lName);
        contentValues.put(DBParametersFeed.col4,score);

        long result = db.insert(DBParametersFeed.TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}
