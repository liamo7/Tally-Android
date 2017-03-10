package com.loh.tally.ui.modules.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.modules.list.presenter.ModuleListContract;

import javax.inject.Inject;

/**
 * File: ModuleListFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles the displaying of modules to the user.
 */
public class ModuleListFragment extends BaseFragment implements ModuleListContract.View {

    @Inject ModuleListContract.Presenter presenter;

    public static ModuleListFragment newInstance() {
        Bundle args = new Bundle();
        ModuleListFragment fragment = new ModuleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.fragment_module_list, container, false);
        presenter.attach(this);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }
}
