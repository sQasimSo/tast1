package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

import static com.experitest.qasimsobeh.task1.R.id.button_confirm;

public class LoginActivity extends AppCompatActivity
{
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FrameLayout subActivityContent;
    Globals g = Globals.getInstance();
    private TextView gps_latitude,gps_longitude,wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Globals.location = new Location(Context.LOCATION_SERVICE);
        subActivityContent = (FrameLayout) findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.base_layout, subActivityContent, true);
        WebView w = (WebView) findViewById(R.id.webView);
        w.loadUrl(getString(R.string.remoteServer));

        Button button_confirm = (Button) findViewById(R.id.button_confirm);

        gps_latitude = (TextView) findViewById(R.id.textView_GPS_Latitude);
        gps_longitude = (TextView) findViewById(R.id.textView_GPS_Longitude);
        wifi = (TextView) findViewById(R.id.textView_wifiName);

        String wifiString = getString(R.string.wifi) + Globals.getWifiName(getApplicationContext());
        gps_latitude.setText("" + Globals.location.getLatitude());
        gps_longitude.setText("" + Globals.location.getLongitude());
        wifi.setText(wifiString);

        //configureLocation();
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


        View.OnClickListener btnClick = new View.OnClickListener()
        {
            EditText entry1 = (EditText) findViewById(R.id.editText_entry1);
            EditText entry2 = (EditText) findViewById(R.id.editText_entry2);
            TextView result = (TextView) findViewById(R.id.textView_result);

            @Override
            public void onClick(View view)
            {
                //Globals.confirmLogin(getApplicationContext(),result,entry1,entry2);
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Log newLog = new Log(currentDateTimeString,"Confirm Button Clicked", "Login Activity");
                Globals.getApplicationLog().add(newLog);

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
                        Intent intent = new Intent(getApplicationContext(), main_menu.class);
                        Globals.setUserName(entry1Value);

                        startActivity(intent);
                        //setContentView(R.layout.activity_records);
                    }
                    else
                    {
                        result.setText("Entries are not identical!");
                    }
                }
            }
        };

        button_confirm.setOnClickListener(btnClick);
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
