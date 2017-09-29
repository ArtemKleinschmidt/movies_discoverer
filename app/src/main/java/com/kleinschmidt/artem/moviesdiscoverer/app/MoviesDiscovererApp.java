package com.kleinschmidt.artem.moviesdiscoverer.app;

import android.app.Application;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */

public class MoviesDiscovererApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent appComponent() {
        return appComponent;
    }

}
