package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Qasim on 9/18/2017.
 */

public class Globals extends Application
{
    private static Globals instance;
    private static String userName;
    private static ArrayList<Log> applicationLog;

    public static ArrayAdapter<Record> getScoresRecord()
    {
        return scoresRecord;
    }

    public static void setScoresRecord(ArrayAdapter<Record> scoresRecord)
    {
        Globals.scoresRecord = scoresRecord;
    }

    private static ArrayAdapter<Record> scoresRecord;

    private Globals()
    {
        this.applicationLog = new ArrayList<Log>();
    }

    public static String getUserName()
    {
        return userName;
    }

    public static void setUserName(String userName)
    {
        Globals.userName = userName;
    }

    public static ArrayList<Log> getApplicationLog()
    {
        return applicationLog;
    }

    public static void setApplicationLog(ArrayList<Log> applicationLog)
    {
        Globals.applicationLog = applicationLog;
    }

    public static synchronized Globals getInstance()
    {
        if (instance == null)
        {
            instance = new Globals();
        }
        return instance;
    }

    public static String getWifiName(Context context)
    {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled())
        {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null)
            {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR)
                {
                    return wifiInfo.getSSID();
                }
            }
        }
        return null;
    }

    public static void confirmLogin(Context context, TextView result, EditText entry1, EditText entry2)
    {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log newLog = new Log(currentDateTimeString,"Confirm Button Clicked", "Login Activity");
        Globals.getApplicationLog().add(newLog);

        newLog = Globals.getApplicationLog().get(0);

        if(newLog == null)
        {
            result.setText("Log not found!");
        }
        else
        {
            result.setText(newLog.getTime() + "," + newLog.getAction() + "," + newLog.getActivity());
        }


        String entry1Value = entry1.getText().toString();
        String entry2Value = entry2.getText().toString();

        if(entry1Value.length() == 0 && entry2Value.length() == 0)
        {
            result.setText("Entries are empty!");
        }
        else
        {
            if (entry1Value.equals(entry2Value))
            {
                result.setText("Confirmed!");
                Intent intent = new Intent(context, main_menu.class);
                Globals.setUserName(entry1Value);

                context.startActivity(intent);
                //setContentView(R.layout.activity_records);
            }
            else
            {
                result.setText("Entries are not identical!");
            }
        }
    }
}
