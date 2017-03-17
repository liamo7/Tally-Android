package com.loh.tally.ui.chat.detail.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: ChatDetailContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public interface ChatDetailContract {

    interface View extends BaseContract.ListView {

        String getMessage();

        void clearMessage();

        String getModuleID();

        void hideKeypad();
    }

    interface Presenter extends BaseContract.Presenter<ChatDetailContract.View> {

        void sendMessage();

        DatabaseReference getChatReference();
    }
}
