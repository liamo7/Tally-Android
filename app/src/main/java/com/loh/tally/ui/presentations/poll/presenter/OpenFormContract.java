package com.loh.tally.ui.presentations.poll.presenter;

import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.presenter.BaseContract;

import java.util.List;

/**
 * File: OpenFormContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface OpenFormContract {

    interface View extends BaseContract.ListView {

        String getResponse();

        void clearMessage();

        String getPollID();
    }

    interface Presenter extends BaseContract.Presenter<OpenFormContract.View> {

        void retrievePollResponses(AsyncCallback<List<String>> callback);

        void retrievePoll();

        void submitResponse();
    }
}
