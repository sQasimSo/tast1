package com.experitest.qasimsobeh.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Logs extends AppCompatActivity
{
    ListView listView_Logs;
    ListAdapter adapter;
    private FrameLayout subActivityContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        subActivityContent = (FrameLayout) findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.base_layout, subActivityContent, true);
        WebView w = (WebView) findViewById(R.id.webView);
        w.loadUrl(getString(R.string.remoteServer));

        ArrayList<Log> log = Globals.getApplicationLog();

        listView_Logs = (ListView) findViewById(R.id.listview_Logs);

        adapter = new LogAdapter(log, getApplicationContext());
        listView_Logs.setAdapter(adapter);
    }
}
