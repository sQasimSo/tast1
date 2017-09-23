package com.experitest.qasimsobeh.task1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * Created by karou on 23-Sep-17.
 */

public class BackgroundTask extends AsyncTask<String, Void, String>
{
    Context ctx;

    public BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params)
    {
        String method = params[0];

        if(method.equals("add_info"))
        {
            DatabaseHelper dh = new DatabaseHelper(ctx);
            SQLiteDatabase db = dh.getWritableDatabase();
            db.execSQL(DBParametersFeed.SQL_CREATE_STATEMENT);

            dh.insertData(db,params[1],params[2],Integer.parseInt(params[3]));

            return "One Row Inserted!";
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
    }
}
