package com.loh.tally;

import android.app.Application;

import com.loh.tally.ui.base.ActivityLifecycleCallback;
import com.loh.tally.ui.base.dagger.component.ApplicationComponent;
import com.loh.tally.ui.base.dagger.component.DaggerApplicationComponent;
import com.loh.tally.ui.base.dagger.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * File: TallyApp.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Application subclass for whole Android application.
 */
public class TallyApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
        initInjector();
        initLeakDetection();
        initOrientation();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initOrientation() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
