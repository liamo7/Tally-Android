package com.loh.tally.ui.presentations.main.presenter;

import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: PresentationContract.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public interface PresentationContract {

    interface View extends BaseContract.View {

        void navigateToPresentations();

        void navigateToChat();

        String getModuleKey();
    }

    interface Presenter extends BaseContract.Presenter<PresentationContract.View> {

        void logout();

        void navigateToChat();

        void navigateToPresentations();
    }

}
