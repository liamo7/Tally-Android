package com.loh.tally.ui.presentations.poll.fragment;

import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.fragment.BaseFragment;
import com.loh.tally.util.IntentUtil;

/**
 * File: PollFragment.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public abstract class PollFragment extends BaseFragment {

    protected Poll getPoll() {
        return (Poll) getArguments().getParcelable(IntentUtil.ARGS_POLL);
    }
}
