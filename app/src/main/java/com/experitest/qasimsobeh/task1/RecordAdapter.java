package com.experitest.qasimsobeh.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordAdapter extends ArrayAdapter<Record>
{
    private ArrayList<Record> gameRecord;
    Context mContext;

    public RecordAdapter(ArrayList<Record> records, Context context)
    {
        super(context, R.layout.record_row_layout, records);
        this.gameRecord = records;
        this.mContext = context;
    }

    private static class ViewHolder
    {
        TextView txtNumber;
        TextView txtTime;
        TextView txtUserName;
        TextView txtScore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Record currentRecord = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.record_row_layout, parent, false);

            viewHolder.txtScore = (TextView) convertView.findViewById(R.id.textView_score);
            viewHolder.txtUserName = (TextView) convertView.findViewById(R.id.textView_userName);
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
        viewHolder.txtUserName.setText(currentRecord.getUserName());
        viewHolder.txtScore.setText("" + currentRecord.getScore());
        viewHolder.txtTime.setText(currentRecord.getTime());

        return result;
    }
}


