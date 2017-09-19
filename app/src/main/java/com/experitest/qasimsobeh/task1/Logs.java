package com.experitest.qasimsobeh.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Logs extends AppCompatActivity
{
    ListView listView_Logs;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        ArrayList<Log> log = Globals.getApplicationLog();

        listView_Logs = (ListView) findViewById(R.id.listview_Logs);

        adapter = new LogAdapter(log, getApplicationContext());
        listView_Logs.setAdapter(adapter);
    }
}
