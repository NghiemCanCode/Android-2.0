package com.example.android_20;

import android.app.Application;

public class App extends Application {
    ArcheryDB archeryDB;

    @Override
    public void onCreate() {
        super.onCreate();
        archeryDB = new ArcheryDB(this);
        archeryDB.copyDatabase();
    }
}
