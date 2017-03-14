package com.loh.tally.ui.presentations.poll.presenter;

import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.presenter.BaseContract;

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

        String getModuleKey();

        Poll getPollFromAdapter(int position);

        void showFabChat();

        void hideFabChat();

        void hideChatMenu();

        void showChatMenu();
    }

    interface Presenter extends BaseContract.Presenter<PollContract.View> {

        void handlePollSelected(int position);

        void retrievePolls(AsyncCallback<List<Poll>> callback);
    }
}
