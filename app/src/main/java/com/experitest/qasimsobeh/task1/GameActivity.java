package com.experitest.qasimsobeh.task1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;


public class GameActivity extends AppCompatActivity
{
    boolean isPlaying = true;
    long timer = 3000;
    CountDownTimer countDownTimer;
    int clicksCount = -1;
    TextView gps_latitude, gps_longitude, wifi, textView_timer;
    Button button;
    RelativeLayout layout;
    ImageButton imageButton;
    int imageButtonX, imageButtonY, imageButtonDimensions, min = 50, max = 100;
    int layoutWidthForGame, layoutHeightForGame;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FrameLayout subActivityContent;
    Globals g;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        subActivityContent = (FrameLayout) findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.base_layout, subActivityContent, true);
        WebView w = (WebView) findViewById(R.id.webView);
        w.loadUrl(getString(R.string.remoteServer));

        button = (Button) findViewById(R.id.button_startGame);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textView_timer = (TextView) findViewById(R.id.textView_remainingTime);
        g = Globals.getInstance();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                button.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                switchButtonLocation();
                startTimer(timer);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                countDownTimer.cancel();
                timer *= 0.95;
                switchButtonLocation();
                startTimer(timer);
            }
        });
        //those line of code are for GPS coordinated and wifi name.

        wifi = (TextView) findViewById(R.id.textView_wifiName);
        gps_latitude = (TextView) findViewById(R.id.textView_GPS_Latitude);
        gps_longitude = (TextView) findViewById(R.id.textView_GPS_Longitude);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new myLocationListener(getApplicationContext(), findViewById(R.id.textView_GPS_Latitude), findViewById(R.id.textView_GPS_Longitude));

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

        String wifiString = getString(R.string.wifi) + Globals.getWifiName(getApplicationContext());
        gps_latitude.setText("Locating your device...");
        gps_longitude.setText("Looking for GPS coordinates..");
        wifi.setText(wifiString);
    }

    public void configureLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
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

    public void switchButtonLocation()
    {
        clicksCount++;

        Random r = new Random();

        imageButtonDimensions = r.nextInt(max - min) + max;
        layout = (RelativeLayout) findViewById(R.id.generalLayout);

        layoutWidthForGame = layout.getWidth() - imageButtonDimensions;
        layoutHeightForGame = layout.getHeight() - imageButtonDimensions;

        imageButtonX = r.nextInt(layoutWidthForGame);
        imageButtonY = r.nextInt(layoutHeightForGame);
        imageButton.setX(imageButtonX);
        imageButton.setY(imageButtonY);


        LayoutParams params = new LayoutParams(imageButtonDimensions, imageButtonDimensions);
        imageButton.setLayoutParams(params);
    }

    private void startTimer(long time)
    {
        countDownTimer = new CountDownTimer(time, 100)
        {
            public void onTick(long millisUntilDone)
            {
                if (isPlaying == true)
                {
                    textView_timer.setText("seconds remaining: " + millisUntilDone / 100 + "." + millisUntilDone % 100);
                }
                else
                {
                    textView_timer.setText("done!");
                }
            }

            public void onFinish()
            {
                isPlaying = false;
                imageButton.setVisibility(View.INVISIBLE);
                textView_timer.setText("done!");

                BackgroundTask backgroundTask = new BackgroundTask(getApplication());
                backgroundTask.execute("add_info", Globals.getUserName(), DateFormat.getDateTimeInstance().format(new Date()), "" + clicksCount);
                finish();

                clicksCount = 0;
                button.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    /*@Override
    protected void onResume()
    {
        if (currentuser.getusername().equals(""))
        {
            Intent myintent = new Intent(gamescreen.this, MainActivity.class);
            startActivity(myintent);
        }
        super.onResume();
    }*/
}
