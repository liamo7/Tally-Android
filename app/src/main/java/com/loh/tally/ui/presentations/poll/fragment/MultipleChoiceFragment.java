package com.loh.tally.ui.presentations.poll.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.presentations.poll.adapter.MultipleChoiceAdapter;
import com.loh.tally.ui.presentations.poll.presenter.MultipleChoiceContract;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * File: MultipleChoiceFragment.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class MultipleChoiceFragment extends PollFragment implements MultipleChoiceContract.View, MultipleChoiceAdapter.OnResponseClickListener {

    @BindView(R.id.question) TextView question;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.container) View container;

    @Inject MultipleChoiceContract.Presenter presenter;
    @Inject MultipleChoiceAdapter adapter;
    @Inject AuthenticationManager authenticationManager;

    private boolean clickable = true;

    public static MultipleChoiceFragment newInstance(Poll poll) {
        Bundle args = new Bundle();
        args.putParcelable(IntentUtil.ARGS_POLL, poll);
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
        setupPoll();
        setupRecycler();

        presenter.listenForResponseChange();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    private void setupPoll() {
        Poll poll = getPoll();
        question.setText(poll.getQuestion());
    }

    private void setupRecycler() {
        adapter.setOnResponseClickListener(this);
        adapter.setResponses(presenter.getResponses());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_multiple_choice;
    }

    @Override
    public Poll getPoll() {
        return getArguments().getParcelable(IntentUtil.ARGS_POLL);
    }

    @Override
    public void setClickable(boolean clickable, int index) {
        this.clickable = clickable;
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) recyclerView.getChildAt(i);
            viewGroup.setClickable(false);
            if (i == index) {
                viewGroup.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorChoiceDisabled));
            }
        }
    }

    @Override
    public String getUserID() {
        return authenticationManager.getCurrentUser().getUid();
    }

    @Override
    public boolean canRespond() {
        return clickable;
    }

    @Override
    public void setSelectedColor(int position) {
        //ViewGroup viewGroup = (ViewGroup) recyclerView.getChildAt(position);
        //viewGroup.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorChoiceSelected));
    }

    @Override
    public void showCastMessage() {
        Snackbar.make(container, R.string.response_casted, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseClicked(int position) {
//        Timber.d("On Response Clicked");
//        if (canRespond()) {
//            Timber.d("Can Respond");
//            presenter.submitResponse(position);
//        }
        presenter.submitResponse(position);
    }
}
