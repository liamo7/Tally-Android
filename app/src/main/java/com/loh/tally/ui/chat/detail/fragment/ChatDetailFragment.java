package com.loh.tally.ui.chat.detail.fragment;

import android.os.Bundle;

import com.loh.tally.R;
import com.loh.tally.ui.base.fragment.BaseFragment;

/**
 * File: ChatDetailFragment.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ChatDetailFragment extends BaseFragment {

    public static ChatDetailFragment newInstance() {
        Bundle args = new Bundle();
        ChatDetailFragment fragment = new ChatDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_chat;
    }
}
