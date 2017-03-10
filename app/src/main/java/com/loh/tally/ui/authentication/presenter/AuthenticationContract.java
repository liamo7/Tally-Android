package com.loh.tally.ui.authentication.presenter;

import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.authentication.event.AuthenticationRegisterEvent;
import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: AuthenticationContract.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface AuthenticationContract {

    interface View extends BaseContract.View {

        void navigateToRegister();

        void navigateToLogin();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void handleChoice(AuthenticationChoiceEvent event);

        void handleLogin(AuthenticationLoginEvent event);

        void handleRegister(AuthenticationRegisterEvent event);
    }
}
