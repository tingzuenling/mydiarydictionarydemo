package com.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myproject.R;

import java.util.List;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class ListViewAdapter extends BaseAdapter {
    List<String> mlist;
    Context mContext;
    LayoutInflater mInflater;

    public ListViewAdapter(Context context,List<String> list) {
        this.mlist = list;
        this.mContext=context;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null)
        {convertView=mInflater.inflate(R.layout.toutiaomessage,parent,false);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.toutiaomess);
            convertView.setTag(viewHolder);
        }
       viewHolder= (ViewHolder) convertView.getTag();
        return convertView;
    }

    public class ViewHolder{
        TextView textView;
    }
}
