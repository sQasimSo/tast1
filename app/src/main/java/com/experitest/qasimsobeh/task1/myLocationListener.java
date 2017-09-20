package com.experitest.qasimsobeh.task1;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Qasim on 9/20/2017.
 */

public class myLocationListener implements LocationListener
{
    TextView gps_latitude;
    TextView gps_longitude;
    Context context;

    public myLocationListener(Context context, View gps_latitude, View gps_longitude)
    {
        this.context = context;
        setGps_latitude((TextView)gps_latitude);
        setGps_longitude((TextView)gps_longitude);
    }

    public void setGps_latitude(TextView gps_latitude)
    {
        this.gps_latitude = gps_latitude;
    }

    public void setGps_longitude(TextView gps_longitude)
    {
        this.gps_longitude = gps_longitude;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        gps_latitude.setText("Latitude: " + location.getLatitude());
        gps_longitude.setText("Longitude: " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle)
    {

    }

    @Override
    public void onProviderEnabled(String s)
    {

    }

    @Override
    public void onProviderDisabled(String s)
    {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        this.context.startActivity(intent);
    }
}
