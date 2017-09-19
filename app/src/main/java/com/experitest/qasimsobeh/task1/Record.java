package com.experitest.qasimsobeh.task1;

/**
 * Created by Qasim on 9/18/2017.
 */

public class Record
{
    private String time;
    private int score;
    private String userName;

    public Record(){}

    public Record(String time, String userName, int score)
    {

        this.time = time;
        this.userName = userName;
        this.score = score;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
