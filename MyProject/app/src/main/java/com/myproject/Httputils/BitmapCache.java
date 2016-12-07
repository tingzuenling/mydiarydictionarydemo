package com.myproject.Httputils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by D1ngZenL1ng on 2016/11/22.
 */

public class BitmapCache implements ImageLoader.ImageCache {

    //创建缓存
    public LruCache<String,Bitmap> cache;
    public static final int MAX=10*1024*1024;

    public BitmapCache() {
        cache=new LruCache<String, Bitmap>(MAX){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };

    }
    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
           cache.put(s,bitmap);
    }
}
