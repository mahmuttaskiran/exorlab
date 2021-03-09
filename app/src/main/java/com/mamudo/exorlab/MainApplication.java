package com.mamudo.exorlab;

import android.app.Application;
import com.mamudo.exorlab.di.ApplicationGraph;
import com.mamudo.exorlab.di.DaggerApplicationGraph;

public class MainApplication extends Application {
    public ApplicationGraph appGraph = DaggerApplicationGraph.create();
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
