package com.loh.tally.ui.chat.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.domain.model.Module;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.ui.chat.list.adapter.ChatListAdapter;
import com.loh.tally.ui.chat.list.presenter.ChatListContract;
import com.loh.tally.ui.presentations.main.activity.PresentationActivity;
import com.loh.tally.ui.presentations.main.adapter.PresentationPagerAdapter;
import com.loh.tally.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * File: ChatListFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles the displaying of chats to the user.
 */
public class ChatListFragment extends BaseFragment implements ChatListContract.View, ChatListAdapter.OnChatListItemClickedListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject ChatListContract.Presenter presenter;
    @Inject ChatListAdapter listAdapter;

    public static ChatListFragment newInstance() {
        Bundle args = new Bundle();
        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
        setupRecycler();
    }

    private void setupRecycler() {
        listAdapter.setOnChatListItemClickedListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listAdapter.cleanup();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_chat_list;
    }

    @Override
    public void onChatItemClicked(Module module) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.INTENT_MODULE_KEY, module.getId());
        bundle.putInt(IntentUtil.INTENT_PRESENTATION_PAGE, PresentationPagerAdapter.FRAGMENT_CHAT);
        startActivity(PresentationActivity.getStartingIntent(getActivity(), bundle));
    }
}
