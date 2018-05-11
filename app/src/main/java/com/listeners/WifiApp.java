package com.listeners;

import android.app.Application;

/**
 * Created by Luong Vien on 19.04.2018.
 */

public class WifiApp extends Application {
    static WifiApp wifiInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }

    public static synchronized WifiApp getInstance() {
        return wifiInstance;
    }

}