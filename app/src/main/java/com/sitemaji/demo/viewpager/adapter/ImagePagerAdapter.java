package com.sitemaji.demo.viewpager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sitemaji.demo.viewpager.Constanct;
import com.sitemaji.demo.viewpager.GlideUtils;
import com.sitemaji.demo.viewpager.R;
import com.sitemaji.demo.viewpager.view.SitemajiAdView;

/**
 * Created by showsky on 19/01/2018.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private String[] mData;
    private int mPage = -1;
    private int mWidth, mHigh;

    public ImagePagerAdapter(Context context) {
        mContext = context;
    }

    public void setAdPageAndSize(int page, int width, int high) {
        if (page == 0 ) {
            page = 1;
        }
        mPage = page;
        mWidth = width;
        mHigh = high;
    }

    public int getAdPage() {
        return mPage;
    }

    public void setData(String[] data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return (mPage != -1) ? mData.length + 1 : mData.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup viewGroup;
        if (mPage != -1 && (mPage - 1 == position)) {
            viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.view_sitemaji_ad, container, false);
            SitemajiAdView sitemajiAdView = (SitemajiAdView) viewGroup.findViewById(R.id.sitemajiAdView);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) mWidth, mContext.getResources().getDisplayMetrics());
            int high = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) mHigh, mContext.getResources().getDisplayMetrics());
            ViewGroup.LayoutParams layoutParams = sitemajiAdView.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = high;
            sitemajiAdView.setLayoutParams(layoutParams);
            sitemajiAdView.loadUrl(Constanct.SITEMAJI_AD_URL);
        } else {
            viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.view_image, container, false);
            ImageView imageView = (ImageView) viewGroup.findViewById(R.id.imageView_image);
            int index = (position < mPage) ? position : position - 1;
            GlideUtils.loadUrl(mContext, mData[index], imageView);
        }
        container.addView(viewGroup);

        return viewGroup;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
