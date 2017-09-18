package com.experitest.qasimsobeh.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Logs extends AppCompatActivity
{
    ListView listView_Logs;
    LogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        listView_Logs = (ListView) findViewById(R.id.listview_Logs);

        adapter = new LogAdapter(getApplicationContext(),R.layout.row_layout);

        listView_Logs.setAdapter(adapter);
    }
}
