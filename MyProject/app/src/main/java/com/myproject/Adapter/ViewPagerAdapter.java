package com.myproject.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public  class  ViewPagerAdapter extends PagerAdapter {
    List<ImageView> mImageViews=new ArrayList<ImageView>();

    public ViewPagerAdapter(List<ImageView> imageViews) {
        this.mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position%4));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        container.addView(mImageViews.get(position%4));

        return mImageViews.get(position%4);
    }
}