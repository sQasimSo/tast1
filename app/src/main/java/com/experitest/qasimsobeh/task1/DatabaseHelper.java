package com.experitest.qasimsobeh.task1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karou on 13-Sep-17.
 */
final class DBParametersFeed
{
    public static final String DATABASE_NAME = "Players.db";
    public static final String TABLE_NAME = "ScoresTable";
    public static final String col1 = "userName";
    public static final String col2 = "recordTime";
    public static final String col3 = "score";
    public static final int DB_Version = 1;

    public static final String SQL_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + col1 + " TEXT, "
            + col2 + " TEXT, "
            + col3 + " INTEGER)";

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

    public ArrayList<Record> getAllRecords()
    {
        Record record;
        ArrayList<Record> records = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DBParametersFeed.TABLE_NAME, null);

        if(cursor.moveToFirst())
        {
            do
            {
                record = new Record();
                record.setTime(cursor.getString(1));
                record.setScore(Integer.parseInt(cursor.getString(2)));
                record.setUserName(cursor.getString(0));

                records.add(record);
            }
            while (cursor.moveToNext());
        }

        return records;
    }

    public boolean insertData(String userName, String scoreTime, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBParametersFeed.SQL_CREATE_STATEMENT);

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBParametersFeed.col1,userName);
        contentValues.put(DBParametersFeed.col2,scoreTime);
        contentValues.put(DBParametersFeed.col3,score);

        long result = db.insert(DBParametersFeed.TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}
