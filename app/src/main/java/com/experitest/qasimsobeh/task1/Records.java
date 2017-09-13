package com.experitest.qasimsobeh.task1;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Records extends AppCompatActivity
{
    DatabaseHelper myDB;
    EditText fName,lName,email,score;
    TextView insertionRes;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        myDB = new DatabaseHelper(this);

        fName = (EditText) findViewById(R.id.editText_firstName);
        lName = (EditText) findViewById(R.id.editText_lastName);
        email = (EditText) findViewById(R.id.editText_email);
        score = (EditText) findViewById(R.id.editText_score);
        insertionRes = (TextView) findViewById(R.id.textView_insertionRes);
        btnAddData = (Button) findViewById(R.id.button_add);

        checkDB();
    }

    public void checkDB()
    {
        btnAddData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean insertionStatus = myDB.insertData(fName.getText().toString(),
                                                          lName.getText().toString(),
                                                          email.getText().toString(),
                                                          Integer.parseInt(score.getText().toString()),
                                                          insertionRes);
                //need to check if the editTexts are empty and deal with it!
                if (insertionStatus==true)
                    insertionRes.setText("Added Successfully");
                else
                    insertionRes.setText("Insertion Failed!");
            }
        });
    }
}
