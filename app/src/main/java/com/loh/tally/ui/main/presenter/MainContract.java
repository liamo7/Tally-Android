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
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void navigateToModules();

        void navigateToChats();

        void logout();
    }
}
