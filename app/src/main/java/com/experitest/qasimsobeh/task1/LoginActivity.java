package com.experitest.qasimsobeh.task1;

import android.content.Intent;
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
    private FrameLayout subActivityContent;
    Globals g = Globals.getInstance();
    private TextView gps_latitude,gps_longitude,wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        subActivityContent = (FrameLayout) findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.base_layout, subActivityContent, true);
        WebView w = (WebView) findViewById(R.id.webView);
        w.loadUrl(getString(R.string.remoteServer));

        Button button_confirm = (Button) findViewById(R.id.button_confirm);

        gps_latitude = (TextView) findViewById(R.id.textView_GPS_Latitude);
        gps_longitude = (TextView) findViewById(R.id.textView_GPS_Longitude);
        wifi = (TextView) findViewById(R.id.textView_wifiName);

        String wifiString = getString(R.string.wifi) + Globals.getWifiName(getApplicationContext());
        gps_latitude.setText("Locating your device...");
        gps_longitude.setText("Looking for GPS coordinates..");
        wifi.setText(wifiString);

        View.OnClickListener btnClick = new View.OnClickListener()
        {
            EditText entry1 = (EditText) findViewById(R.id.editText_entry1);
            EditText entry2 = (EditText) findViewById(R.id.editText_entry2);
            TextView result = (TextView) findViewById(R.id.textView_result);

            @Override
            public void onClick(View view)
            {
                Globals.confirmLogin(getApplicationContext(),result,entry1,entry2);
            }
        };

        button_confirm.setOnClickListener(btnClick);
    }
}
