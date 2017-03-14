package com.loh.tally.ui.modules.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.domain.model.Module;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.modules.list.adapter.ModuleListAdapter;
import com.loh.tally.ui.modules.list.adapter.ModuleViewHolder;
import com.loh.tally.ui.modules.list.presenter.ModuleListContract;
import com.loh.tally.ui.presentations.main.activity.PresentationActivity;
import com.loh.tally.ui.presentations.main.adapter.PresentationPagerAdapter;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * File: ModuleListFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles the displaying of modules to the user.
 */
public class ModuleListFragment extends BaseFragment implements ModuleListContract.View, ModuleViewHolder.OnModuleItemClickListener {

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
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
        return R.layout.fragment_module_list;
    }

    private void setupRecycler() {
        listAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onModuleClicked(Module module) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.INTENT_MODULE_KEY, module.getId());
        bundle.putInt(IntentUtil.INTENT_PRESENTATION_PAGE, PresentationPagerAdapter.FRAGMENT_PRESENTATIONS);
        startActivity(PresentationActivity.getStartingIntent(getActivity(), bundle));
    }

    @Override
    public void onChatClicked(String moduleID) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.INTENT_MODULE_KEY, moduleID);
        bundle.putInt(IntentUtil.INTENT_PRESENTATION_PAGE, PresentationPagerAdapter.FRAGMENT_CHAT);
        startActivity(PresentationActivity.getStartingIntent(getActivity(), bundle));
    }
}
