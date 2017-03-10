package com.loh.tally.ui.modules.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.modules.list.adapter.ModuleListAdapter;
import com.loh.tally.ui.modules.list.presenter.ModuleListContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * File: ModuleListFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles the displaying of modules to the user.
 */
public class ModuleListFragment extends BaseFragment implements ModuleListContract.View {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject ModuleListContract.Presenter presenter;
    @Inject ModuleListAdapter listAdapter;

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
        ButterKnife.bind(this, layout);
        presenter.attach(this);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
        Timber.d(listAdapter.getItemCount() + "");
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
