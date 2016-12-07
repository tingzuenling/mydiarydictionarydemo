package com.myproject.Activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.myproject.Bean.Result;
import com.myproject.Fragment.DiaryFragment;
import com.myproject.Fragment.DictionaryFragment;
import com.myproject.Fragment.SaishiFragment;
import com.myproject.Fragment.ToutiaoFragment;
import com.myproject.Httputils.Httputils;
import com.myproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myproject.Httputils.Httputils.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private ViewPager viewPager;
    private int[] pic = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4};
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView tab4;
    private float alpha = 0.1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        viewPager = (ViewPager) findViewById(R.id.vp);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (position == 0) {
                } else {
                    super.destroyItem(container, position, object);
                }
            }
        };
        viewPager.setAdapter(mAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("test", "" + position + "  " + positionOffset + "  " + positionOffsetPixels);

                hideTab();
                switch (position) {
                    case 0: {
                        tab1.setAlpha(1 - ((1-alpha)) * positionOffset);
                        tab2.setAlpha(alpha +(1-alpha) * positionOffset);
                        break;
                    }
                    case 1: {
                        tab2.setAlpha(1 - ((1-alpha)) * positionOffset);
                        tab3.setAlpha(alpha +(1-alpha) * positionOffset);
                        break;
                    }
                    case 2: {
                        tab3.setAlpha(1 - ((1-alpha)) * positionOffset);
                        tab4.setAlpha(alpha +(1-alpha) * positionOffset);
                        break;
                    }
                    case 3: {
                        tab4.setAlpha(1.0f);
                        break;
                    }
                }

            }

            @Override
            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0: {
//                        hideTab();
//                        tab1.setAlpha(1.0f);
//                        break;
//                    }
//                    case 1: {
//                        hideTab();
//                        tab2.setAlpha(1.0f);
//                        break;
//                    }
//                    case 2: {
//                        hideTab();
//                        tab3.setAlpha(1.0f);
//                        break;
//                    }
//                    case 3: {
//                        hideTab();
//                        tab4.setAlpha(1.0f);
//                        break;
//                    }
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//       android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
//         transaction.add(R.id.content,toutiaoFragment)


    }

    private void initFragment() {
        Fragment toutiaoFragment = new ToutiaoFragment();
        Fragment saishiFragment = new SaishiFragment();
        Fragment diaryFragment = new DiaryFragment();
        Fragment dictionaryFragment = new DictionaryFragment();
        fragments.add(toutiaoFragment);
        fragments.add(saishiFragment);
        fragments.add(dictionaryFragment);
        fragments.add(diaryFragment);

    }

    private void initView() {
        tab1 = (TextView) findViewById(R.id.tab_01);
        tab2 = (TextView) findViewById(R.id.tab_02);
        tab3 = (TextView) findViewById(R.id.tab_03);
        tab4 = (TextView) findViewById(R.id.tab_04);
        tab2.setAlpha(alpha);
        tab3.setAlpha(alpha);
        tab4.setAlpha(alpha);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        hideTab();
        switch (v.getId()) {
            case R.id.tab_01: {
                tab1.setAlpha(1.0f);
                viewPager.setCurrentItem(0, false);
                break;
            }
            case R.id.tab_02: {
                tab2.setAlpha(1.0f);
                viewPager.setCurrentItem(1, false);
                break;
            }
            case R.id.tab_03: {
                tab3.setAlpha(1.0f);
                viewPager.setCurrentItem(2, false);
                break;
            }
            case R.id.tab_04: {
                tab4.setAlpha(1.0f);
                viewPager.setCurrentItem(3, false);
                break;
            }
        }
    }

    private void hideTab() {
        tab1.setAlpha(alpha);
        tab2.setAlpha(alpha);
        tab3.setAlpha(alpha);
        tab4.setAlpha(alpha);
    }
}
