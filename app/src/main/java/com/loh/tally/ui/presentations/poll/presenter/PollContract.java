package com.loh.tally.ui.presentations.poll.presenter;

import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.presenter.BaseContract;
import com.loh.tally.ui.presentations.poll.adapter.PollPagerAdapter;

import java.util.List;

/**
 * File: PollContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface PollContract {

    interface View extends BaseContract.View {

        String getPresentationID();
    }

    interface Presenter extends BaseContract.Presenter<PollContract.View> {

        void retrievePolls(AsyncCallback<List<Poll>> callback);

        List<Poll> getPolls();

        List<PollPagerAdapter.PollType> getPollTypes();
    }
}
