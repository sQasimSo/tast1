package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Logs extends AppCompatActivity
{
    private TextView gps_latitude,gps_longitude,wifi;
    ListView listView_Logs;
    ListAdapter adapter;
    private FrameLayout subActivityContent;

    private LocationListener locationListener;
    private LocationManager locationManager;
    Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        subActivityContent = (FrameLayout) findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.base_layout, subActivityContent, true);
        WebView w = (WebView) findViewById(R.id.webView);
        w.loadUrl(getString(R.string.remoteServer));

        gps_latitude = (TextView) findViewById(R.id.textView_GPS_Latitude);
        gps_longitude = (TextView) findViewById(R.id.textView_GPS_Longitude);
        wifi = (TextView) findViewById(R.id.textView_wifiName);

        String wifiString = getString(R.string.wifi) + Globals.getWifiName(getApplicationContext());
        gps_latitude.setText("" + Globals.location.getLatitude());
        gps_longitude.setText("" + Globals.location.getLongitude());
        wifi.setText(wifiString);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new myLocationListener(getApplicationContext(), findViewById(R.id.textView_GPS_Latitude), findViewById(R.id.textView_GPS_Longitude));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        }

        ArrayList<Log> log = Globals.getApplicationLog();

        listView_Logs = (ListView) findViewById(R.id.listview_Logs);

        adapter = new LogAdapter(log, getApplicationContext());
        listView_Logs.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
                }
                return;
        }
    }
}
