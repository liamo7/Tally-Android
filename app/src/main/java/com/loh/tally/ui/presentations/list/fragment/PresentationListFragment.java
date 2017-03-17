package com.loh.tally.ui.presentations.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.domain.model.Presentation;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.presentations.list.adapter.PresentationListAdapter;
import com.loh.tally.ui.presentations.list.presenter.PresentationListContract;
import com.loh.tally.ui.presentations.poll.activity.PollActivity;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * File: PresentationListFragment.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public class PresentationListFragment extends BaseFragment implements PresentationListContract.View, PresentationListAdapter.OnPresentationItemClickListener {

    @Inject PresentationListContract.Presenter presenter;

    // avoid injecting this due to the requirement for a reference within constructor
    // @Inject PresentationListAdapter listAdapter;
    private PresentationListAdapter listAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    public static PresentationListFragment newInstance(String moduleID) {
        Bundle args = new Bundle();
        args.putString(IntentUtil.INTENT_MODULE_KEY, moduleID);
        PresentationListFragment fragment = new PresentationListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attach(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listAdapter.cleanup();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_presentation_list;
    }

    private void setupRecycler() {
        listAdapter = new PresentationListAdapter(presenter.getPresentationModulesReference());
        listAdapter.setOnPresentationClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public String getModuleID() {
        return getArguments().getString(IntentUtil.INTENT_MODULE_KEY, null);
    }

    @Override
    public void onPresentationClicked(Presentation presentation) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.INTENT_PRESENTATION_KEY, presentation.getId());
        bundle.putString(IntentUtil.INTENT_MODULE_KEY, getModuleID());
        startActivity(PollActivity.getStartingIntent(getActivity(), bundle));
    }
}
