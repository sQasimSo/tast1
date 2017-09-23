package com.experitest.qasimsobeh.task1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Records extends AppCompatActivity
{
    ListView listView;
    RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        listView = (ListView) findViewById(R.id.listView_Records);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        BackgroundTask backgroundTask = new BackgroundTask(this);

        //for testing
        for (int i = 0; i < 10; i++)
        {
            backgroundTask.execute("add_info","user number " + i, DateFormat.getDateTimeInstance().format(new Date()),"" + i*7);
            finish();
        }


        ArrayList<Record> records = databaseHelper.getAllRecords();
        /*recordAdapter = new RecordAdapter(records, this);
        listView.setAdapter(recordAdapter);*/
    }
}
