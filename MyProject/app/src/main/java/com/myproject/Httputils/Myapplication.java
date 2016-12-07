package com.myproject.Httputils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by D1ngZenL1ng on 2016/11/22.
 */

public class Myapplication extends Application {
    private static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        queue= Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpRequestQueue() {
        return queue;
    }
}
