package com.example.vuthiphuong_android43_btcuoikhoa.login;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
