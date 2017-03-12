package com.loh.tally.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.TallyApp;
import com.loh.tally.ui.base.dagger.component.ViewComponent;
import com.loh.tally.ui.base.dagger.module.ViewModule;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * File: BaseFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles creation of view components for {@link Fragment}.
 */
public abstract class BaseFragment extends Fragment {

    @Inject protected Bus eventbus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    private void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    protected abstract void inject();

    protected abstract int getFragmentLayout();

    protected ViewComponent getViewComponent() {
        return getTallyApp()
                .getApplicationComponent()
                .plusViewComponent(new ViewModule((AppCompatActivity) getActivity()));
    }

    protected TallyApp getTallyApp() {
        return (TallyApp) getActivity().getApplication();
    }
}
