package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

    public void setBaseLayoutValues(Context context)
    {
        wifi = (TextView) findViewById(R.id.textView_wifiName);
        gps_latitude = (TextView) findViewById(R.id.textView_GPS_Latitude);
        gps_longitude = (TextView) findViewById(R.id.textView_GPS_Longitude);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new myLocationListener(getApplicationContext(),findViewById(R.id.textView_GPS_Latitude), findViewById(R.id.textView_GPS_Longitude));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 10);
                return;
            }
        }
        else
        {
            configureLocation();
        }

        gps_latitude.setText("Locating your device...");
        gps_longitude.setText("Looking for GPS coordinates..");
        wifi.setText("Wifi Name: " + getWifiName(getApplicationContext()));
    }
}
