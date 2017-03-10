package com.loh.tally.ui.base.dagger.module;

import android.support.v7.app.AppCompatActivity;

import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.main.adapter.MainPagerAdapter;
import com.loh.tally.ui.main.presenter.MainContract;
import com.loh.tally.ui.main.presenter.MainPresenter;
import com.loh.tally.ui.modules.list.presenter.ModuleListContract;
import com.loh.tally.ui.modules.list.presenter.ModuleListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * File: ViewModule.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Details how all components scoped by {@link ViewScope} are provided.
 */
@Module
public class ViewModule {

    private final AppCompatActivity activity;

    public ViewModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ViewScope
    public AppCompatActivity provideActivity() {
        return this.activity;
    }

    // Main Components
    @Provides
    @ViewScope
    public MainContract.Presenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @ViewScope
    public MainPagerAdapter provideMainPagerAdapter(AppCompatActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }

    // Module List Components
    @Provides
    @ViewScope
    public ModuleListContract.Presenter provideModuleListPresenter() {
        return new ModuleListPresenter();
    }
}
