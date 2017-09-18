package com.experitest.qasimsobeh.task1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * Created by Qasim on 9/18/2017.
 */

public class RecordAdapter extends ArrayAdapter
{
    public RecordAdapter(@NonNull Context context, @LayoutRes int resource)
    {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object)
    {
        super.add(object);
    }
}


