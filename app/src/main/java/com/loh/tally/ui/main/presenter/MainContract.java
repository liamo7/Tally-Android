package com.loh.tally.ui.main.presenter;

import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: MainContract.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Describes required functionality of Main presenter and its view.
 */
public interface MainContract {

    interface View extends BaseContract.View {

        void navigateToModules();

        void navigateToChats();

        void showEnrollFab();

        void hideEnrollFab();

        void showEnrollDialog();

        void showSuccesfulEnrollmentMessage();

        void showFailureEnrollmentMessage();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void navigateToModules();

        void navigateToChats();

        void logout();

        void handleSwitch(int position);

        void showEnrollDialog();

        void enrollOnModule(CharSequence input);
    }
}
