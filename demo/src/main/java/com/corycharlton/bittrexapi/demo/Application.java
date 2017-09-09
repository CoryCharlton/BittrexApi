package com.corycharlton.bittrexapi.demo;

import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationSettings.initialize(this);
    }
}
