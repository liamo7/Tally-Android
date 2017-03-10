package com.loh.tally.ui.authentication.presenter;

import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.authentication.event.AuthenticationRegisterEvent;
import com.loh.tally.ui.base.presenter.BasePresenter;

/**
 * File: AuthenticationPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    public AuthenticationPresenter() {
    }

    @Override
    public void handleChoice(AuthenticationChoiceEvent event) {
        if (event.getChoice() == AuthenticationChoiceEvent.AuthenticationChoice.REGISTER) {
            getView().navigateToRegister();
            return;
        }

        getView().navigateToLogin();
    }

    @Override
    public void handleLogin(AuthenticationLoginEvent event) {
        String email = event.getEmail();
        String password = event.getPassword();

        // TODO: 10/03/2017 Post credentials to auth manager
    }

    @Override
    public void handleRegister(AuthenticationRegisterEvent event) {
        String email = event.getEmail();
        String password = event.getPassword();

        // TODO: 10/03/2017 Post credentials to auth manager
    }
}
