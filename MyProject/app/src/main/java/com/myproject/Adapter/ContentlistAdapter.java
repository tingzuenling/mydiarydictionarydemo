package com.myproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.myproject.Activity.NewActivity;
import com.myproject.Bean.Contentlist;
import com.myproject.Httputils.BitmapCache;
import com.myproject.Httputils.Myapplication;
import com.myproject.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by D1ngZenL1ng on 2016/11/21.
 */

public class ContentlistAdapter extends BaseAdapter {
    private List<Contentlist> mContentlists;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContentlistAdapter(Context context, List<Contentlist> contentlists) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mContentlists = contentlists;
    }

    @Override
    public int getCount() {
        return mContentlists.size();
    }

    @Override
    public Object getItem(int position) {
        return mContentlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        final Contentlist content = mContentlists.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.new_list, parent, false);

            viewHolder.pic = (ImageView) convertView.findViewById(R.id.new_pic);
            viewHolder.tv_resource = (TextView) convertView.findViewById(R.id.new_resource);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.new_time);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.new_title);
            viewHolder.tv_desc = (TextView) convertView.findViewById(R.id.new_desc);
            if ((content.getImageurls().size()) != 0) {
                String url = content.getImageurls().get(0).getUrl();
                ImageLoader imgLoader=new ImageLoader(Myapplication.getHttpRequestQueue(),new BitmapCache());
                ImageLoader.ImageListener listener=ImageLoader.getImageListener(viewHolder.pic,R.drawable.item13,R.drawable.item13);
                imgLoader.get(url,listener);
            }
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tv_resource.setText(content.getSource());
        viewHolder.tv_time.setText(content.getPubDate());
        viewHolder.tv_title.setText(content.getTitle());
        viewHolder.tv_desc.setText(content.getDesc());
        if ((content.getImageurls().size()) != 0) {
            String url = content.getImageurls().get(0).getUrl();
            ImageLoader imgLoader=new ImageLoader(Myapplication.getHttpRequestQueue(),new BitmapCache());
            ImageLoader.ImageListener listener=ImageLoader.getImageListener(viewHolder.pic,R.drawable.item13,R.drawable.item13);
            imgLoader.get(url,listener);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "你点击了第"+(position+1)+"个新闻", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();

//                Bundle bundle=new Bundle();
//                bundle.putSerializable("content", (Serializable) mContentlists);
                intent.putExtra("content", (Serializable) mContentlists.get(position));
                intent.putExtra("isfragment2", true);

                intent.putExtra("channelName", mContentlists.get(position).getChannelName());
                intent.setClass(mContext, NewActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView tv_time;
        TextView tv_resource;
        TextView tv_desc;
        TextView tv_title;
        ImageView pic;
    }
}
