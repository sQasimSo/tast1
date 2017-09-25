package com.experitest.qasimsobeh.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class LogAdapter extends ArrayAdapter<Log>
{
    private ArrayList<Log> applicationLog;
    Context mContext;

    public LogAdapter(ArrayList<Log> log, Context context)
    {
        super(context, R.layout.row_layout, log);
        this.applicationLog = log;
        this.mContext = context;
    }

    private static class ViewHolder
    {
        TextView txtNumber;
        TextView txtAction;
        TextView txtActivity;
        TextView txtTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Log currentLog = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_layout, parent, false);

            viewHolder.txtAction = (TextView) convertView.findViewById(R.id.textView_action);
            viewHolder.txtActivity = (TextView) convertView.findViewById(R.id.textView_activity);
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.textView_time);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.textView_number);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtNumber.setText("" + (position+1));
        viewHolder.txtAction.setText(currentLog.getAction());
        viewHolder.txtActivity.setText(currentLog.getActivity());
        viewHolder.txtTime.setText(currentLog.getTime());

        return result;
    }
}
