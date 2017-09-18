package com.experitest.qasimsobeh.task1;

/**
 * Created by Qasim on 9/18/2017.
 */

public class Record
{
    private String time;
    private String action;
    private String activity;

    public Record(){}

    public Record(String time, String action, String activity)
    {

        this.time = time;
        this.action = action;
        this.activity = activity;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getActivity()
    {
        return activity;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }
}
