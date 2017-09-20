package com.experitest.qasimsobeh.task1;

import android.Manifest;
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
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import static android.R.attr.countDown;
import static android.R.attr.supportsSwitchingToNextInputMethod;
import static android.R.attr.text;
import static android.R.attr.width;
import static com.experitest.qasimsobeh.task1.R.attr.height;

public class GameActivity extends AppCompatActivity
{
    boolean lost = false;
    private Handler handler;
    double timer = 3000;
    CountDownTimer countDownTimer;
    int clicksCount = 0;
    TextView gps_latitude, gps_longitude, wifi, textView_timer;
    Button button;
    RelativeLayout layout;
    ImageButton imageButton;
    int imageButtonX, imageButtonY, imageButtonDimensions, min=50, max=100;
    int layoutWidthForGame, layoutHeightForGame;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView_timer = (TextView) findViewById(R.id.textView_remainingTime);
        button = (Button) findViewById(R.id.button_startGame);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        handler = new Handler();

        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //kol al khara hoon!
                imageButton.setVisibility(View.INVISIBLE);
                timer *=0.95;

                if(!lost)
                {
                    switchButtonLocation();
                    countDownTimer = new CountDownTimer((int) timer, 100)
                    {
                        @Override
                        public void onTick(long l)
                        {
                            textView_timer.setText("" + l);
                        }

                        @Override
                        public void onFinish()
                        {
                            if(imageButton.getVisibility() == View.INVISIBLE)
                            {
                                imageButton.setVisibility(View.VISIBLE);
                                countDownTimer.cancel();
                            }
                            else
                            {
                                lost = true;
                                imageButton.setEnabled(false);
                                textView_timer.setText("LOST");
                            }
                        }
                    }.start();
                }
                else
                {
                    textView_timer.setText("LOST");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                button.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);

                switchButtonLocation();
            }
        });
        //those line of code are for GPS coordinated and wifi name.
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureLocation();
                return;
        }
    }

    private void configureLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
    }

    public void switchButtonLocation()
    {
        clicksCount++;

        Random r = new Random();

        imageButtonDimensions = r.nextInt(max - min) + max;
        layout = (RelativeLayout) findViewById(R.id.layoutGame);

        layoutWidthForGame = layout.getWidth() - imageButtonDimensions;
        layoutHeightForGame = layout.getHeight() - imageButtonDimensions;

        imageButtonX = r.nextInt(layoutWidthForGame);
        imageButtonY = r.nextInt(layoutHeightForGame);
        imageButton.setX(imageButtonX);
        imageButton.setY(imageButtonY);


        LayoutParams params = new LayoutParams(imageButtonDimensions, imageButtonDimensions);
        imageButton.setLayoutParams(params);
    }


    public String getWifiName(Context context)
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
