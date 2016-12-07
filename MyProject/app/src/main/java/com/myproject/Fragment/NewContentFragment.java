package com.myproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.Activity.NewActivity;
import com.myproject.Bean.Contentlist;
import com.myproject.Interface.SetContent;
import com.myproject.R;

import java.util.List;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class NewContentFragment extends Fragment {
    private TextView tv_content;
    private ScrollView scrollView;
    private TextView tv_title;
    private TextView tv_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newcontentfragment, container, false);

        Bundle bundle = null;
        Contentlist contentlist;
        if ((bundle = getArguments()) != null) {
            contentlist = (Contentlist) bundle.getSerializable("content");
            tv_content = (TextView) view.findViewById(R.id.contentPanel);
            tv_title = (TextView) view.findViewById(R.id.new_content_title);
            tv_date = (TextView) view.findViewById(R.id.new_content_time);
            if (contentlist != null) {
                tv_title.setText(contentlist.getTitle());
                tv_date.setText(contentlist.getPubDate() + "\n");

                for (int i = 0; i < contentlist.getAllList().length; i++) {
                    if (contentlist.getAllList()[i] instanceof String) {
                        String new_content = new String(contentlist.getAllList()[i] + "");
                        if (new_content.startsWith("　　")) {
                            tv_content.append("" + contentlist.getAllList()[i] + "\n");
                        }else tv_content.append("        " + contentlist.getAllList()[i] + "\n");
                    }

                }
            } else {
                tv_title.setText("数据加载出错...");
                tv_date.setText("数据加载出错..." + "\n");
                tv_content.append("数据加载出错...");
            }
//        tv_content.setText(contentlist.getDesc());
        }

        return view;


    }


}
