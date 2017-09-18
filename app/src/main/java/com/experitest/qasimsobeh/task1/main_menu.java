package com.experitest.qasimsobeh.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class main_menu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        TextView textView = (TextView) findViewById(R.id.textView_welcome);
        textView.setText("Welcome " + Globals.getUserName() + "!");
    }

    public void openLogsButton(View view)
    {
        Intent intent_Logs = new Intent(getApplicationContext(),Logs.class);
        startActivity(intent_Logs);
    }
    public void logOutButton(View view)
    {
        Globals.setUserName("");
        Intent intent_Logs = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent_Logs);
    }
    public void openRecordsButton(View view)
    {
        Intent intent_Logs = new Intent(getApplicationContext(),Records.class);
        startActivity(intent_Logs);
    }
    public void GameButton(View view)
    {
        Intent intent_Logs = new Intent(getApplicationContext(),GameActivity.class);
        startActivity(intent_Logs);
    }
}
