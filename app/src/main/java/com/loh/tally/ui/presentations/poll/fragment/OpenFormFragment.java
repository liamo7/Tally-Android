package com.loh.tally.ui.presentations.poll.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.domain.profanity.ProfanityChecker;
import com.loh.tally.ui.presentations.poll.adapter.OpenFormListAdapter;
import com.loh.tally.ui.presentations.poll.presenter.OpenFormContract;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * File: OpenFormFragment.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class OpenFormFragment extends PollFragment implements OpenFormContract.View {

    @BindView(R.id.question) TextView question;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.input) EditText input;
    @BindView(R.id.sendBtn) Button sendButton;

    @Inject OpenFormContract.Presenter presenter;
    @Inject ProfanityChecker profanityChecker;

    private OpenFormListAdapter adapter;

    public static OpenFormFragment newInstance(Poll poll) {
        Bundle args = new Bundle();
        args.putParcelable(IntentUtil.ARGS_POLL, poll);
        OpenFormFragment fragment = new OpenFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
        setupRecycler();
        setupPoll();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_open_form;
    }

    @Override
    public String getResponse() {
        String message = input.getText().toString();

        if (getPoll().isProfanityFilter()) {
            return profanityChecker.check(message);
        }

        return message;
    }

    @Override
    public void clearMessage() {
        input.setText("");
    }

    @Override
    public String getPollID() {
        Poll poll = getArguments().getParcelable(IntentUtil.ARGS_POLL);
        return poll != null ? poll.getId() : null;
    }

    @Override
    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(adapter.getItemCount());
    }

    private void setupRecycler() {
        adapter = new OpenFormListAdapter(presenter.getPollResponseReference());
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private LinearLayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        return layoutManager;
    }

    private void setupPoll() {
        Poll poll = getPoll();
        question.setText(poll.getQuestion());

    }

    @OnClick(R.id.sendBtn)
    protected void onSendButtonClicked() {
        presenter.submitResponse();
    }
}
