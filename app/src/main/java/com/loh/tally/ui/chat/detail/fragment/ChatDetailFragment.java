package com.loh.tally.ui.chat.detail.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.chat.detail.adapter.ChatDetailAdapter;
import com.loh.tally.ui.chat.detail.presenter.ChatDetailContract;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * File: ChatDetailFragment.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ChatDetailFragment extends BaseFragment implements ChatDetailContract.View {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.input) EditText messageEditText;
    @BindView(R.id.sendBtn) Button sendButton;

    @Inject ChatDetailContract.Presenter presenter;

    private ChatDetailAdapter chatAdapter;

    public static ChatDetailFragment newInstance(String moduleID) {
        Bundle args = new Bundle();
        args.putString(IntentUtil.INTENT_MODULE_KEY, moduleID);
        ChatDetailFragment fragment = new ChatDetailFragment();
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
        setupRecyclerView();
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
        return R.layout.fragment_chat;
    }

    @Override
    public String getMessage() {
        return messageEditText.getText().toString();
    }

    @Override
    public void clearMessage() {
        messageEditText.setText("");
    }

    @Override
    public String getModuleID() {
        return getArguments().getString(IntentUtil.INTENT_MODULE_KEY, null);
    }

    @Override
    public void hideKeypad() {
        View focusView = getActivity().getCurrentFocus();

        if (focusView != null) {
            InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
    }

    private void setupRecyclerView() {
        chatAdapter = new ChatDetailAdapter(presenter.getChatReference());
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(chatAdapter);
    }

    private LinearLayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        return layoutManager;
    }

    @OnClick(R.id.sendBtn)
    protected void onSendButtonClicked() {
        presenter.sendMessage();
        recyclerView.smoothScrollToPosition(chatAdapter.getItemCount());

        if (getActivityBottomNav() != null) {
            getActivityBottomNav().setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.input)
    protected void onInputClicked() {
        // dirty fix as bottomnav is moved up when edittext is in focus
        if (getActivityBottomNav() != null) {
            getActivityBottomNav().setVisibility(View.GONE);
        }
    }

    private View getActivityBottomNav() {
        return getActivity().findViewById(R.id.bottomNavView);
    }
}
