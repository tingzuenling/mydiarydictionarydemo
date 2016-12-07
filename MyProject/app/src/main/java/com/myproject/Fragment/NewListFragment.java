package com.myproject.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.myproject.Adapter.ContentlistAdapter;
import com.myproject.Bean.Contentlist;
import com.myproject.Bean.Result;
import com.myproject.Httputils.Myapplication;
import com.myproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myproject.Httputils.Httputils.APPID;
import static com.myproject.Httputils.Httputils.APPSECRET;
import static com.myproject.Httputils.Httputils.MAXRESULT;
import static com.myproject.Httputils.Httputils.NEEDALLLIST;
import static com.myproject.Httputils.Httputils.NEEDCONTENT;
import static com.myproject.Httputils.Httputils.NEEDHTML;
import static com.myproject.Httputils.Httputils.PAGE;
import static com.myproject.Httputils.Httputils.URL_REQUEST;
import static com.myproject.Httputils.Httputils.getChannelName;
import static com.myproject.Httputils.Httputils.getTitle;
import static com.myproject.Httputils.Httputils.setChannelName;
import static com.myproject.Httputils.Httputils.setTitle;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class NewListFragment extends Fragment {
    private ListView newList;
    private ContentlistAdapter mAdapter;
    private List<Contentlist> contentlists = new ArrayList<Contentlist>();
    private String chanelname = "";
    private String title = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newlistfragment, container, false);
        Bundle bundle = getArguments();
        chanelname = bundle.getString("channelName");
        title=bundle.getString("title");
        setChannelName(chanelname);
        setTitle(title);
        getNewlist();
        newList = (ListView) view.findViewById(R.id.new_list);
        return view;
    }

    private void getNewlist() {

        RequestQueue queue = Myapplication.getHttpRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Result result = gson.fromJson(s, Result.class);
                contentlists = result.getShowapi_res_body().getPagebean().getContentlist();
                mAdapter = new ContentlistAdapter(getContext(), contentlists);
                newList.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("act2 fragment2请求失败", volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("channelName", getChannelName());
                params.put("title", getTitle());
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
