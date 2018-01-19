package com.sitemaji.demo.viewpager.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sitemaji.demo.viewpager.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by showsky on 12/07/2017.
 */
final public class SitemajiAdView extends WebView {

    private final static String TAG = SitemajiAdView.class.getSimpleName();
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Logger.d(TAG, "load page finish url: %s", url);
            /*
            int[] screenSize = parseUrlSize(url);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                screenSize[0],
                getResources().getDisplayMetrics()
            );
            params.height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                screenSize[1],
                getResources().getDisplayMetrics()
            );
            view.setLayoutParams(params);
            */
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        }
    };

    public SitemajiAdView(Context context) {
        super(context);
        initWebView();
    }

    public SitemajiAdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    public SitemajiAdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView();
    }

    private void initWebView() {
        if (Logger.isDebug()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }

        WebSettings setting = getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setWebViewClient(mWebViewClient);
    }

    public static int[] parseUrlSize(String url) {
        Pattern pattern = Pattern.compile(".*s=(\\d+)x(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher= pattern.matcher(url);
        int width = 0;
        int high = 0;
        if (matcher.find() && matcher.groupCount() == 2) {
            try {
                width = Integer.parseInt(matcher.group(1));
                high = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException e) {}
        }

        return new int[] {
            width,
            high
        };
    }

    public static boolean checkSupportSize(Context context, int width, int high) {
        if (width == 0 && high == 0) {
            Logger.e(TAG, "width = 0 and high = 0");
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidth = displayMetrics.widthPixels;
        float screenHigh = displayMetrics.heightPixels;
        float widthDP = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            width,
            context.getResources().getDisplayMetrics()
        );
        float highDP = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            high,
            context.getResources().getDisplayMetrics()
        );
        return widthDP < screenWidth && highDP < screenHigh;
    }

    @Override
    public void loadUrl(String url) {
        if ( ! URLUtil.isValidUrl(url)) {
            Logger.e(TAG, "illegal url: %s", url);
            return;
        }
        /*
        int[] screenSize = parseUrlSize(url);
        if (checkSupportSize(getContext(), screenSize[0], screenSize[1])) {
            Logger.d(TAG, "Load Url: %s", url);
            super.loadUrl(url);
        } else {
            Logger.w(TAG, "not support screen size");
            setVisibility(View.GONE);
        }
        */

        super.loadUrl(url);
    }
}
