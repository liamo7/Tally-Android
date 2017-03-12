package com.loh.tally.ui.presentations.poll.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;
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

public class MultipleChoiceFragment extends PollFragment implements MultipleChoiceContract.View {

    @BindView(R.id.question) TextView question;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject MultipleChoiceContract.Presenter presenter;
    @Inject MultipleChoiceAdapter adapter;

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
        eventbus.post(new ChatFabEvent(false));
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

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_multiple_choice;
    }
}
