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

        View.OnClickListener btnClick = new View.OnClickListener()
        {
            EditText entry1 = (EditText) findViewById(R.id.editText_entry1);
            EditText entry2 = (EditText) findViewById(R.id.editText_entry2);
            TextView result = (TextView) findViewById(R.id.textView_result);

            @Override
            public void onClick(View view)
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
}
