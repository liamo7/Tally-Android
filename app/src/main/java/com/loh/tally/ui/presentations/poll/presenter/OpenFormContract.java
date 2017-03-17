package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: OpenFormContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public interface OpenFormContract {

    interface View extends BaseContract.ListView {

        String getResponse();

        void clearMessage();

        String getPollID();

        void scrollToTop();
    }

    interface Presenter extends BaseContract.Presenter<OpenFormContract.View> {

        void submitResponse();

        DatabaseReference getPollResponseReference();
    }
}
