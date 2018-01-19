package com.sitemaji.demo.viewpager;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by showsky on 19/01/2018.
 */

public class GlideUtils {

    public static void loadUrl(Context context, String url, ImageView imageView) {
        Glide.with(context)
            .load(url)
            .into(imageView);
    }
}
