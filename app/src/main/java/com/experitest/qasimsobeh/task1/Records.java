package com.experitest.qasimsobeh.task1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        recordAdapter = new RecordAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(recordAdapter);


    }
}
