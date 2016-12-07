package com.myproject.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.Bean.Contentlist;
import com.myproject.Fragment.NewContentFragment;
import com.myproject.Fragment.NewListFragment;
import com.myproject.R;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;


/*
* 由两个fragment组成
* 一个显示Mainactivity获得的新闻分类的列表
* 一个显示详细的新闻内容
* */
public class NewActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TextView channelTitle;
    private Fragment fragmentNewlist = new NewListFragment();
    private Fragment fragmentNewcontent = null;
    private float startX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        //实例化
        channelTitle = (TextView) findViewById(R.id.new_category);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragments.add(fragmentNewlist);
//        fragmentTransaction.add(R.id.content_fragment, fragments.get(0));
        //获取头条页面listview点击后发送过来的序列化数据
        Contentlist content = (Contentlist) getIntent().getSerializableExtra("content");
        //接受channelName并显示
        Bundle bundle = new Bundle();
        String channel = getIntent().getStringExtra("channelName");
        String title = getIntent().getStringExtra("title");
//        将channelname存入bundle
        bundle.putString("channelName", channel);
        bundle.putString("title", title);
        channelTitle.setText(channel);

        if (getIntent().getBooleanExtra("isfragment2", false)) {
            //判断是否要打开fragment2，是才加载，不是不加载
            if (fragmentNewcontent == null) {
                fragmentNewcontent = new NewContentFragment();
                fragments.add(fragmentNewcontent);
                fragmentTransaction.add(R.id.content_fragment, fragments.get(1));
            }
            bundle.putSerializable("content", content);
            fragmentNewcontent.setArguments(bundle);
            //发送channelname给newlist
            fragmentNewlist.setArguments(bundle);
//        Toast.makeText(this, content.getDesc(), Toast.LENGTH_LONG).show();
        }
        //说明不需要fragment2，则为查询新闻，所以传递bundle给fragment1
        else {
//            bundle.putString("title", title);
//            bundle.putString("channelName", channel);
            fragmentNewlist.setArguments(bundle);
            fragmentTransaction.add(R.id.content_fragment, fragments.get(0));
        }
        fragmentTransaction.commit();
    }

    //设置滑动结束页面
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (startX < (event.getX() - 50)) {
                    finish();
                }
                break;
            }

        }
        return super.onTouchEvent(event);

    }
}
