package com.experitest.qasimsobeh.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.experitest.qasimsobeh.task1.R.id.button_confirm;
import static com.experitest.qasimsobeh.task1.R.id.editText_entry1;
import static com.experitest.qasimsobeh.task1.R.id.textView_result;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button_confirm = (Button) findViewById(R.id.button_confirm);

        View.OnClickListener btnClick = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText entry1 = (EditText) findViewById(R.id.editText_entry1);
                EditText entry2 = (EditText) findViewById(R.id.editText_entry1);
                TextView result = (TextView) findViewById(R.id.textView_result);

                String entry1Value = entry1.getText().toString();
                String entry2Value = entry2.getText().toString();

                if(entry1Value.equals(entry2Value))
                {
                    result.setText("Confirmed!");
                }
                else
                {
                    result.setText("Entries are not identical!");
                }
            }
        };

        button_confirm.setOnClickListener(btnClick);
    }
}
