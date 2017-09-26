package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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
    public static Location location;
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
}
