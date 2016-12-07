package com.myproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.myproject.Activity.MainActivity;
import com.myproject.Activity.NewActivity;
import com.myproject.Adapter.ContentlistAdapter;
import com.myproject.Adapter.ListViewAdapter;
import com.myproject.Adapter.ViewPagerAdapter;
import com.myproject.Bean.Contentlist;
import com.myproject.Bean.Result;
import com.myproject.Httputils.Httputils;
import com.myproject.Httputils.Myapplication;
import com.myproject.R;

import org.xml.sax.helpers.LocatorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import static com.myproject.Httputils.Httputils.APPID;
import static com.myproject.Httputils.Httputils.APPSECRET;
import static com.myproject.Httputils.Httputils.MAXRESULT;
import static com.myproject.Httputils.Httputils.NEEDALLLIST;
import static com.myproject.Httputils.Httputils.NEEDCONTENT;
import static com.myproject.Httputils.Httputils.NEEDHTML;
import static com.myproject.Httputils.Httputils.PAGE;
import static com.myproject.Httputils.Httputils.URL_REQUEST;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class ToutiaoFragment extends Fragment implements View.OnClickListener {
    private ViewPager viewPager;
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private int[] pic = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4};
    private ViewPagerAdapter mAdapter;
    private EditText edit;
    private Button btn;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<String> lists = new ArrayList<String>();
    private ContentlistAdapter comAdapter;
    public List<Contentlist> contentlists = new ArrayList<Contentlist>();
    private Handler mHandler;
    private View footer;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toutiaofragment, container, false);
        initImageView(view);
        //Viewpager
        viewPager = (ViewPager) view.findViewById(R.id.toutiaovp);

        for (int i = 0; i < pic.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(pic[i]);
            mImageViews.add(imageView);
        }
        mAdapter = new ViewPagerAdapter(mImageViews);
        viewPager.setAdapter(mAdapter);

        //自动轮播
        mHandler = new Handler();
        imageloope(5000);
        //循环图片，但是衔接有问题，因此在adapter加载多个item的方法

        listView = (ListView) view.findViewById(R.id.listview);

        //添加上拉加载功能
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        footer = mInflater.inflate(R.layout.footer, null);
//        listView.addFooterView(footer);
        getTop();

//        footerupdate();
        return view;


    }

    private void footerupdate() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

             @Override
             public void onScrollStateChanged(AbsListView view, int scrollState) {

             }

             @Override
             public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                 Log.i("test", "first:" + firstVisibleItem + "  visible:" + visibleItemCount + "  last:" + totalItemCount);
                 if (totalItemCount == firstVisibleItem + visibleItemCount) {

                     listView.setOnTouchListener(new View.OnTouchListener() {
                         @Override
                         public boolean onTouch(View v, MotionEvent event) {
                             float startY = 0;
                             float tempY = 0;
                             boolean add = false;
                             switch (event.getAction()) {
                                 case MotionEvent.ACTION_DOWN: {
                                     startY = event.getY();
                                     break;
                                 }
                                 case MotionEvent.ACTION_UP: {
                                     tempY = event.getY();
                                     break;
                                 }
                             }
                             if ((tempY - startY) >= 80) {
                                 if (add==false) {
                                     listView.addFooterView(footer);
                                     add = true;
                                 }
                             } else{ add = false;
                             listView.removeFooterView(footer);
                             }
                             Log.i("test", "onTouch: " + startY+"  "+tempY+"  "+add);
                             return false;
                         }
                     });



                 }

             }
         });
    }

    //loope一直调用自己实现循环
    private void imageloope(final int i) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() < 100) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    viewPager.setCurrentItem(0);
                }
                imageloope(i);
            }

        }, i);
    }


    private void initImageView(View view) {
        ImageView channel1 = (ImageView) view.findViewById(R.id.iv1);
        ImageView channel2 = (ImageView) view.findViewById(R.id.iv2);
        ImageView channel3 = (ImageView) view.findViewById(R.id.iv3);
        ImageView channel4 = (ImageView) view.findViewById(R.id.iv4);
        ImageView channel5 = (ImageView) view.findViewById(R.id.iv5);
        ImageView channel6 = (ImageView) view.findViewById(R.id.iv6);
        ImageView channel7 = (ImageView) view.findViewById(R.id.iv7);
        ImageView channel8 = (ImageView) view.findViewById(R.id.iv8);
        ImageView channel9 = (ImageView) view.findViewById(R.id.iv9);
        ImageView channe20 = (ImageView) view.findViewById(R.id.iv10);
        edit = (EditText) view.findViewById(R.id.edit_search);
        btn = (Button) view.findViewById(R.id.btn_search);
        btn.setOnClickListener(this);

        channel1.setOnClickListener(this);
        channel2.setOnClickListener(this);
        channel3.setOnClickListener(this);
        channel4.setOnClickListener(this);
        channel5.setOnClickListener(this);
        channel6.setOnClickListener(this);
        channel7.setOnClickListener(this);
        channel8.setOnClickListener(this);
        channel9.setOnClickListener(this);
        channe20.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NewActivity.class);
        intent.putExtra("isfragment2", false);
        switch (v.getId()) {
            case R.id.iv1: {
                intent.putExtra("channelName", "国内焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv2: {
                intent.putExtra("channelName", "国际焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv3: {
                intent.putExtra("channelName", "汽车焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv4: {
                intent.putExtra("channelName", "体育焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv5: {
                intent.putExtra("channelName", "财经焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv6: {
                intent.putExtra("channelName", "游戏焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv7: {
                intent.putExtra("channelName", "军事最新");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv8: {
                intent.putExtra("channelName", "互联网最新");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv9: {
                intent.putExtra("channelName", "CBA最新");
                intent.putExtra("title", "");
                break;
            }
            case R.id.iv10: {
                intent.putExtra("channelName", "娱乐焦点");
                intent.putExtra("title", "");
                break;
            }
            case R.id.btn_search: {
                edit.setText("");
                intent.putExtra("title", edit.getText().toString());
                intent.putExtra("channelName", "");

                break;
            }

        }
        startActivity(intent);
    }

    private void getTop() {
        RequestQueue queue = Myapplication.getHttpRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Result result = gson.fromJson(s, Result.class);
                contentlists = result.getShowapi_res_body().getPagebean().getContentlist();
                comAdapter = new ContentlistAdapter(getContext(), contentlists);
                listView.setAdapter(comAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("act1 fragment1请求失败", volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("showapi_appid", APPID);
                params.put("showapi_sign", APPSECRET);
                params.put("maxResult", MAXRESULT);
                params.put("needAllList", NEEDALLLIST);
                params.put("needContent", NEEDCONTENT);
                params.put("needHtml", NEEDHTML);
                params.put("page", PAGE);

                return params;
            }
        };
        queue.add(stringRequest);
    }

}
