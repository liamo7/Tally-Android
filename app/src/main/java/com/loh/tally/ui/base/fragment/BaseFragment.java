package com.loh.tally.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.loh.tally.TallyApp;
import com.loh.tally.ui.base.dagger.component.ViewComponent;
import com.loh.tally.ui.base.dagger.module.ViewModule;

/**
 * File: BaseFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles creation of view components for {@link Fragment}.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    protected abstract void inject();

    protected ViewComponent getViewComponent() {
        return getTallyApp()
                .getApplicationComponent()
                .plusViewComponent(new ViewModule((AppCompatActivity) getActivity()));
    }

    protected TallyApp getTallyApp() {
        return (TallyApp) getActivity().getApplication();
    }
}
