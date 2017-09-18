package com.experitest.qasimsobeh.task1;

import android.app.Application;
import android.widget.ArrayAdapter;

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
        if(instance == null)
        {
            instance = new Globals();
        }
        return instance;
    }
}
