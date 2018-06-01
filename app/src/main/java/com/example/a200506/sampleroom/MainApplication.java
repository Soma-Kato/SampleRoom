package com.example.a200506.sampleroom;

import android.app.Application;

import com.example.a200506.sampleroom.room.SampleDataBase;
import com.facebook.stetho.Stetho;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SampleDataBase.initInstance(this);

        Stetho.Initializer initializer = Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build();
        Stetho.initialize(initializer);
    }
}
