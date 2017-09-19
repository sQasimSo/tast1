package com.experitest.qasimsobeh.task1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        //for testing
        /*for(int i = 0; i < 10; i++)
        {
            databaseHelper.insertData(Globals.getUserName() + i, DateFormat.getDateTimeInstance().format(new Date()), i*5);
        }*/


        ArrayList<Record> records = databaseHelper.getAllRecords();
        recordAdapter = new RecordAdapter(records,getApplicationContext());
        listView.setAdapter(recordAdapter);
    }
}
