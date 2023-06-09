package com.nippy.nippydemo;

import android.app.Application;

import com.sdk.nippy.NippyPay;

public class AppLifeCycle extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NippyPay.init(AppLifeCycle.this);
    }
}
