package com.loh.tally.ui.presentations.poll.fragment;

import android.os.Bundle;

import com.loh.tally.R;

/**
 * File: MultipleChoiceFragment.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class MultipleChoiceFragment extends PollFragment {

    public static MultipleChoiceFragment newInstance() {
        Bundle args = new Bundle();
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        fragment.setArguments(args);
        return fragment;
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
