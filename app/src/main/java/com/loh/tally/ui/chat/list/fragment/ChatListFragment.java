package com.loh.tally.ui.chat.list.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;

/**
 * File: ChatListFragment.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Handles the displaying of chats to the user.
 */
public class ChatListFragment extends BaseFragment {

    public static ChatListFragment newInstance() {
        Bundle args = new Bundle();
        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_chat_list;
    }
}
