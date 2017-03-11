package com.loh.tally.ui.presentations.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.presentations.list.presenter.PresentationListContract;

import javax.inject.Inject;

/**
 * File: PresentationListFragment.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class PresentationListFragment extends BaseFragment implements PresentationListContract.View {

    @Inject PresentationListContract.Presenter presenter;
    //@Inject PresentationListAdapter listAdapter;

    public static PresentationListFragment newInstance() {
        Bundle args = new Bundle();
        PresentationListFragment fragment = new PresentationListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_presentation_list;
    }
}
