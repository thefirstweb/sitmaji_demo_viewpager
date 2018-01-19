package com.sitemaji.demo.viewpager.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.sitemaji.demo.viewpager.Constanct;
import com.sitemaji.demo.viewpager.R;
import com.sitemaji.demo.viewpager.adapter.ImagePagerAdapter;

/**
 * Created by showsky on 19/01/2018.
 */
public class ViewPagerActivity extends Activity {

    private Context mContext;
    private ViewPager mViewPager;
    private ImagePagerAdapter mImagePagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_view_pager);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPage_image);
        mViewPager.setOffscreenPageLimit(3);
        mImagePagerAdapter = new ImagePagerAdapter(mContext);
        mImagePagerAdapter.setAdPageAndSize((int) Math.ceil(Constanct.IMAGES.length / 2), 300, 300);
        mImagePagerAdapter.setData(Constanct.IMAGES);
        mViewPager.setAdapter(mImagePagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
