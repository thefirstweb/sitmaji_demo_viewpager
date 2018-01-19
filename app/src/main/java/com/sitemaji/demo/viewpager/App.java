package com.sitemaji.demo.viewpager;

import android.app.Application;

/**
 * Created by showsky on 19/01/2018.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setDebugLog(true);
    }
}
