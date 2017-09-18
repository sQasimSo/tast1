package com.experitest.qasimsobeh.task1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qasim on 9/18/2017.
 */

class LogAdapter extends ArrayAdapter
{
    List list = new ArrayList();

    static class DataHandler
    {
        TextView number;
        TextView time;
        TextView action;
        TextView activity;
    }
    public LogAdapter(@NonNull Context context, @LayoutRes int resource)
    {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position)
    {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View row;
        DataHandler dh;
        LayoutInflater inflater;
        row = convertView;

        if(convertView == null)
        {
            inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.row_layout,parent,false);

            dh = new DataHandler();
            dh.action = (TextView) row.findViewById(R.id.textView_action);
            dh.number = (TextView) row.findViewById(R.id.textView_number);
            dh.activity = (TextView) row.findViewById(R.id.textView_activity);
            dh.time = (TextView) row.findViewById(R.id.textView_time);

            row.setTag(dh);
        }
        else
        {
            dh = (DataHandler)row.getTag();
        }

        Log log = (Log) this.getItem(position);

        dh.number.setText(log.getTime());
        dh.action.setText(log.getAction());
        dh.activity.setText(log.getActivity());
        dh.time.setText(log.getTime());

        return row;
    }
}
