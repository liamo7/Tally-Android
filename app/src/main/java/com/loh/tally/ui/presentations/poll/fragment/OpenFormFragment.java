package com.loh.tally.ui.presentations.poll.fragment;

import android.os.Bundle;

import com.loh.tally.R;

/**
 * File: OpenFormFragment.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class OpenFormFragment extends PollFragment {

    public static OpenFormFragment newInstance() {
        Bundle args = new Bundle();
        OpenFormFragment fragment = new OpenFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_open_form;
    }
}
